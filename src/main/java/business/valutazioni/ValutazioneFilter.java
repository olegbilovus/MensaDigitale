package business.valutazioni;

import business.consumatore.ConsumatoreBean;
import business.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValutazioneFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    Utente utente = (Utente) req.getSession().getAttribute("utente");

    if (utente == null) {
      // non e' loggato
      resp.sendRedirect(resp.encodeURL(req.getContextPath() + "/login.jsp"));
      return;
    } else if (utente.getClass() != ConsumatoreBean.class) {
      // non e' consumatore
      resp.sendRedirect(resp.encodeURL(req.getContextPath() + "/index.jsp"));
      return;
    }

    chain.doFilter(request, response);

  }

}
