/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LivroDAO;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Autor;
import modelo.Categoria;
import modelo.Editora;
import modelo.Genero;
import modelo.Livro;
import modelo.Status;
import modelo.StatusLeitura;
import modelo.Usuario;

/**
 *
 * @author Lucas
 */
public class LivroController {
      public static String salvar(String titulo, String ano, String volume, String paginas, String edicao, String caminhofoto, String idAutor, String idEditora, String idCategoria, String idGenero, String idStatus, String idStatusLeitura, String idUsuario){
          String retorno = "";
        try {
            AutorDAO autordao = new AutorDAO();
            Autor autor = autordao.findById(Integer.parseInt(idAutor));
            EditoraDAO editoradao = new EditoraDAO();
            Editora editora = editoradao.findById(Integer.parseInt(idEditora));
            CategoriaDAO categoriadao = new CategoriaDAO();
            Categoria categoria = categoriadao.findById(Integer.parseInt(idCategoria));
            GeneroDAO generodao = new GeneroDAO();
            Genero genero = generodao.findById(Integer.parseInt(idGenero));            
            StatusDAO statusdao = new StatusDAO();
            Status status = statusdao.findById(Integer.parseInt(idStatus));
            StatusLeituraDAO statusleituradao = new StatusLeituraDAO();
            StatusLeitura statusleitura = statusleituradao.findById(Integer.parseInt(idStatusLeitura));
            
            /*
            List<Livro> livros = retornaLivros(titulo);
            System.out.println(livros.get(0).getTitulo());
            for(int i = 0; i< livros.size(); i++){
                if(livros.get(i).getTitulo().equals(titulo) && livros.get(i).getEdicao().equals(edicao) && livros.get(i).getVolume() == Integer.parseInt(volume)){
                    return "Livro já existente.";
                }
            }*/
            Livro l = new Livro(titulo, Integer.parseInt(ano), Integer.parseInt(volume), Integer.parseInt(paginas), edicao, caminhofoto, autor, editora, categoria, genero, status, statusleitura);
            new LivroDAO().salvar(l);
            UsuarioController.vinculaLivro(LivroController.retornaLivrosId(titulo, volume, edicao)+"", new UsuarioDAO().findById(Integer.parseInt(idUsuario)).getId()+"");
          } catch (Exception e) {
                retorno += "Livro já existente.";
                return retorno;
              
          }
          return retorno;
        
        
    }
    
    public static void editar(String id, String titulo, String ano, String volume, String paginas, String edicao, String caminhofoto, String idAutor, String idEditora, String idCategoria, String idGenero, String idStatus, String idStatusLeitura){
        AutorDAO autordao = new AutorDAO();
        Autor autor = autordao.findById(Integer.parseInt(idAutor));
        EditoraDAO editoradao = new EditoraDAO();
        Editora editora = editoradao.findById(Integer.parseInt(idEditora));
        CategoriaDAO categoriadao = new CategoriaDAO();
        Categoria categoria = categoriadao.findById(Integer.parseInt(idCategoria));
        GeneroDAO generodao = new GeneroDAO();
        Genero genero = generodao.findById(Integer.parseInt(idGenero));
        StatusDAO statusdao = new StatusDAO();
        Status status = statusdao.findById(Integer.parseInt(idStatus));
        StatusLeituraDAO statusleituradao = new StatusLeituraDAO();
        StatusLeitura statusleitura = statusleituradao.findById(Integer.parseInt(idStatusLeitura));
        
        Livro l = new Livro(Integer.parseInt(id), titulo, Integer.parseInt(ano), Integer.parseInt(volume), Integer.parseInt(paginas), edicao, caminhofoto, autor, editora, categoria, genero, status, statusleitura);
        new LivroDAO().alterar(l);
    }
    
    public static void excluir(String idUsuario, String id){
        Usuario usuario = new UsuarioDAO().findById(Integer.parseInt(idUsuario));
        List<Livro> livros = new ArrayList<Livro>();
        //System.out.println(usuario.getNome());
        livros = usuario.getLivros();
        for(int i = 0; i<livros.size(); i++){
            if(livros.get(i).getId() == Integer.parseInt(id)){
                livros.remove(i);
            }
        }
        usuario.setLivros(livros);
        new UsuarioDAO().alterar(usuario);
        new LivroDAO().excluir(Integer.parseInt(id));
    }
    
