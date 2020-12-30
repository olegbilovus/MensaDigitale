package business.addetto;

import business.consumatore.ConsumatoreBean;
import business.prenotazioni.PrenotazioneBean;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import storage.interfaces.ConsumatoreInterface;
import storage.interfaces.PrenotazioneInterface;
import storage.manager.ConsumatoreDao;
import storage.manager.PrenotazioneDao;

/**
 * Servlet implementation class Pagamento.
 */
public class Pagamento extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Pagamento() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
  }

  private static PrenotazioneInterface<PrenotazioneBean<String>> prenotazioneDao =
      new PrenotazioneDao();
  private static ConsumatoreInterface<ConsumatoreBean> consumatoreDao = new ConsumatoreDao();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String identificativo = request.getParameter("identificativo");
    JSONObject json = new JSONObject();
    String res = "response";
    try {
      PrenotazioneBean<String> prenotazione = prenotazioneDao.doRetrieveByKey(identificativo);
      if (prenotazione != null) {
        int costo = Integer.parseInt(request.getParameter("costo"));
        ConsumatoreBean consumatore = consumatoreDao.doRetrieveByKey(prenotazione.getEmail());
        if (consumatore.getSaldo() - costo >= 0) {
          updateSaldo(json, consumatore, costo);
          json.put(res, "200");
        } else {
          if (consumatore.isDocente()) {
            updateSaldo(json, consumatore, costo);
            json.put(res, "200");
          } else {
            json.put(res, "403");
          }
        }
      } else {
        json.put(res, "404");
      }
    } catch (SQLException e) {
      json.put(res, "500");
      e.printStackTrace();
    }
    response.getWriter().println(json.toString());
  }

  private void updateSaldo(JSONObject json, ConsumatoreBean consumatore, int costo)
      throws SQLException {
    consumatore.setSaldo(consumatore.getSaldo() - costo);
    consumatoreDao.doUpdate(consumatore);
    json.put("response", "200");
  }

}
