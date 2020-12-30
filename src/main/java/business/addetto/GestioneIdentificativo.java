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
 * Servlet implementation class ControlloIdentificativo.
 */
public class GestioneIdentificativo extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public GestioneIdentificativo() {
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
    response.setContentType("application/json");
    String identificativo = request.getParameter("identificativo");
    JSONObject json = new JSONObject();
    String action = request.getParameter("action");
    String res = "response";
    try {
      PrenotazioneBean<String> prenotazione = prenotazioneDao.doRetrieveByKey(identificativo);
      if (prenotazione != null) {
        json.put(res, "200");
        if (action.equals("controllo")) {
          ConsumatoreBean consumatore = consumatoreDao.doRetrieveByKey(prenotazione.getEmail());
          json.put("prenotazione", prenotazione);
          json.put("consumatore", consumatore);
        } else if (action.equals("segnala")) {
          prenotazione.setEntrato(true);
          prenotazioneDao.doUpdate(prenotazione);
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

}
