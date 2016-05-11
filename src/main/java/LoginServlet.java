/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * https://github.com/ftsuda82/pi3-agenda-2016
 *
 * @author Guilherme Gomes
 */
public class LoginServlet extends HttpServlet {

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
        HttpSession sessao = request.getSession(false);
        if (sessao == null || sessao.getAttribute("usuario") == null) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jspx");
            rd.forward(request, response);

            return;
        }
        // Usar o request.getContextPath() para corrigir o caminho da URL.
        response.sendRedirect(request.getContextPath() + "/NamtabServlet");
        //
        //request.getRequestDispatcher("WEB-INF/login.jspx").forward(request, response);
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
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        //response.sendRedirect(request.getContextPath() + "/NamtabServlet");
        // Validar nome de usuário e senha.
        //Usuario usuario = validar(usuario, senha);
 /**       if (usuario != null) {
            HttpSession sessao = request.getSession(false);
            if (sessao != null) {
                // Força invalidação da sessão anterior.
                sessao.invalidate();
            }
            sessao = request.getSession(true);
            sessao.setAttribute("usuario", usuario);
            response.sendRedirect(request.getContextPath() + "/NamtabServlet");
            return;
            // FIM CASO SUCESSO
        }
        response.sendRedirect(request.getContextPath() + "/erroLogin.jsp");
        AINDA SENDO IMPLEMENTADO**/
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
