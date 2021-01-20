package business.richieste;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.ConsumatoreDao;
import storage.manager.RichiestaDao;

/**
 * Servlet implementation class ValutaRichiesta.
 *
 */
public class ValutaRichiesta extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final RichiestaDao richiestaDao = new RichiestaDao();
  private final ConsumatoreDao consumatoreDao = new ConsumatoreDao();

  public ValutaRichiesta() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));

    try {
      RichiestaBean r = richiestaDao.doRetrieveByKey(id);
      ConsumatoreBean c = consumatoreDao.doRetrieveByKey(r.getEmail());
      request.setAttribute("richiesta", r);
      request.setAttribute("rCognome", c.getCognome());
      request.setAttribute("rNome", c.getNome());
      request.setAttribute("rData", c.getDataDiNascita());
      request.setAttribute("rProvincia", c.getProvinciaNascita());
      request.setAttribute("rComune", c.getComuneNascita());
      request.setAttribute("rCodice", c.getCodiceFiscale());
      request.setAttribute("rCittadinanza", c.getCittadinanza());
      request.setAttribute("rRifugiato", c.getRifugiato());
      request.setAttribute("rResidenza", c.getResidenzaNucleoFamiliare());
      request.setAttribute("rIndirizzo", c.getIndirizzo());
      request.setAttribute("rTelefono", c.getTelefono());
      request.setAttribute("rCellulare", c.getCellulare());
      request.setAttribute("rEmail", c.getEmail());

      RequestDispatcher rd =
          request.getRequestDispatcher(
              response.encodeURL("/visualizzaRichiesta.jsp"));
      rd.forward(request, response);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
