package business.utente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import business.addetto.AddettoBean;
import business.admin.AdministratorBean;
import business.consumatore.ConsumatoreBean;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.manager.AddettoDao;
import storage.manager.AdministratorDao;
import storage.manager.ConsumatoreDao;

public class LoginServletTest {

  private static final String rightEmail = "a.cacciapuoti3@studenti.unisa.it";
  private static final String badEmail = "yantcaccia@gmail.com";

  private static final ConsumatoreDao consumatoreDao = new ConsumatoreDao();
  private static final AddettoDao addettoDao = new AddettoDao();
  private static final AdministratorDao adminDao = new AdministratorDao();
  private static final ConsumatoreBean testBean =
      new ConsumatoreBean("a.cacciapuoti3@studenti.unisa.it", "Antonio", "Cacciapuoti", 1,
          "CCCNTNT99999999X", null, null, null, null, null, null, null, false, false, 100, 1);
  private static final LoginServlet servlet = new LoginServlet();
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final HttpSession session = Mockito.mock(HttpSession.class);

  @BeforeAll
  static void beforeAll() throws SQLException {
    Mockito.doReturn("loginGoogle").when(request).getParameter("action");
    Mockito.doReturn(session).when(request).getSession();
    consumatoreDao.doSave(testBean);
  }

  @AfterAll
  static void afterAll() throws SQLException {
    consumatoreDao.doDelete(testBean);
  }

  @Test
  public void tc_gu_1_1() {

    Mockito.doReturn(badEmail).when(request).getParameter("email");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  public void tc_gu_1_2() throws SQLException {

    Mockito.doReturn(rightEmail).when(request).getParameter("email");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
    consumatoreDao.doDelete(testBean);

  }

  @Test
  public void testLogout() throws SQLException {

    Mockito.doReturn("logOut").when(request).getParameter("action");
    assertDoesNotThrow(() -> servlet.doPost(request, response));

  }

  @Test
  public void testDocenteEsistente() throws SQLException {
    ConsumatoreBean negro = new ConsumatoreBean("anegro@unisa.it", "Alberto", "Negro", 0, "NGRLBR999999999X", null, null, null, null, null, null, null, false, false, 100, 1);
    consumatoreDao.doSave(negro);
    Mockito.doReturn("anegro@unisa.it").when(request).getParameter("email");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
    consumatoreDao.doDelete(negro);

  }

  @Test
  public void testDocenteNonEsistente() throws SQLException {

    Mockito.doReturn("anegro@unisa.it").when(request).getParameter("email");
    assertDoesNotThrow(() -> servlet.doPost(request, response));

  }

  @Test
  public void testStudenteNonEsistente() throws SQLException {

    Mockito.doReturn("anegro@studenti.unisa.it").when(request).getParameter("email");
    assertDoesNotThrow(() -> servlet.doPost(request, response));

  }

  @Test
  public void testAddettoEsistente() throws SQLException {

    AddettoBean tmp = new AddettoBean("sonounaddetto@gmail.com", "Add", "Etto", 1);
    addettoDao.doSave(tmp);
    Mockito.doReturn(tmp.getEmail()).when(request).getParameter("email");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
    addettoDao.doDelete(tmp);

  }

  @Test
  public void testAdminEsistente() throws SQLException {

    AdministratorBean tmp = new AdministratorBean("sonounadmin@gmail.com", "Ad", "Min");
    adminDao.doSave(tmp);
    Mockito.doReturn(tmp.getEmail()).when(request).getParameter("email");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
    adminDao.doDelete(tmp);

  }

  @Test
  public void nullAction() {
    Mockito.doReturn(null).when(request).getParameter("action");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
  }

  @Test
  public void defaultAction() {
    Mockito.doReturn("notInTheSwitch").when(request).getParameter("action");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
  }

  @Test
  public void nullEmail() {
    Mockito.doReturn(null).when(request).getParameter("email");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
  }

}
