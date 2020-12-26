package business.prenotazioni;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.consumatore.ConsumatoreBean;
import storage.interfaces.PrenotazioneInterface;
import storage.manager.PrenotazioneDao;

/**
 * Servlet implementation class PrenotazioneServlet.
 */
public class PrenotazioneServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static PrenotazioneInterface<PrenotazioneBean<String>> prenotazioneDAO =
      new PrenotazioneDao();

  public PrenotazioneServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    ConsumatoreBean consumatore =
        (ConsumatoreBean) request.getSession().getAttribute("consumatore");
    int fasciaOraria = Integer.parseInt(request.getParameter("fasciaOraria"));
    if()
  }

}
