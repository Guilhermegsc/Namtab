/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.VendaDAO;
import entity.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme Gomes
 */
public class VendaServlet extends HttpServlet {

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
        request.setAttribute("variavel", "Ta vendo o que aconteceeu");
        request.getRequestDispatcher("WEB-INF/venda.jspx").forward(request, response);

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
        request.getRequestDispatcher("WEB-INF/venda.jspx").forward(request, response);

        int idProduto = -1;
        double valor = 0;

        String aux = request.getParameter("valor");
        valor = Double.parseDouble(aux);
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        if (request.getParameter("produto").equals("Óleo automotivo")) {
            idProduto = 1;

        } else if (request.getParameter("produto").equals("Extintor automotivo")) {
            idProduto = 2;
        } else if (request.getParameter("produto").equals("Gasolina")) {
            
            idProduto = 3;
            Combustivel comb = new Combustivel(null, valor, null, quantidade, idProduto);
            VendaDAO dao = new VendaDAO();
            dao.vendaCombustivel(comb);
            
        } else if (request.getParameter("produto").equals("G. Aditivada")) {
            idProduto = 4;
        } else if (request.getParameter("produto").equals("Alcool")) {
            idProduto = 5;
        } else if (request.getParameter("produto").equals("Diesel")) {
            idProduto = 6;
        }

        Date data = new Date(2016, 05, 05);

        VendaDAO dao = new VendaDAO();

        try {
            dao.efetuaVenda("13456789101", 1, idProduto, data, valor);
        } catch (SQLException ex) {
            Logger.getLogger(VendaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
