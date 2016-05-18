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
        int id = 1;

        /* if (nomeProduto.equals("oleo")) {
         id = 2;
         } else if (nomeProduto.equals("extintor")) {
         id = 2;
         } else if (nomeProduto.equals("gas")) {
         id = 3;
         } else if (nomeProduto.equals("gadt")) {
         id = 4;
         } else if (nomeProduto.equals("etanol")) {
         id = 5;
         } else if (nomeProduto.equals("diesel")) {
         id = 6;
         }*/
        double preco = produto.buscaPreco(id);
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
        Object obj = request.getSession().getAttribute("usuario");
        Usuario user = (Usuario) obj;
        String cpf = user.getIdUsuario();
        int idFilial = user.getIdFilial();
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String aux = request.getParameter("valor");
        double valor = Double.parseDouble(aux);

        // criar venda
        VendaDAO venda = new VendaDAO();
        int idProduto;
        switch (request.getParameter("produto")) {
            case "Oleo Automotivo":
                idProduto = 1;
                OleoLubrificante ol = new OleoLubrificante(idProduto, user.getIdUsuario(), user.getIdFilial(), quantidade);
                venda.vendaOleo(ol);
                break;
            case "Extintor Automotivo":
                idProduto = 2;
                Extintor ex = new Extintor(idProduto, user.getIdUsuario(), user.getIdFilial(), quantidade);
                venda.vendaExtintor(ex);
                break;
            case "Gasolina Comum":
                break;
            case "Gasolina Aditivada":
                idProduto = 4;
                break;
            case "Etanol":
                idProduto = 5;
                break;
            case "Diesel":
                idProduto = 6;
                break;
        }
        //  --------
        processRequest(request, response);
        request.getRequestDispatcher("WEB-INF/venda.jspx").forward(request, response);

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
