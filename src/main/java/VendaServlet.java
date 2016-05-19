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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        ProdutoDAO produto = new ProdutoDAO();        
        ArrayList<Produto> prod = new ArrayList<Produto>();     
        prod = produto.listaProduto();
          
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        String cpf = usuario.getIdUsuario();
        int idFilial = usuario.getIdFilial();
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double valor = 5;//hardcooode

        // criar venda
        VendaDAO venda = new VendaDAO();
        int idProduto;
        switch (request.getParameter("produto")) {
            case "Oleo Lubrificante":
                idProduto = 1;
                OleoLubrificante ol = new OleoLubrificante(idProduto, cpf, idFilial, quantidade);
                venda.vendaOleo(ol);
                break;
            case "Extintor Automotivo":
                idProduto = 2;
                Extintor ex = new Extintor(idProduto, cpf, idFilial, quantidade);
                venda.vendaExtintor(ex);
                break;
            case "Gasolina Comum":
                idProduto = 3;
                Combustivel gComum = new Combustivel(idProduto, cpf, idFilial, valor);
                venda.vendaCombustivel(gComum);
                break;
            case "Gasolina Aditivada":
                idProduto = 4;
                Combustivel gAditv = new Combustivel(idProduto, cpf, idFilial, valor);
                venda.vendaCombustivel(gAditv);
                break;
            case "Etanol":
                idProduto = 5;
                Combustivel etanol = new Combustivel(idProduto, cpf, idFilial, valor);
                venda.vendaCombustivel(etanol);
                break;
            case "Diesel":
                idProduto = 6;
                Combustivel diesel = new Combustivel(idProduto, cpf, idFilial, valor);
                venda.vendaCombustivel(diesel);
                break;
        }
        //  --------
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
