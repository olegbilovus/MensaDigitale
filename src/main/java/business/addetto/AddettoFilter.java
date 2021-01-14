package business.addetto;

import business.utente.Utente;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddettoFilter implements Filter {
  /**
   * Filtro per le servlet il cui accesso deve essere consentito unicamente all'addetto.
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    Utente utente = (Utente) req.getSession().getAttribute("utente");
    if (utente == null || !(utente.getClass() == AddettoBean.class)) {
      res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    chain.doFilter(request, response);
  }
}
