package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.addetto.AddettoBean;
import business.consumatore.ConsumatoreBean;
import business.richieste.RichiestaBean;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RichiestaDaoTest {

  private final RichiestaDao dao = new RichiestaDao();
  private final AddettoDao daoA = new AddettoDao();
  private final AddettoBean addetto = new AddettoBean("testerAd@unisa.it", "tester", "tester", 1);
  private final ConsumatoreDao daoC = new ConsumatoreDao();
  private final ConsumatoreBean consumatore =
      new ConsumatoreBean("testerP8@unisa.it", "tester", "tester",
          1, "tester", new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester",
          "tester", "tester", false, false, 0, 1);
  private final RichiestaBean bean =
      new RichiestaBean(-1, consumatore.getEmail(), 0, addetto.getEmail());

  @BeforeEach
  public void initEach() throws SQLException {
    daoA.doSave(addetto);
    daoC.doSave(consumatore);
    dao.doSave(bean);

  }

  @AfterEach
  public void destroyEach() throws SQLException {
    dao.doDelete(bean);
    daoA.doDelete(addetto);
    daoC.doDelete(consumatore);
  }

  @Test
  void testDoRetrieveByKey() throws SQLException {
    assertTrue(dao.doRetrieveByKey(bean.getId()) != null);
  }

  @Test
  void testDoRetrieveAll() throws SQLException {
    assertTrue(dao.doRetrieveAll().size() != 0);
  }

  @Test
  void testDoSave() throws SQLException {
    RichiestaBean bean2 = new RichiestaBean(-3, consumatore.getEmail(), 0, addetto.getEmail());
    try {
      dao.doSave(bean2);
      assertTrue(dao.doRetrieveByKey(bean2.getId()) != null);
    } finally {
      dao.doDelete(bean2);
    }
  }

  @Test
  void testDoUpdate() throws SQLException {
    int esito = 1;
    bean.setEsito(esito);
    dao.doUpdate(bean);
    assertTrue(dao.doRetrieveByKey(bean.getId()).getEsito() == esito);
  }

  @Test
  void testDoDelete() throws SQLException {
    RichiestaBean bean2 = new RichiestaBean(-3, consumatore.getEmail(), 0, addetto.getEmail());
    dao.doSave(bean2);
    dao.doDelete(bean2);
    assertTrue(dao.doRetrieveByKey(bean2.getId()) == null);
  }

  @Test
  void testDoRetrieveInSospeso() {
    assertTrue(dao.doRetrieveInSospeso().size() != 0);
  }

}
