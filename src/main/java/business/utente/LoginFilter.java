package business.utente;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * Filtro per la servlet Login.
 * L'accesso alla servlet e' consentito solo se non si e' gia loggati.
 */
public class LoginFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

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
