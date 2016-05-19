/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gustavo.soliveir16
 */
public class AutorizacaoFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain) throws IOException, ServletException {
        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

    // 2) NA LÓGICA IMPLEMENTADA, SE EXISTIR OBJETO DO USUÁRIO SIGNIFICA
        //    QUE USUÁRIO ESTÁ LOGADO
        //    CASO CONTRÁRIO, REDIRECIONA PARA TELA DE LOGIN
        if (usuario == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login");
            return;
        }

       /*try {
            // 3) VERIFICAR SE USUARIO PODE ACESSAR PAGINA
            if (verificarAcesso(usuario, httpRequest, httpResponse)) {
                // CHAMADA QUE ENVIA A REQUISIÇÃO PARA O PRÓXIMO FILTRO OU SERVLET
                chain.doFilter(request, response);
            } else {
                // SE NAO PODER ACESSAR, APRESENTA ERRO
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/ErroLoginServlet");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }*/
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
