package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.prenotazioni.FasciaOrariaBean;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FasciaOrariaDaoTest {

  private final FasciaOrariaDao dao = new FasciaOrariaDao();
  private final FasciaOrariaBean bean = new FasciaOrariaBean(98, "11:40");

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
    assertTrue(dao.doRetrieveByKey(bean.getId()) != null);
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
