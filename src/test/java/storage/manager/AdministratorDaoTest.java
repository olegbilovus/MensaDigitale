package storage.manager;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.addetto.AddettoBean;
import business.admin.AdministratorBean;

class AdministratorDaoTest {
  
  private AdministratorDao dao = new AdministratorDao();
  private AdministratorBean bean = new AdministratorBean("testerA@unisa.it", "tester", "tester");
  
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
    AdministratorBean bean2 = new AdministratorBean("testerA2@unisa.it", "tester", "tester");
    try {
      dao.doSave(bean2);
      assertTrue(dao.doRetrieveByKey(bean2.getEmail()) != null);
    } finally {
      dao.doDelete(bean2);
    }
  }

  @Test
  void testDoUpdate() {
    fail("Not yet implemented");
  }

  @Test
  void testDoDelete() {
    fail("Not yet implemented");
  }

}
