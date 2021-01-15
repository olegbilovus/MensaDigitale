package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.consumatore.ConsumatoreBean;
import business.piatti.PiattoBean;
import business.valutazioni.ValutazioneBean;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValutazioneDaoTest {

  private final ValutazioneDao dao = new ValutazioneDao();
  private final PiattoDao daoP = new PiattoDao();
  private final ConsumatoreDao daoC = new ConsumatoreDao();
  private final ConsumatoreBean consumatore =
      new ConsumatoreBean("testerP8@unisa.it", "tester", "tester",
          1, "tester", new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester",
          "tester", "tester", false, false, 0, 1);
  private final PiattoBean piatto = new PiattoBean("testerAb1", "av", "primo", 0, 0, 0, 0, 0);
  private final ValutazioneBean bean =
      new ValutazioneBean(consumatore.getEmail(), piatto.getNome(), 2,
          new Date(System.currentTimeMillis()));

  @BeforeEach
  public void initEach() throws SQLException {
    daoP.doSave(piatto);
    daoC.doSave(consumatore);
    dao.doSave(bean);

  }

  @AfterEach
  public void destroyEach() throws SQLException {
    dao.doDelete(bean);
    daoP.doDelete(piatto);
    daoC.doDelete(consumatore);
  }

  @Test
  void testDoRetrieveByKey() throws SQLException {
    assertTrue(dao.doRetrieveByKey(bean.getEmail(), bean.getPiatto()) != null);
  }

  @Test
  void testDoRetrieveAll() throws SQLException {
    assertTrue(dao.doRetrieveAll().size() != 0);
  }

  @Test
  void testDoSave() throws SQLException {
    PiattoBean piatto2 = new PiattoBean("testerAb5", "av", "primo", 0, 0, 0, 0, 0);
    daoP.doSave(piatto2);
    ValutazioneBean bean2 = new ValutazioneBean(consumatore.getEmail(), piatto2.getNome(), 2,
        new Date(System.currentTimeMillis()));
    try {
      dao.doSave(bean2);
      assertTrue(dao.doRetrieveByKey(bean2.getEmail(), bean2.getPiatto()) != null);
    } finally {
      dao.doDelete(bean2);
      daoP.doDelete(piatto2);
    }
  }

  @Test
  void testDoUpdate() throws SQLException {
    int recensione = 4;
    bean.setRecensione(recensione);
    dao.doUpdate(bean);
    assertTrue(
        dao.doRetrieveByKey(bean.getEmail(), bean.getPiatto()).getRecensione() == recensione);
  }

  @Test
  void testDoDelete() throws SQLException {
    PiattoBean piatto2 = new PiattoBean("testerAb55", "av", "primo", 0, 0, 0, 0, 0);
    daoP.doSave(piatto2);
    ValutazioneBean bean2 = new ValutazioneBean(consumatore.getEmail(), piatto2.getNome(), 2,
        new Date(System.currentTimeMillis()));
    dao.doSave(bean2);
    dao.doDelete(bean2);
    daoP.doDelete(piatto2);
    assertTrue(dao.doRetrieveByKey(bean2.getEmail(), bean2.getPiatto()) == null);
  }

}
