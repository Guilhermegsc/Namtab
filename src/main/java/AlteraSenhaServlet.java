/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme Gomes
 */
public class AlteraSenhaServlet extends HttpServlet {

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
        request.getRequestDispatcher("WEB-INF/administracao-AlteraSenha.jspx").forward(request, response);
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
        UsuarioDAO u = new UsuarioDAO();
        String id = request.getParameter("idUsuario");
        String senhaAtual = request.getParameter("senhaAtual");
        String novaSenha = request.getParameter("senhaNova");
        String confirmaSenha = request.getParameter("senhaConfirma");

        if (senhaAtual.equals("")) {
            request.setAttribute("mensagem", "Por favor, digite a sua senha atual.");
        } else if (!senhaAtual.equals(u.getSenha(id))) {
            request.setAttribute("mensagem", "Senha atual inválida");
        } else if (novaSenha.equals("")) {
            request.setAttribute("mensagem", "Por favor, digite uma senha.");
        } else if (confirmaSenha.equals("")) {
            request.setAttribute("mensagem", "Por favor, confirme a nova senha.");
        } else if (novaSenha.equals(confirmaSenha)) {
            u.alterarSenha(id, novaSenha);
            request.setAttribute("mensagem", "Senha alterada com sucesso!");
        } else {
            request.setAttribute("mensagem", "As senhas não coincidem. Tente novamente.");
        }
        request.getRequestDispatcher("WEB-INF/administracao-AlteraSenha.jspx").forward(request, response);

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
