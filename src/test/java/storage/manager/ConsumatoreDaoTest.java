package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.consumatore.ConsumatoreBean;
import business.prenotazioni.FasciaOrariaBean;
import business.prenotazioni.PrenotazioneBean;
import business.prenotazioni.QRCode;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsumatoreDaoTest {

  private ConsumatoreDao dao = new ConsumatoreDao();
  private ConsumatoreBean bean = new ConsumatoreBean("testerP@unisa.it", "tester", "tester", 1,
      "tester", new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester",
      "tester", "tester", false, false, 0, 1);

  @BeforeEach
  public void initEach() throws SQLException {
    dao.doSave(bean);
  }

  @AfterEach
  public void destroyEach() throws SQLException {
    dao.doDelete(bean);
  }

  @Test
  void testDoRetrieveByKey() throws SQLException {
    assertTrue(dao.doRetrieveByKey(bean.getEmail()) != null);
  }

  @Test
  void testDoRetrieveAll() throws SQLException {
    assertTrue(dao.doRetrieveAll().size() != 0);
  }

  @Test
  void testDoSave() throws SQLException {
    ConsumatoreBean bean2 = new ConsumatoreBean("testerP2@unisa.it", "tester", "tester", 1,
        "tester", new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester",
        "tester", "tester", false, false, 0, 1);
    try {
      dao.doSave(bean2);
      assertTrue(dao.doRetrieveByKey(bean2.getEmail()) != null);
    } finally {
      dao.doDelete(bean2);
    }
  }

  @Test
  void testDoUpdate() throws SQLException {
    int statoPervizi = 3;
    bean.setStatoServizi(statoPervizi);;
    dao.doUpdate(bean);
    assertTrue(dao.doRetrieveByKey(bean.getEmail()).getStatoServizi() == statoPervizi);
  }

  @Test
  void testDoDelete() throws SQLException {
    ConsumatoreBean bean2 = new ConsumatoreBean("testerP2@unisa.it", "tester", "tester", 1,
        "tester", new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester",
        "tester", "tester", false, false, 0, 1);
    dao.doSave(bean2);
    dao.doDelete(bean2);
    assertTrue(dao.doRetrieveByKey(bean2.getEmail()) == null);
  }

  @Test
  void testDdoRetrieveForTracciamento() throws SQLException {
    ConsumatoreBean bean2 = new ConsumatoreBean("testerP2@unisa.it", "tester", "tester", 1,
        "tester2", new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester",
        "tester", "tester", false, false, 0, 1);
    FasciaOrariaBean fascia = new FasciaOrariaBean(98, "11:40");
    PrenotazioneBean<String> prenotazione =
        new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
            new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
            bean2.getEmail());
    prenotazione.setEntrato(true);
    PrenotazioneBean<String> prenotazione2 =
        new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
            new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
            bean.getEmail());
    prenotazione2.setEntrato(true);
    PrenotazioneBean<String> prenotazione3 =
        new PrenotazioneBean<>(new Date(System.currentTimeMillis() - 103680000000L),
            new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
            bean2.getEmail());
    prenotazione3.setEntrato(true);
    PrenotazioneBean<String> prenotazione4 =
        new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
            new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
            bean.getEmail());
    PrenotazioneBean<String> prenotazione5 =
        new PrenotazioneBean<>(new Date(System.currentTimeMillis() - 103680000000L),
            new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
            bean.getEmail());
    prenotazione5.setEntrato(true);
    FasciaOrariaDao daoF = new FasciaOrariaDao();
    PrenotazioneDao daoP = new PrenotazioneDao();
    try {
      dao.doSave(bean2);
      daoF.doSave(fascia);
      daoP.doSave(prenotazione);
      daoP.doSave(prenotazione2);
      daoP.doSave(prenotazione3);
      daoP.doSave(prenotazione4);
      daoP.doSave(prenotazione5);

      assertTrue(dao.doRetrieveForTracciamento(bean.getCodiceFiscale(),
          new Date(System.currentTimeMillis()).toString()).size() != 0);

    } finally {
      dao.doDelete(bean2);
      daoP.doDelete(prenotazione);
      daoP.doDelete(prenotazione2);
      daoP.doDelete(prenotazione3);
      daoP.doDelete(prenotazione4);
      daoP.doDelete(prenotazione5);
      daoF.doDelete(fascia);

    }


  }


}
