package business.prenotazioni;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import business.consumatore.ConsumatoreBean;
import storage.manager.PrenotazioneDao;


public class FilterResetSale implements Filter {

  /**
   * Filtro per resettare la disponibilita delle sale.
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    Date vecchiaData = (Date) request.getServletContext().getAttribute("dataSaleReset");
    Date oggi = new Date(System.currentTimeMillis());

    if (vecchiaData.getDay() != oggi.getDay() || vecchiaData.getMonth() != oggi.getMonth()
        || vecchiaData.getYear() != oggi.getYear()) {
      int numFasceOrarie = (Integer) request.getServletContext().getAttribute("numFasceOrarie");
      HashMap<Integer, HashMap<Integer, Boolean>> saleDisponibili = new HashMap<>(5);
      for (int i = 1; i <= 5; i++) {
        HashMap<Integer, Boolean> fasceOrarie = new HashMap<>(numFasceOrarie);
        for (int j = 1; j <= numFasceOrarie; j++) {
          fasceOrarie.put(j, true);
        }
        saleDisponibili.put(i, fasceOrarie);
      }
      request.getServletContext().setAttribute("saleDisponibili", saleDisponibili);
      request.getServletContext().setAttribute("dataSaleReset", oggi);
    }

    HttpServletRequest req = (HttpServletRequest) request;
    ConsumatoreBean consuamtore = (ConsumatoreBean) req.getSession().getAttribute("utente");
    if (consuamtore != null) {
      PrenotazioneBean<String> prenotazione = null;
      try {
        prenotazione = new PrenotazioneDao()
            .doRetrieveByDateAndMail(new Date(System.currentTimeMillis()), consuamtore.getEmail());
      } catch (SQLException e) {
        e.printStackTrace();
      }
      req.getSession().setAttribute("prenotazione", prenotazione);
    }
    chain.doFilter(request, response);
  }


}
