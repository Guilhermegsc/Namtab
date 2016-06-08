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

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

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

    //Traz todas as filiais para serem mostradas na lista de escolha do usuario na tela.
    public void preencheFiliais(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        FiliaisDAO filial = new FiliaisDAO();
        ArrayList<Filial> filiais = new ArrayList<Filial>();
        filiais = filial.listaFiliais();
        request.getSession().setAttribute("filiais", filiais);

    }

    public Boolean validarCPF(String cpf) {
        int soma = 0, indice = 8, contador = 10;
        Integer vetor[] = new Integer[11];
        Character vetorChar[] = new Character[11];

        if (cpf.length() < 11) {
            return false;
        }
        for (int i = 0; i < cpf.length(); i++) {
            vetorChar[i] = cpf.charAt(i);
            vetor[i] = Character.getNumericValue(vetorChar[i]);
        }
        for (int i = 0; i < cpf.length() - 2; i++) {
            soma = soma + (vetor[i] * contador);
            contador--;
        }
        int pDigito = 11 - (soma % 11);
        if (pDigito == 10 || pDigito == 11) {
            pDigito = 0;
        }

        contador = 11;
        soma = 0;
        for (int i = 0; i < cpf.length() - 1; i++) {
            soma = soma + (vetor[i] * contador);
            contador--;
        }
        int sDigito = 11 - (soma % 11);
        if (sDigito == 10 || sDigito == 11) {
            sDigito = 0;
        }
        if (pDigito == vetor[9] && sDigito == vetor[10]) {
            return true;
        }
        return false;
    }

    public void cadastraUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String idUsuario = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String cnfSenha = request.getParameter("cnfSenha");

        if ("".equals(idUsuario)) {
            request.setAttribute("mensagem", "CPF não está preenchido!");
        } // Se já existir cpf na base.
        else if (validarCPF(idUsuario) == false) {
            request.setAttribute("mensagem", "CPF inválido!");
        } else if (usuarioDAO.buscarQualquerUsuario(idUsuario) != null) {
            request.setAttribute("mensagem", "CPF ja cadastrado!");
        } else if ("".equals(request.getParameter("Filiais"))
                || "".equals(request.getParameter("perfil"))
                || "".equals(request.getParameter("cargo")) || nome.isEmpty()) {
            request.setAttribute("mensagem", "Não foi possível concluir o processo. Preencha todos os campos!");
        } else if ("".equals(senha)) {
            request.setAttribute("mensagem", "Preencha a senha!");
        } else if (!senha.equals(cnfSenha)) {
            request.setAttribute("mensagem", "Campo Senha não confere com a Confirmação!");
        } else {
            int idFilial = Integer.parseInt(request.getParameter("Filiais"));
            int tipoPerfil = Integer.parseInt(request.getParameter("perfil"));
            String funcao = request.getParameter("cargo");
            Usuario usuario = new Usuario(idUsuario, senha, nome, idFilial, tipoPerfil, funcao);
            usuarioDAO.incluirUsuario(usuario);
            request.setAttribute("mensagem", "Usuário cadastrado com sucesso!");

        }

    }

    public void salvaAlteracoes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuarioPsq = (Usuario) request.getSession().getAttribute("userPsq");
        String status = request.getParameter("check");

        if (usuarioPsq == null) {
            request.setAttribute("mensagem", "Usuário não encontrado!");
        } else if ("".equals(request.getParameter("Filiais"))
                || "".equals(request.getParameter("perfil"))
                || "".equals(request.getParameter("cargo")) || "".equals(request.getParameter("nome"))) {
            request.setAttribute("mensagem", "Não foi possível concluir o processo. Preencha todos os campos!");
        } else {
            String idUsuario = usuarioPsq.getIdUsuario();
            String nome = request.getParameter("nome");
            String funcao = request.getParameter("cargo");
            int idFilial = Integer.parseInt(request.getParameter("Filiais"));
            int tipoPerfil = Integer.parseInt(request.getParameter("perfil"));

            Usuario usuario = new Usuario(idUsuario, nome, idFilial, tipoPerfil, funcao);

            //Verifica o status para alterá-lo.
            if (status.equals("INATIVO")) {
                usuarioDAO.alterarUsuario(usuario);
                usuarioDAO.inativarUsuario(idUsuario);
                request.setAttribute("mensagem", "Cadastro atualizado!");
            } else {
                usuarioDAO.ativarUsuario(idUsuario);
                usuarioDAO.alterarUsuario(usuario);
                request.setAttribute("mensagem", "Cadastro atualizado!");
            }

            FiliaisDAO filialDAO = new FiliaisDAO();
            Filial filPesquisada = filialDAO.buscaFilial(idFilial);
            request.getSession().setAttribute("status", status);
            request.getSession().setAttribute("userPsq", usuario);
            request.getSession().setAttribute("filialPesquisada", filPesquisada);

        }
    }

    public void pesquisaCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status;
        //processRequest(request, response);
        String idUser = request.getParameter("pesquisa");
        Usuario usuarioPesquisado = usuarioDAO.buscarQualquerUsuario(idUser);
        if (usuarioPesquisado == null) {
            request.setAttribute("mensagem", "Usuário não encontrado!");
        } else {
            FiliaisDAO filialDAO = new FiliaisDAO();
            Filial filPesquisada = filialDAO.buscaFilial(usuarioPesquisado.getIdFilial());
            if (usuarioPesquisado.getStatus()) {
                status = "ATIVO";
            } else {
                status = "INATIVO";
            }
            request.getSession().setAttribute("status", status);
            request.getSession().setAttribute("userPsq", usuarioPesquisado);
            request.getSession().setAttribute("filialPesquisada", filPesquisada);
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

        //----------------------------------CADASTRAR---------------------------
        if (request.getParameter("cadastrar") != null) {
            cadastraUsuario(request, response);
        } //---------------------------NOVO---------------------------------------
        else if (request.getParameter("novo") != null) {
            request.getSession().setAttribute("userPsq", null);
            request.getSession().setAttribute("filialPesquisada", null);
        } //--------------------------SALVA AS ALTERAÇÕES-------------------------
        else if (request.getParameter("salva") != null) {
            salvaAlteracoes(request, response);
        } //-------------------------------PESQUISA-------------------------------
        else {
            pesquisaCadastro(request, response);
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
