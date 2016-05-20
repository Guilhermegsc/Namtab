/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProdutoDAO;
import dao.UsuarioDAO;
import dao.VendaDAO;
import entity.*;
import java.io.IOException;
import java.util.ArrayList;
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
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        ProdutoDAO produto = new ProdutoDAO();        
        ArrayList<Produto> prod = new ArrayList<Produto>();     
        prod = produto.listaProduto();
        request.getSession().setAttribute("prod", prod);
          
        request.setAttribute("produto", prod);
        request.setAttribute("variavel", "Ta vendo o que aconteceeu");

        request.getRequestDispatcher("WEB-INF/venda.jspx").forward(request, response);

        //System.out.println(preco);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Produto prod = (Produto) request.getSession().getAttribute("prod");
        
        String cpf = usuario.getIdUsuario();
        int idFilial = usuario.getIdFilial();
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double valor = Double.parseDouble(request.getParameter("valor").replace(",", "."));//hardcooode
        String produto = request.getParameter("produto");
        
        // criar venda
        VendaDAO venda = new VendaDAO();
        int idProduto;

        //  --------
        request.getRequestDispatcher("WEB-INF/venda.jspx").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
