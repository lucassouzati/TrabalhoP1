<%-- 
    Document   : salvarCapaLivro
    Created on : 27/12/2015, 15:03:46
    Author     : Usuario
--%>
<%@ page import="java.io.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.fileupload.*" %> 

<%
    try {
        String idLivro = session.getAttribute("idLivroEditar").toString();    
        DiskFileUpload fu = new DiskFileUpload();

        List fileItems = fu.parseRequest(request);

        Iterator i = fileItems.iterator();

        FileItem fi = (FileItem) i.next();

        InputStream in = fi.getInputStream();
        
        String imagem = idLivro + ".jpeg";

        File file = new File(getServletContext().getRealPath(getServletContext().getContextPath()) + "novo" + idLivro + ".jpeg");
        //C:/Users/Carlos/Documents/NetBeansProjects/TrabalhoP1/build/web/
        //File file2 = new File();
        FileOutputStream fos = new FileOutputStream(file);

        int c;
        while ((c = in.read()) != -1) {
            fos.write(c);
        }

        fos.close();
        //out.println(getServletContext().getRealPath(getServletContext().getContextPath()));
        session.setAttribute("capaLivro", "TrabalhoP1novo"+imagem);
        RequestDispatcher rd = request.getRequestDispatcher("srvEdicaoLivro?opcao=salvar");
        rd.forward(request, response);
    } catch (Exception e) {
        out.println(e.toString());
    }
%> 