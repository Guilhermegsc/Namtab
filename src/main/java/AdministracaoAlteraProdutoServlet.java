/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.ProdutoDAO;
import dao.UsuarioDAO;
import entity.Produto;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme Gomes
 */
public class AdministracaoAlteraProdutoServlet extends HttpServlet {

    public void preencheProdutos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        ProdutoDAO produto = new ProdutoDAO();
        ArrayList<Produto> prod = new ArrayList<Produto>();
        prod = produto.listaProduto();
        request.getSession().setAttribute("prod", prod);

        request.setAttribute("produto", prod);
        request.getRequestDispatcher("WEB-INF/administracao-AlteraProduto.jspx").forward(request, response);

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        preencheProdutos(request, response);

        /*request.getRequestDispatcher("WEB-INF/administracao-AlteraProduto.jspx").forward(request, response);
        String idUsuario = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        int idFilial = Integer.parseInt(request.getParameter("idFilial"));
        int tipoPerfil = Integer.parseInt(request.getParameter("perfil"));
        String funcao = request.getParameter("cargo");

        Usuario usuario = new Usuario(idUsuario, senha, nome, idFilial, tipoPerfil, funcao);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.incluirUsuario(usuario);
        System.out.println("aeeeee");
         */
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
        int idProduto = Integer.parseInt(request.getParameter("produto"));
        double novoPreco = Double.parseDouble(request.getParameter("novoPreco").replace(",", "."));

        if (novoPreco <= 0) {
            request.setAttribute("mensagem", "Houve um problema. Tente novamente.");
        } else {
            alteraPreco(idProduto, novoPreco);
            request.setAttribute("mensagem", "Preco atualizado!");
        }

        preencheProdutos(request, response);

    }

    public static void alteraPreco(int idProduto, double novoPreco) {
        ProdutoDAO p = new ProdutoDAO();
        p.atualizarProduto(idProduto, novoPreco);

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
