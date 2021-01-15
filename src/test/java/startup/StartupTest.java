package startup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.prenotazioni.FasciaOrariaBean;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import storage.manager.FasciaOrariaDao;

class StartupTest {

  private final Startup startup = new Startup();
  private final ServletContextEvent sce = Mockito.mock(ServletContextEvent.class);
  private final ServletContext ctx = Mockito.mock(ServletContext.class);
  private final FasciaOrariaDao daoF = new FasciaOrariaDao();

  @Test
  void testContextInitialized() throws SQLException {
    FasciaOrariaBean fascia = new FasciaOrariaBean(98, "11:40");
    try {
      Mockito.doReturn(ctx).when(sce).getServletContext();
      ArgumentCaptor<Integer> numFasceOrarie = ArgumentCaptor.forClass(Integer.class);

      Mockito.doNothing().when(ctx).setAttribute(Mockito.eq("numFasceOrarie"),
          numFasceOrarie.capture());
      Mockito.doNothing().when(ctx).setAttribute(Mockito.eq("capienzaSale"), Mockito.anyInt());

      daoF.doSave(fascia);

      startup.contextInitialized(sce);

      assertTrue(numFasceOrarie.getValue() == daoF.doRetrieveAll().size());

    } finally {
      daoF.doDelete(fascia);
    }

  }

}
