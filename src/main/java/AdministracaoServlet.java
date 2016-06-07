/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.FiliaisDAO;
import dao.UsuarioDAO;
import entity.Filial;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme Gomes
 */
public class AdministracaoServlet extends HttpServlet {

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

    public void preencheFiliais(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        FiliaisDAO filial = new FiliaisDAO();
        ArrayList<Filial> filiais = new ArrayList<Filial>();
        filiais = filial.listaFiliais();
        request.getSession().setAttribute("filiais", filiais);

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
        preencheFiliais(request, response);

        request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);

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
        preencheFiliais(request, response);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String idUsuario, nome, senha, funcao;
        int idFilial = 0, tipoPerfil = 0;
        String status = request.getParameter("check");

        if (request.getParameter("cadastrar") != null) {

            idUsuario = request.getParameter("cpf");
            nome = request.getParameter("nome");
            senha = request.getParameter("senha");
            String cnfSenha = request.getParameter("cnfSenha");

            // se nao existir cpf na base
            if (usuarioDAO.buscarUsuario(idUsuario) != null) {

                request.setAttribute("mensagem", "CPF ja cadastrado!");

            } else {
                if (!"".equals(request.getParameter("Filiais")) && !"".equals(request.getParameter("perfil")) && !nome.isEmpty()) {
                    idFilial = Integer.parseInt(request.getParameter("Filiais"));
                    tipoPerfil = Integer.parseInt(request.getParameter("perfil"));
                    funcao = request.getParameter("cargo");

                    if (senha.equals(cnfSenha)) {
                        Usuario usuario = new Usuario(idUsuario, senha, nome, idFilial, tipoPerfil, funcao);
                        usuarioDAO.incluirUsuario(usuario);
                        request.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
                    } else {
                        request.setAttribute("mensagem", "Verifique as senhas!");
                    }

                } else {
                    request.setAttribute("mensagem", "Verifique os campos!");
                }
            }

        } else if (request.getParameter("novo") != null) {

        } else if (request.getParameter("salva") != null) {

            idUsuario = (String) request.getSession().getAttribute("cpfAtualizar");
            nome = request.getParameter("nome");

            if (!"".equals(request.getParameter("Filiais")) && !"".equals(request.getParameter("perfil")) && !nome.isEmpty()) {
                idFilial = Integer.parseInt(request.getParameter("Filiais"));
                tipoPerfil = Integer.parseInt(request.getParameter("perfil"));
                funcao = request.getParameter("cargo");

                Usuario usuario = new Usuario(idUsuario, nome, idFilial, tipoPerfil, funcao);

                if (status.equals("INATIVO")) {
                    usuarioDAO.alterarUsuario(usuario);
                    usuarioDAO.inativarUsuario(idUsuario);
                    request.setAttribute("mensagem", "Cadastro atualizado!");
                } else {
                    usuarioDAO.ativarUsuario(idUsuario);
                    usuarioDAO.alterarUsuario(usuario);
                    request.setAttribute("mensagem", "Cadastro atualizado!");
                }
            } else {

                request.setAttribute("mensagem", "Verifique os campos!");
            }
            request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);

        } else {

            //processRequest(request, response);
            String idUser = request.getParameter("pesquisa");
            if (idUser.isEmpty()) {

            } else {
                UsuarioDAO userDAO = new UsuarioDAO();
                Usuario user = userDAO.buscarQualquerUsuario(idUser);

                if (user != null) {
                    if (user.getStatus()) {
                        status = "ATIVO";
                    } else {
                        status = "INATIVO";
                    }
                    request.setAttribute("status", status);
                    request.setAttribute("user", user);
                    request.getSession().setAttribute("cpfAtualizar", idUser);
                } else {

                    request.setAttribute("mensagem", "Usuário inválido!");
                }
            }

        }
        request.getRequestDispatcher("WEB-INF/administracao.jspx").forward(request, response);

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
