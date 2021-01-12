package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;
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
      new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
          new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
          consumatore.getEmail());

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
    PrenotazioneBean<String> bean2 = new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
        new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
        consumatore.getEmail());
    try {
      dao.doSave(bean2);
      assertTrue(dao.doRetrieveByKey(bean2.getIdentificativo().getIdentificativo()) != null);
    } finally {
      dao.doDelete(bean2);
    }
  }

  @Test
  void testDoUpdate() throws SQLException {
    Long data = System.currentTimeMillis() + 10368000000L;
    bean.setDataPrenotazione(new Date(data));
    dao.doUpdate(bean);
    assertTrue(dao.doRetrieveByKey(bean.getIdentificativo().getIdentificativo())
        .getDataPrenotazione().toString().equals(new Date(data).toString()));
  }

  @Test
  void testDoDelete() throws SQLException {
    PrenotazioneBean<String> bean2 = new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
        new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
        consumatore.getEmail());
    dao.doSave(bean2);
    dao.doDelete(bean2);
    assertTrue(dao.doRetrieveByKey(bean2.getIdentificativo().getIdentificativo()) == null);
  }

  @Test
  void testDoRetrieveByDateAndFascia1() throws SQLException {
    assertTrue(dao.doRetrieveByDateAndFascia(bean.getDataPrenotazione(), bean.getEmail(),
        bean.getFasciaOraria()) != null);
  }
  
  @Test
  void testDoRetrieveByDateAndFascia2() throws SQLException {
    assertTrue(dao.doRetrieveByDateAndFascia(bean.getDataPrenotazione(), "",
        bean.getFasciaOraria()) == null);
  }

  @Test
  void testDoRetrieveByDateAndMail1() throws SQLException {
    assertTrue(dao.doRetrieveByDateAndMail(bean.getDataPrenotazione(), bean.getEmail()) != null);
  }
  
  @Test
  void testDoRetrieveByDateAndMail2() throws SQLException {
    assertTrue(dao.doRetrieveByDateAndMail(bean.getDataPrenotazione(), "") == null);
  }

  @Test
  void testDoRetrieveByDateSalaFascia() throws SQLException {
    assertTrue(dao.doRetrieveByDateSalaFascia(bean.getDataPrenotazione(), bean.getSala(),
        bean.getFasciaOraria()) != null);
  }


}
