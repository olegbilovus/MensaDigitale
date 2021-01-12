package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Date;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.consumatore.ConsumatoreBean;

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

}
