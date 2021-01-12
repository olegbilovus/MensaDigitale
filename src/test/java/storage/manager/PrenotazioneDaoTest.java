package storage.manager;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.consumatore.ConsumatoreBean;
import business.prenotazioni.FasciaOrariaBean;
import business.prenotazioni.PrenotazioneBean;
import business.prenotazioni.QRCode;

class PrenotazioneDaoTest {

  private PrenotazioneDao dao = new PrenotazioneDao();
  private FasciaOrariaDao daoF = new FasciaOrariaDao();
  private ConsumatoreDao daoC = new ConsumatoreDao();
  private ConsumatoreBean consumatore = new ConsumatoreBean("testerP8@unisa.it", "tester", "tester",
      1, "tester", new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester",
      "tester", "tester", false, false, 0, 1);
  private FasciaOrariaBean fascia = new FasciaOrariaBean(98, "11:40");
  private PrenotazioneBean<String> bean =
      new PrenotazioneBean<>(new Date(System.currentTimeMillis()), new QRCode("abc"), 2,
          fascia.getId(), consumatore.getEmail());

  @BeforeEach
  public void initEach() throws SQLException {
    daoF.doSave(fascia);
    daoC.doSave(consumatore);
    dao.doSave(bean);

  }

  @AfterEach
  public void destroyEach() throws SQLException {
    dao.doDelete(bean);
    daoF.doDelete(fascia);
    daoC.doDelete(consumatore);
  }

  @Test
  void testDoRetrieveByKey() throws SQLException {
    assertTrue(dao.doRetrieveByKey(bean.getIdentificativo().getIdentificativo()) != null);
  }

  @Test
  void testDoRetrieveAll() throws SQLException {
    assertTrue(dao.doRetrieveAll().size() != 0);
  }

  @Test
  void testDoSave() throws SQLException {
    FasciaOrariaBean bean2 = new FasciaOrariaBean(97, "11:40");
    try {
      dao.doSave(bean2);
      assertTrue(dao.doRetrieveByKey(bean2.getId()) != null);
    } finally {
      dao.doDelete(bean2);
    }
  }

  @Test
  void testDoUpdate() throws SQLException {
    String fascia = "11:00";
    bean.setFascia(fascia);
    dao.doUpdate(bean);
    assertTrue(dao.doRetrieveByKey(bean.getId()).getFascia().equals(fascia));
  }

  @Test
  void testDoDelete() throws SQLException {
    FasciaOrariaBean bean2 = new FasciaOrariaBean(97, "11:40");
    dao.doSave(bean2);
    dao.doDelete(bean2);
    assertTrue(dao.doRetrieveByKey(bean2.getId()) == null);
  }

}
