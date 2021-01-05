package business.valutazioni;

import storage.manager.ValutazioneDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ValutazioneServlet extends HttpServlet {

  private static final ValutazioneDao dao = new ValutazioneDao();
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

    email = request.getParameter("email");
    piatto = request.getParameter("piatto");

    if (email != null && piatto != null && testFormatoPiatto(piatto)) {

      switch (action) {
        case "aggiungiValutazione" -> aggiungiValutazione(request, response);
        case "modificaValutazione" -> modificaValutazione(request, response);
        case "rimuoviValutazione" -> rimuoviValutazione();
        default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
      }

    } else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
    }

  }

  private void aggiungiValutazione(HttpServletRequest request, HttpServletResponse response) throws IOException {

    int valutazione = Integer.parseInt(request.getParameter("valutazione"));

    if (testValutazione(valutazione)){

      try {
        dao.doSave(
                new ValutazioneBean(email, piatto, valutazione, new Date(System.currentTimeMillis())));
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

    } else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
    }

  }

  private void modificaValutazione(HttpServletRequest request, HttpServletResponse response) throws IOException {

    int valutazione = Integer.parseInt(request.getParameter("valutazione"));

    if (testValutazione(valutazione)){

      try {
        dao.doUpdate(
            new ValutazioneBean(email, piatto, valutazione, new Date(System.currentTimeMillis())));
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }

    } else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei parametri della richiesta!");
    }

  }

  private void rimuoviValutazione() {

    try {
      dao.doDelete(new ValutazioneBean(email, piatto, 0, null));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  private boolean testFormatoPiatto(String piatto){
    return piatto.matches("^([A-Z])+$");
  }

  private boolean testValutazione(int valutazione){
    return (valutazione >= 1 && valutazione <= 5);
  }

}
