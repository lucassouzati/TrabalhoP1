/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import controller.AutorController;
import controller.CategoriaController;
import controller.EditoraController;
import controller.GeneroController;
import controller.StatusController;
import controller.StatusLeituraController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class srvCadastroNovo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String tipo = request.getParameter("tipo");
            
            if(tipo.equals("autor")){
            
                String nome = request.getParameter("nome");
                AutorController.salvar(nome);
                
                
            }else if(tipo.equals("categoria")){
                
                String nome = request.getParameter("nome");
                CategoriaController.salvar(nome);
                
                
            }else if(tipo.equals("editora")){
            
                String nome = request.getParameter("nome");
                EditoraController.salvar(nome);
                
                
            }else if(tipo.equals("genero")){
            
                String nome = request.getParameter("nome");
                GeneroController.salvar(nome);
               
                
            }
            
                String listarAutor = AutorController.retornaSelect();
                String listarCategoria = CategoriaController.retornaSelect();
                String listarEditora = EditoraController.retornaSelect();
                String listarGenero = GeneroController.retornaSelect();
                String listarStatus = StatusController.retornaSelect();
                String listarStatusLeitura = StatusLeituraController.retornaSelect();
                RequestDispatcher rd = request.getRequestDispatcher("cadastroLivro.jsp?listarAutor="+listarAutor+
                        "&listarCategoria="+listarCategoria+"&listarEditora="+listarEditora+"&listarGenero="
                        +listarGenero+"&listarStatus="+listarStatus+"&listarStatusLeitura="+listarStatusLeitura);
                rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
