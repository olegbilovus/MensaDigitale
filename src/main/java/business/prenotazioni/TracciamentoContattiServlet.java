package business.prenotazioni;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.interfaces.ConsumatoreInterface;
import storage.manager.ConsumatoreDao;

public class TracciamentoContattiServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ConsumatoreInterface<ConsumatoreBean> consumatoreDao = new ConsumatoreDao();

  public TracciamentoContattiServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String codiceFiscale = request.getParameter("cf");
    /*
     * I controlli sul codiceFiscale vengono fatti con javascript lato client
     */
    Date today = new Date(System.currentTimeMillis());
    Collection<ConsumatoreBean> listaTracciati =
        consumatoreDao.doRetrieveForTracciamento(codiceFiscale, getDataIniziale(today));
    
  }

  /*
   * Restituisce la data relativa ai 14 giorni prima della data odierna.
   */
  private String getDataIniziale(Date oggi) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    c.setTime(oggi);
    c.add(Calendar.DATE, -14);
    return df.format(c.getTime());    
  }
}
