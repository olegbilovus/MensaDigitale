package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.admin.AdministratorBean;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdministratorDaoTest {

  private final AdministratorDao dao = new AdministratorDao();
  private final AdministratorBean bean =
      new AdministratorBean("testerA@unisa.it", "tester", "tester");

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
  void testDoUpdate() throws SQLException {
    String nome = "tester3A";
    bean.setNome(nome);
    dao.doUpdate(bean);
    assertTrue(dao.doRetrieveByKey(bean.getEmail()).getNome().equals(nome));
  }

  @Test
  void testDoDelete() throws SQLException {
    AdministratorBean bean2 = new AdministratorBean("testerA2@unisa.it", "tester", "tester");
    dao.doSave(bean2);
    dao.doDelete(bean2);
    assertTrue(dao.doRetrieveByKey(bean2.getEmail()) == null);
  }

}
