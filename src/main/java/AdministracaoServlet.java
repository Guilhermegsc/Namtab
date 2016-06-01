/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.FiliaisDAO;
import dao.UsuarioDAO;
import entity.Filial;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme Gomes
 */
public class AdministracaoServlet extends HttpServlet {

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

    }

    public void preencheFiliais(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        FiliaisDAO filial = new FiliaisDAO();
        ArrayList<Filial> filiais = new ArrayList<Filial>();
        filiais = filial.listaFiliais();
        request.getSession().setAttribute("filiais", filiais);

        // request.setAttribute("produto", prod);
        request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);

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
        preencheFiliais(request, response);

        request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);

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
        preencheFiliais(request, response);
        if (request.getParameter("cadastrar") != null) {
            String idUsuario = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String senha = request.getParameter("senha");
            int idFilial = Integer.parseInt(request.getParameter("idFilial"));
            int tipoPerfil = Integer.parseInt(request.getParameter("perfil"));
            String funcao = request.getParameter("cargo");

            Usuario usuario = new Usuario(idUsuario, senha, nome, idFilial, tipoPerfil, funcao);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.incluirUsuario(usuario);
            request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);
        } else if (request.getParameter("novo") != null) {
            processRequest(request, response);
            request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);

        } else if (request.getParameter("salva") != null) {
            processRequest(request, response);
            String idUser = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String senha = request.getParameter("senha");
            int idFilial = Integer.parseInt(request.getParameter("idFilial"));
            int tipoPerfil = Integer.parseInt(request.getParameter("perfil"));
            String funcao = request.getParameter("cargo");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            Usuario usuario = new Usuario(idUser, senha, nome, idFilial, tipoPerfil, funcao);
            UsuarioDAO userDAO = new UsuarioDAO();
            if(!status){
            userDAO.inativarUsuario(idUser);
            }
            userDAO.alterarUsuario(usuario);
            request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);
        } else {

            processRequest(request, response);
            String idUser = request.getParameter("pesquisa");
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario user = userDAO.buscarQualquerUsuario(idUser);
            request.setAttribute("user", user);
            request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);

        }

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
