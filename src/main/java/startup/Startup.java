package startup;

import business.prenotazioni.FasciaOrariaBean;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import storage.interfaces.FasciaOrariaInterface;
import storage.manager.FasciaOrariaDao;

/**
 * Application Lifecycle Listener implementation class Startup.
 *
 */
@WebListener
public class Startup implements ServletContextListener {

  /**
   * Context created.
   */
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext ctx = sce.getServletContext();

    FasciaOrariaInterface<FasciaOrariaBean> fasciaOrariaDao = new FasciaOrariaDao();
    Integer numFasceOrarie = 0;
    try {
      numFasceOrarie = fasciaOrariaDao.doRetrieveAll().size();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    ctx.setAttribute("numFasceOrarie", numFasceOrarie);

    HashMap<Integer, Integer> capienzaSale = new HashMap<>(5);
    capienzaSale.put(1, 300);
    capienzaSale.put(2, 152);
    capienzaSale.put(3, 106);
    capienzaSale.put(4, 40);
    capienzaSale.put(5, 15);

    ctx.setAttribute("capienzaSale", capienzaSale);


    HashMap<Integer, HashMap<Integer, Boolean>> saleDisponibili = new HashMap<>(5);
    for (int i = 1; i <= 5; i++) {
      HashMap<Integer, Boolean> fasceOrarie = new HashMap<>(numFasceOrarie);
      for (int j = 1; j <= numFasceOrarie; j++) {
        fasceOrarie.put(j, true);
      }
      saleDisponibili.put(i, fasceOrarie);
    }

    ctx.setAttribute("saleDisponibili", saleDisponibili);
    ctx.setAttribute("dataSaleReset", new Date(System.currentTimeMillis()));

  }

}
