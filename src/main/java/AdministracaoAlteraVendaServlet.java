/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.VendaDAO;
import entity.Produto;
import java.io.IOException;
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

    public void BuscarVenda(HttpServletRequest request, int x) { // SE X=-1 É PORQUE ESTÁ VINDO DO MÉTODO GET
        ArrayList<Produto> venda;                                // ENTÃO LIMPA A TELA NÃO PREENCHENDO A VENDA
        VendaDAO bd = new VendaDAO();
        int id = x;

        try {
            id = Integer.parseInt(request.getParameter("campo"));
            request.setAttribute("mensagem", "");
        } catch (Exception e) {
        }

        venda = bd.buscaVenda(id);

        if (venda.size() == 0 && x != -1) {
            request.setAttribute("mensagem", "Código de Venda "+id+" não encontrado. Favor consulte o relatório de venda.");
        } 

        request.getSession().setAttribute("venda", venda);

    }

    public void ExcluiVenda(HttpServletRequest request) {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("idVenda"));
            request.setAttribute("mensagem", "Venda "+id+" excluída com sucesso.");
        } catch (Exception e) {
        }
        
        VendaDAO v = new VendaDAO();
        v.inativarVenda(id);
        if(id == 0){
            BuscarVenda(request, id); // SE O ID=0 ENTÃO MOSTRA MENSAGEM DE NÃO ENCONTRADO
        }else{
            BuscarVenda(request, -1); // SE NÃO MOSTRA MENSAGEM DE EXCLUIDO COM SUCESSO
        }
        
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
        BuscarVenda(request, -1);

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
