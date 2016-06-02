/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.VendaDAO;
import entity.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thiago
 */
public class AdministracaoAlteraVendaServlet extends HttpServlet {

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

    }

    public void BuscarVenda(HttpServletRequest request) {
        ArrayList<Produto> venda;
        int id = 0;
        VendaDAO bd = new VendaDAO();
        
        try {
           id = Integer.parseInt(request.getParameter("campo"));
        } catch (Exception e) {
        }
        
        venda = bd.buscaVenda(id);
        request.getSession().setAttribute("venda", venda);

    }
    
    public void ExcluiVenda(HttpServletRequest request){
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("idVenda"));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        VendaDAO v = new VendaDAO();
        v.inativarVenda(id);
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

        BuscarVenda(request);
        request.getRequestDispatcher("WEB-INF/administracao-AlteraVenda.jspx").forward(request, response);
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
        ExcluiVenda(request);
        BuscarVenda(request);
        request.getRequestDispatcher("WEB-INF/administracao-AlteraVenda.jspx").forward(request, response);
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
