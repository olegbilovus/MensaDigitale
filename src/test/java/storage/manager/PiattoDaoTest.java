package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.piatti.PiattoBean;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PiattoDaoTest {

  private final PiattoDao dao = new PiattoDao();
  private final PiattoBean bean = new PiattoBean("tester", "tester", "primo", 0, 0, 0, 0, 0);

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
    assertTrue(dao.doRetrieveByKey(bean.getNome()) != null);
  }

  @Test
  void testDoRetrieveAll() throws SQLException {
    assertTrue(dao.doRetrieveAll().size() != 0);
  }

  @Test
  void testDoSave() throws SQLException {
    PiattoBean bean2 = new PiattoBean("tester2", "tester", "primo", 0, 0, 0, 0, 0);
    try {
      dao.doSave(bean2);
      assertTrue(dao.doRetrieveByKey(bean2.getNome()) != null);
    } finally {
      dao.doDelete(bean2);
    }
  }

  @Test
  void testDoUpdate() throws SQLException {
    String ingredienti = "a, b, c";
    bean.setIngredienti(ingredienti);
    dao.doUpdate(bean);
    assertTrue(dao.doRetrieveByKey(bean.getNome()).getIngredienti().equals(ingredienti));
  }

  @Test
  void testDoDelete() throws SQLException {
    PiattoBean bean2 = new PiattoBean("tester2", "tester", "primo", 0, 0, 0, 0, 0);
    dao.doSave(bean2);
    dao.doDelete(bean2);
    assertTrue(dao.doRetrieveByKey(bean2.getNome()) == null);
  }

}
