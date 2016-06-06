/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProdutoDAO;
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

    public void preencheProdutos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        ProdutoDAO produto = new ProdutoDAO();
        ArrayList<Produto> prod = new ArrayList<Produto>();
        prod = produto.listaProduto();
        request.getSession().setAttribute("prod", prod);

        // PREENCHER O ID DA VENDA
        VendaDAO v = new VendaDAO();
        int nVenda = v.ultimaVenda() + 1;

        request.setAttribute("venda", nVenda);
        request.setAttribute("produto", prod);

    }

    public boolean efetuaVenda(HttpServletRequest request,
            HttpServletResponse response, Usuario usuario, ProdutoDAO produtoDAO, int idProduto) {

        String cpf = usuario.getIdUsuario();
        int idFilial = usuario.getIdFilial();

        // criar venda
        VendaDAO venda = new VendaDAO();
        if (idProduto == 0) {
            return false;
        } else if (idProduto <= 4) {
            double valor = Double.parseDouble(request.getParameter("valor").replace(",", "."));
            Combustivel com = new Combustivel(cpf, idFilial, idProduto, valor);
            venda.vendaCombustivel(com);
            return true;
        } else if (idProduto == 5) {
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            OleoLubrificante oleo = new OleoLubrificante(idProduto, cpf, idFilial, quantidade);
            venda.vendaOleo(oleo);
            return true;
        } else if (idProduto == 6) {
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            Extintor ext = new Extintor(idProduto, cpf, idFilial, quantidade);
            venda.vendaExtintor(ext);
            return true;
        } else {
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            OleoLubrificante oleo = new OleoLubrificante(idProduto, cpf, idFilial, quantidade);
            venda.vendaOleo(oleo);
            return true;
        }

    }

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

        preencheProdutos(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int idProduto = Integer.parseInt(request.getParameter("produto"));
        

        if (efetuaVenda(request, response, usuario, produtoDAO, idProduto)) {
            request.setAttribute("mensagem", "Venda efetuada com sucesso!");
        } else {
            request.setAttribute("mensagem", "Algo deu errado, por favor tente novamente.");
        }

        preencheProdutos(request, response);
        response.sendRedirect("VendaServlet");

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
