package business.utente;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * Filtro per la servlet Login. L'accesso alla servlet e' consentito solo se non si e' gia loggati.
 */
public class LoginFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    String action = req.getParameter("action");
    Utente utente = (Utente) req.getSession().getAttribute("utente");

    if (action != null && !action.equals("logOut") && utente != null) {
      // gia' loggato ma prova a rifare il login
      resp.sendRedirect(resp.encodeURL(req.getContextPath() + "/index.jsp"));
      return;
    }

    chain.doFilter(request, response);
  }
}
