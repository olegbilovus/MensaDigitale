package business.prenotazioni;


import static org.junit.jupiter.api.Assertions.fail;

import business.consumatore.ConsumatoreBean;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.interfaces.ConsumatoreInterface;
import storage.manager.ConsumatoreDao;

class PrenotazioneServletTest {

  PrenotazioneServlet servlet = new PrenotazioneServlet();

  @BeforeEach
  public void init() throws SQLException {
    ConsumatoreBean tester = new ConsumatoreBean("testerPrenotazione@unisa.it", "tester", "tester",
        1, "tester", new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester",
        "tester", "tester", 0, 0, 0, 1);
    ConsumatoreInterface<ConsumatoreBean> consumatoreDao = new ConsumatoreDao();
    consumatoreDao.doSave(tester);
    // need login servlet to continue the testing
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse() {
    fail("Not yet implemented");
  }

}
