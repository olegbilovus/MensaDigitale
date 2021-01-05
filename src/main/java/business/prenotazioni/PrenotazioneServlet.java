package business.prenotazioni;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
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

  private static String saleDisponibili = "saleDisponibili";
  private static String error = "error";

  public PrenotazioneServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HashMap<Integer, HashMap<Integer, Boolean>> saleState =
        ((HashMap<Integer, HashMap<Integer, Boolean>>) getServletContext()
            .getAttribute(saleDisponibili));
    PrenotazioneBean<String> bean =
        (PrenotazioneBean<String>) request.getSession().getAttribute("prenotazione");
    try {
      prenotazioneDAO.doDelete(bean);
    } catch (SQLException e) {
      request.setAttribute(error, true);
      request.getRequestDispatcher(response.encodeURL("prenotazione.jsp")).forward(request,
          response);
      return;
    }
    request.getSession().removeAttribute("prenotazione");
    saleState.get(bean.getSala()).put(bean.getFasciaOraria(), true);
    getServletContext().setAttribute(saleDisponibili, saleState);
    request.getRequestDispatcher(response.encodeURL("prenotazione.jsp")).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    try {
      ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("utente");
      int fasciaOraria = Integer.parseInt(request.getParameter("fasciaOraria"));
      int sala = Integer.parseInt(request.getParameter("sala"));

      if (fasciaOraria >= 1
          && fasciaOraria <= (Integer) getServletContext().getAttribute("numFasceOrarie")
          && sala >= 1 && sala <= 5) {
        HashMap<Integer, HashMap<Integer, Boolean>> saleState =
            ((HashMap<Integer, HashMap<Integer, Boolean>>) getServletContext()
                .getAttribute(saleDisponibili));
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
        request.setAttribute(error, false);
      } else {
        request.setAttribute(error, true);
      }
    } catch (Exception e) {
      request.setAttribute(error, true);
    }
    request.getRequestDispatcher(response.encodeURL("prenotazione.jsp")).forward(request, response);
  }


}
