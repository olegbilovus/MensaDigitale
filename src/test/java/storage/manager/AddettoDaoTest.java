package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.addetto.AddettoBean;

class AddettoDaoTest {

  private AddettoDao dao = new AddettoDao();
  private AddettoBean bean = new AddettoBean("tester@unisa.it", "tester", "tester", 1);

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
    AddettoBean bean2 = new AddettoBean("tester2@unisa.it", "tester", "tester", 1);
    try {
      dao.doSave(bean2);
      assertTrue(dao.doRetrieveByKey(bean2.getEmail()) != null);
    } finally {
      dao.doDelete(bean2);
    }
  }

  @Test
  void testDoUpdate() throws SQLException {
    int permessi = 2;
    bean.setLvlPermessi(permessi);
    dao.doUpdate(bean);
    assertTrue(dao.doRetrieveByKey(bean.getEmail()).getLvlPermessi() == permessi);
  }

  @Test
  void testDoDelete() throws SQLException {
    AddettoBean bean2 = new AddettoBean("tester2@unisa.it", "tester", "tester", 1);
    dao.doSave(bean2);
    dao.doDelete(bean2);
    assertTrue(dao.doRetrieveByKey(bean2.getEmail()) == null);
  }

}
