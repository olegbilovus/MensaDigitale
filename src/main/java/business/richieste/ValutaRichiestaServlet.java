package business.richieste;

import business.consumatore.ConsumatoreBean;
import business.utente.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.ConsumatoreDao;
import storage.manager.RichiestaDao;

public class ValutaRichiestaServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final RichiestaDao richiestaDao = new RichiestaDao();
  private final ConsumatoreDao consumatoreDao = new ConsumatoreDao();

  public ValutaRichiestaServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int id = Integer.parseInt(request.getParameter("idRichiesta"));
    int esito = Integer.parseInt(request.getParameter("esito"));

    if (esito != 1 && esito != 2) {
      throw new IllegalArgumentException();
    }

    try {
      RichiestaBean richiesta = richiestaDao.doRetrieveByKey(id);
      ConsumatoreBean consumatore =
          (ConsumatoreBean) consumatoreDao.doRetrieveByKey(richiesta.getEmail());
      consumatore.setStatoServizi(esito % 2);
      richiesta.setEsito(esito);
      richiesta.setValutatore(((Utente) request.getSession().getAttribute("utente")).getEmail());
      richiestaDao.doUpdate(richiesta);
      consumatoreDao.doUpdate(consumatore);
      if (response.getWriter() != null) {
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert(\"Esito della richiesta memorizzato correttamente.\")");
        out.println("window.location.href = \"visualizzaRichiesteInSospeso.jsp\"");
        out.println("</script>");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
