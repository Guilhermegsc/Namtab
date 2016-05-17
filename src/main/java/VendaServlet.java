/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProdutoDAO;
import dao.VendaDAO;
import entity.*;
import java.io.IOException;
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
         ProdutoDAO produto = new ProdutoDAO();
        String nomeProduto = request.getParameter("produto");           
        double preco = produto.buscaPreco(1);
        request.setAttribute("preco", preco);
        request.setAttribute("variavel", "Ta vendo o que aconteceeu");
        
        request.getRequestDispatcher("WEB-INF/venda.jspx").forward(request, response);
        
       
        
        System.out.println(preco);

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
        Object obj = request.getSession().getAttribute("usuario");
        Usuario user = (Usuario) obj;

        int idProduto = -1;
        double valor = 0;

        String aux = request.getParameter("valor");
        valor = Double.parseDouble(aux);
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        
        // criar venda
        VendaDAO venda = new VendaDAO();

        if (request.getParameter("produto").equals("Oleo Automotivo")) {
            idProduto = 1;
            OleoLubrificante ol = new OleoLubrificante(idProduto, user.getIdUsuario(), user.getIdFilial(), quantidade);
            venda.vendaOleo(ol);

        } else if (request.getParameter("produto").equals("Extintor Automotivo")) {
            idProduto = 2;
        } else if (request.getParameter("produto").equals("Gasolina Comum")) {
         
        } else if (request.getParameter("produto").equals("Gasolina Aditivada")) {
            idProduto = 4;
        } else if (request.getParameter("produto").equals("Etanol")) {
            idProduto = 5;
        } else if (request.getParameter("produto").equals("Diesel")) {
            idProduto = 6;
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
