package business.valutazioni;

import business.piatti.PiattoBean;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.PiattoDao;
import storage.manager.ValutazioneDao;

/**
 * Servlet implementation class ValutazioneServlet.
 */
public class ValutazioneServlet extends HttpServlet {

  private static final ValutazioneDao valutazioneDao = new ValutazioneDao();
  private static final PiattoDao piattoDao = new PiattoDao();
  private String email;
  private String piatto;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("action");
    System.out.println(action);

    email = request.getParameter("email");
    piatto = request.getParameter("piatto");

    if (email != null) {
      if (action.equals("ottieniValutazioni")) {
        ottieniValutazioni(request, response);
        return;
      } else if (piatto != null && testFormatoPiatto(piatto)) {

        // controllo che il piatto esiste davvero
        try {
          PiattoBean tmp = piattoDao.doRetrieveByKey(piatto);
          if (tmp == null) {
            // il piatto non esiste in DB
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                "Il piatto non esiste nel database");
            throw new IllegalArgumentException();
          }
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }

        switch (action) {
          case "aggiungiValutazione" -> aggiungiValutazione(request, response);
          case "modificaValutazione" -> modificaValutazione(request, response);
          case "rimuoviValutazione" -> rimuoviValutazione(request, response);
          default -> response
              .sendError(HttpServletResponse.SC_BAD_REQUEST,
                  "Errore nei parametri della richiesta!");
        }

      } else {
        response
            .sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
        throw new IllegalArgumentException();
      }

    } else {
      response
          .sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
      throw new IllegalArgumentException();
    }

  }

  private void aggiungiValutazione(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    int valutazione = Integer.parseInt(request.getParameter("valutazione"));

    if (testValutazione(valutazione)) {

      try {
        valutazioneDao.doSave(
            new ValutazioneBean(email, piatto, valutazione, new Date(System.currentTimeMillis())));
        response.sendRedirect(
            response.encodeURL(request.getContextPath() + "/inserisciValutazione.jsp"));
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

    } else {
      response
          .sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
      throw new IllegalArgumentException();
    }

  }

  private void modificaValutazione(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    int valutazione = Integer.parseInt(request.getParameter("valutazione"));

    if (testValutazione(valutazione)) {

      try {
        valutazioneDao.doUpdate(
            new ValutazioneBean(email, piatto, valutazione, new Date(System.currentTimeMillis())));
        response.sendRedirect(
            response.encodeURL(request.getContextPath() + "/inserisciValutazione.jsp"));
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

    } else {
      response
          .sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
      throw new IllegalArgumentException();
    }

  }

  private void rimuoviValutazione(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    System.out.println(piatto);

    try {
      valutazioneDao.doDelete(new ValutazioneBean(email, piatto, 0, null));
      response
          .sendRedirect(response.encodeURL(request.getContextPath() + "/inserisciValutazione.jsp"));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  private void ottieniValutazioni(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    try {
      ArrayList<ValutazioneBean> valutazioniByEmail =
          (ArrayList<ValutazioneBean>) valutazioneDao.doRetrieveByEmail(email);
      //response.sendRedirect(response.encodeURL(request.getContextPath() + "/inserisciValutazione.jsp"));
      request.setAttribute("valutazioniByEmail", valutazioniByEmail);
      request.getRequestDispatcher(response.encodeURL("/inserisciValutazione.jsp"))
          .forward(request, response);
    } catch (SQLException | ServletException throwables) {
      throwables.printStackTrace();
    }
  }

  private boolean testFormatoPiatto(String piatto) {
    return piatto.matches("^([A-Z ])+$");
  }

  private boolean testValutazione(int valutazione) {
    return (valutazione >= 1 && valutazione <= 5);
  }

}
