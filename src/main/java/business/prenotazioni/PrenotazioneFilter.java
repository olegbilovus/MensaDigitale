package business.prenotazioni;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class PrenotazioneFilter.
 */
public class PrenotazioneFilter implements Filter {

  /**
   * Servlet Filter implementation class PrenotazioneFilter.
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    ConsumatoreBean consumatore = (ConsumatoreBean) req.getSession().getAttribute("utente");
    PrenotazioneBean<String> prenotazione =
        (PrenotazioneBean<String>) req.getSession().getAttribute("prenotazione");
    String error = "error";

    if (consumatore == null || (req.getMethod().equals("POST") && prenotazione != null)
        || (req.getMethod().equals("GET") && prenotazione == null)) {
      req.setAttribute(error, true);
      System.out.println(getClass().getName());
      req.getRequestDispatcher(res.encodeURL("prenotazione.jsp")).forward(request, response);
      return;
    }
    chain.doFilter(request, response);
  }
  


}
