package business.prenotazioni;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");

    ConsumatoreBean consumatore =
        (ConsumatoreBean) request.getSession().getAttribute("consumatore");
    int fasciaOraria = Integer.parseInt(request.getParameter("fasciaOraria"));
    int sala = Integer.parseInt(request.getParameter("sala"));
    String action = request.getParameter("action");
    String saleDisponibili = "saleDisponibili";

    if (fasciaOraria >= 1
        && fasciaOraria <= (Integer) getServletContext().getAttribute("numFasceOrarie") && sala >= 1
        && sala <= 5) {
      try {
        HashMap<Integer, HashMap<Integer, Boolean>> saleState =
            ((HashMap<Integer, HashMap<Integer, Boolean>>) getServletContext()
                .getAttribute(saleDisponibili));
        PrenotazioneBean<String> bean = prenotazioneDAO.doRetrieveByDateAndFascia(
            new Date(System.currentTimeMillis()), consumatore.getEmail(), fasciaOraria);
        if (action.equals("invia") && bean == null) {
          int postiOccupati = prenotazioneDAO
              .doRetrieveByDateSalaFascia(new Date(System.currentTimeMillis()), sala, fasciaOraria)
              .size();
          HashMap<Integer, Integer> capienzaSale =
              (HashMap<Integer, Integer>) getServletContext().getAttribute("capienzaSale");
          if (capienzaSale.get(sala) - postiOccupati > 0) {
            Identificativo<String> identificativo =
                new QRCode(UUID.randomUUID().toString().replace("-", ""));

            PrenotazioneBean<String> prenotazione =
                new PrenotazioneBean<>(new Date(System.currentTimeMillis()), identificativo, sala,
                    fasciaOraria, consumatore.getEmail());

            prenotazioneDAO.doSave(prenotazione);
            request.getSession().setAttribute("prenotazione", prenotazione);

            if (capienzaSale.get(sala) - postiOccupati - 1 < 1) {
              saleState.get(sala).put(fasciaOraria, false);
              getServletContext().setAttribute(saleDisponibili, saleState);
            }
          }
        } else if (action.equals("elimina") && bean != null) {
          prenotazioneDAO.doDelete(bean);
          request.getSession().removeAttribute("prenotazione");
          saleState.get(sala).put(fasciaOraria, true);
          getServletContext().setAttribute(saleDisponibili, saleState);

        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      throw new IllegalArgumentException();
    }
  }


}
