package business.richieste;

import business.utente.Utente;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.RichiestaDao;

public class ValutaRichiestaServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final RichiestaDao richiestaDao = new RichiestaDao();

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
      richiesta.setEsito(esito);
      richiesta.setValutatore(((Utente) request.getSession().getAttribute("utente")).getEmail());
      richiestaDao.doUpdate(richiesta);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