    public static String retornaCampo(String id, String campo){
        String retorno = "";
        LivroDAO dao = new LivroDAO();
        Livro l = dao.findById(Integer.parseInt(id));
       
        try {
            Class<?> classe = Livro.class;
            Field atributo;
            atributo = classe.getDeclaredField(campo);
            atributo.setAccessible(true);
            Object value;    
            value = atributo.get(l);
            retorno = value.toString();

        } catch (NoSuchFieldException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
    
     public static String retornaSelect(){
        
        LivroDAO dao = new LivroDAO();
        List<Livro> livros = dao.findAll();
        String retorno = "";
        for(int i=0; i< livros.size(); i++){
            retorno += "<option value='"+livros.get(i).getId() +"'>"+livros.get(i).getTitulo()+"</option>";
        }
        return retorno;
    }
     
      public static int retornaId(String titulo){
        LivroDAO dao = new LivroDAO();
        Livro u = dao.findByCollumPalavra("titulo", titulo   );
         return u.getId();
    }
      
    public static List<Livro> retornaLivros(String titulo)  {
        LivroDAO dao = new LivroDAO();
        List<Livro> livrosencontrados = dao.listaLivrosPorNome(titulo);
        return livrosencontrados;
    }
    
     public static int retornaLivrosId(String titulo, String volume, String edicao)  {
        LivroDAO dao = new LivroDAO();
        List<Livro> livrosencontrados = dao.listaLivrosPorNome(titulo);
         for(int i = 0; i< livrosencontrados.size(); i++){
                if(livrosencontrados.get(i).getTitulo().equals(titulo) && livrosencontrados.get(i).getEdicao().equals(edicao) && livrosencontrados.get(i).getVolume() == Integer.parseInt(volume)){
                    return livrosencontrados.get(i).getId();
                }
            }
        return 0;
    }
      
    public static String listaLivroPorUsuario(String idUsuario){
        String retorno = "";
        
        /*
        <div class="col-sm-4 col-lg-4 col-md-4">
            <div class="thumbnail">
                <img src="http://placehold.it/320x150" alt="">
                <div class="caption">
                    <h4 class="pull-right">$64.99</h4>
                    <h4><a href="#">Second Product</a>
                    </h4>
                    <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                </div>
                <div class="ratings">
                    <p class="pull-right">12 reviews</p>
                    <p>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                    </p>
                </div>
            </div>
        </div>
        */
        UsuarioDAO usuariodao = new UsuarioDAO();
        Usuario usuario = usuariodao.findById(Integer.parseInt(idUsuario));
        //LivroDAO livrodao = new LivroDAO();
        List<Livro> livros = usuario.getLivros();//livrodao.listaLivrosPorIdUsuario(Integer.parseInt(idUsuario));
        
        for(int i = 0; i <livros.size(); i++){
            retorno += "" +
            "<div class=\"col-sm-4 col-lg-4 col-md-4\">\n" +
            "   <div class=\"thumbnail\">" + 
            "       <img width=256px height=256px src='"+livros.get(i).getCaminhofoto() + "' alt=''>" +
                    
            "        <div class=\"caption\">  " +
       //     "           <h4 class=\"pull-right\">$64.99</h4>" + 
            "           <h4>Gênero: "+ livros.get(i).getGenero().getNome() + "</h4>"+
            "           <h4>Status: "+ livros.get(i).getStatus().getNome() + "</h4>"+
            "        </div>   "+/*
            "        <div class=\"ratings\">\n" +
            "            <p class=\"pull-right\">12 reviews</p>\n" +
            "            <p>\n" +
            "            <span class=\"glyphicon glyphicon-star\"></span>\n" +
            "            <span class=\"glyphicon glyphicon-star\"></span>\n" +
            "            <span class=\"glyphicon glyphicon-star\"></span>\n" +
            "            <span class=\"glyphicon glyphicon-star\"></span>\n" +
            "            <span class=\"glyphicon glyphicon-star-empty\"></span>\n" +
            "            </p>\n" +
            "            </div>\n" +*/
            "            </div>\n" +
            "        </div>"       ;
            
        }
        
        return retorno;
    }
    
    public static String listaLivroPorUsuarioFiltrado(String idUsuario, String filtro, String idFiltro){
        String retorno = "";
        int idfiltro = Integer.parseInt(idFiltro);
        /*
        <div class="col-sm-4 col-lg-4 col-md-4">
            <div class="thumbnail">
                <img src="http://placehold.it/320x150" alt="">
                <div class="caption">
                    <h4 class="pull-right">$64.99</h4>
                    <h4><a href="#">Second Product</a>
                    </h4>
                    <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                </div>
                <div class="ratings">
                    <p class="pull-right">12 reviews</p>
                    <p>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                    </p>
                </div>
            </div>
        </div>
        */
        UsuarioDAO usuariodao = new UsuarioDAO();
        Usuario usuario = usuariodao.findById(Integer.parseInt(idUsuario));
        System.out.println(usuario.getNome());
        //LivroDAO livrodao = new LivroDAO();
        List<Livro> livros = usuario.getLivros();//livrodao.listaLivrosPorIdUsuario(Integer.parseInt(idUsuario));
        System.out.println(livros.get(0).getTitulo());
        List<Livro> livrosfiltrados = new ArrayList<Livro>();
        
        if (filtro.equals("categoria")){
            for(int i = 0; i <livros.size(); i++){
                if(livros.get(i).getCategoria().getId() == idfiltro){
                    livrosfiltrados.add(livros.get(i));
                }
            }
        } else if(filtro.equals("genero")){
            for(int i = 0; i <livros.size(); i++){
                if(livros.get(i).getGenero().getId() == idfiltro){
                    livrosfiltrados.add(livros.get(i));
                }
            }
        } else if (filtro.equals("status")){
            for(int i = 0; i <livros.size(); i++){
                if(livros.get(i).getStatus().getId() == idfiltro){
                    livrosfiltrados.add(livros.get(i));
                }
            }  
        } else if(filtro.equals("statusleitura")){
            for(int i = 0; i <livros.size(); i++){
                if(livros.get(i).getStatusleitura().getId() == idfiltro){
                    livrosfiltrados.add(livros.get(i));
                }
            }
        }
        for(int i = 0; i <livrosfiltrados.size(); i++){
            retorno += "" +
            "<div class=\"col-sm-4 col-lg-4 col-md-4\">\n" +
            "   <div class=\"thumbnail\">" + 
            "       <img src='"+livrosfiltrados.get(i).getCaminhofoto() + "' alt=''>" +
                    "           <h4>Gênero: "+ livros.get(i).getGenero().getNome() + "</h4>"+
            "           <h4>Status: "+ livros.get(i).getStatus().getNome() + "</h4>"+
                    /*
            "        <div class=\"caption\">  " +
            "           <h4 class=\"pull-right\">$64.99</h4>" + 
            "           <h4><a href=\"#\">"+ livros.get(i).getTitulo() + "</a></h4>"+
            "        </div>   "+
            "        <div class=\"ratings\">\n" +
            "            <p class=\"pull-right\">12 reviews</p>\n" +
            "            <p>\n" +
            "            <span class=\"glyphicon glyphicon-star\"></span>\n" +
            "            <span class=\"glyphicon glyphicon-star\"></span>\n" +
            "            <span class=\"glyphicon glyphicon-star\"></span>\n" +
            "            <span class=\"glyphicon glyphicon-star\"></span>\n" +
            "            <span class=\"glyphicon glyphicon-star-empty\"></span>\n" +
            "            </p>\n" +
            "            </div>\n" +*/
            "            </div>\n" +
            "        </div>"       ;
            
        }
        
        return retorno;
    }
    
    public static String contaLivroPorUsuarioEFiltro(String idUsuario, String filtro, String idFiltro){
        String retorno = "";
        int idfiltro = Integer.parseInt(idFiltro);
        UsuarioDAO usuariodao = new UsuarioDAO();
        Usuario usuario = usuariodao.findById(Integer.parseInt(idUsuario));
        List<Livro> livros = usuario.getLivros();
        List<Livro> livrosfiltrados = new ArrayList<Livro>();
        if (filtro.equals("categoria")){
            for(int i = 0; i <livros.size(); i++){
                if(livros.get(i).getCategoria().getId() == idfiltro){
                    livrosfiltrados.add(livros.get(i));
                }
            }
        } else if(filtro.equals("genero")){
            for(int i = 0; i <livros.size(); i++){
                if(livros.get(i).getGenero().getId() == idfiltro){
                    livrosfiltrados.add(livros.get(i));
                }
            }
        } else if (filtro.equals("status")){
            for(int i = 0; i <livros.size(); i++){
                if(livros.get(i).getStatus().getId() == idfiltro){
                    livrosfiltrados.add(livros.get(i));
                }
            }  
        } else if(filtro.equals("statusleitura")){
            for(int i = 0; i <livros.size(); i++){
                if(livros.get(i).getStatusleitura().getId() == idfiltro){
                    livrosfiltrados.add(livros.get(i));
                }
            }
        }else if(filtro.equals("")){
            for(int i = 0; i <livros.size(); i++){
                livrosfiltrados.add(livros.get(i));
             
             
            }
        }
        retorno += "" + livrosfiltrados.size();
        return retorno;
        
        
    }
}
