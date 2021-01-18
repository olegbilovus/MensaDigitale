package business.piatti;

import business.addetto.AddettoBean;
import business.admin.AdministratorBean;
import business.utente.Utente;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PiattoFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    /*
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    
    Utente utente = (Utente) req.getSession().getAttribute("utente");

    if (utente == null) {
      // non e' loggato, fai il login
      resp.sendRedirect(resp.encodeURL(req.getContextPath() + "/login.jsp"));
      return;
    } else if ((utente.getClass() != AddettoBean.class)
        && (utente.getClass() != AdministratorBean.class)) {
      // non e' addetto e non e' admin
      System.out.println("non e' addetto e non e' admin");
      resp.sendRedirect(resp.encodeURL(req.getContextPath() + "/index.jsp"));
      return;
    }
    */

    chain.doFilter(request, response);
  }
}
