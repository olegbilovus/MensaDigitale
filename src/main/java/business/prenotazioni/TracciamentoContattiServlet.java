package business.prenotazioni;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
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
     * I controlli sul codiceFiscale vengono fatti con javascript lato client o tramite filtro
     */
    Date today = new Date(System.currentTimeMillis());
    Collection<String> listaTracciati =
        consumatoreDao.doRetrieveForTracciamento(codiceFiscale, getDataIniziale(today));
    JSONArray jsArray = new JSONArray();
    int i = 0;
    for (String s : listaTracciati) {
      String[] splitted = s.split("\\|");
      JSONObject jsObj = new JSONObject();
      jsObj.put("nome", splitted[0] + " " + splitted[1]);
      jsObj.put("email", splitted[2]);
      jsObj.put("ora", splitted[3]);
      jsObj.put("sala", splitted[4]);
      jsObj.put("dataPrenotazione", splitted[5]);

      jsArray.put(i, jsObj);
      i += 1;
    }

    PrintWriter pw = response.getWriter();
    pw.print(jsArray.toString());
    pw.close();
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
