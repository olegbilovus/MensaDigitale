package startup;

import business.prenotazioni.FasciaOrariaBean;
import java.sql.SQLException;
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
  }

}
