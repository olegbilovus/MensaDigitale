package business.prenotazioni;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.manager.FasciaOrariaDao;

class FasciaOrariaServletTest {
  private static FasciaOrariaBean fascia_test1 = new FasciaOrariaBean(1, "13:00");
  private static FasciaOrariaBean fascia_test2 = new FasciaOrariaBean(2, "14:20");
  private static FasciaOrariaDao fasciaOrariaDao = new FasciaOrariaDao();
  private static HttpSession session = Mockito.mock(HttpSession.class);
  private static HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static ServletContext ctx = Mockito.mock(ServletContext.class);
  private FasciaOrariaServlet servlet = new FasciaOrariaServlet() {
    public ServletContext getServletContext() {
      return ctx;
    }
  };

  @BeforeAll
  public static void init() {
    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn("inserisci").when(request).getParameter("action");
    Mockito.doReturn(20).when(ctx).getAttribute("numFasceOrarie");

  }

  @BeforeEach
  public void initFasce() throws SQLException {
    fasciaOrariaDao.doSave(fascia_test1);
    fasciaOrariaDao.doSave(fascia_test2);
  }

  @AfterEach
  public void deleteFasce() throws SQLException {
    fasciaOrariaDao.doDelete(fascia_test1);
    fasciaOrariaDao.doDelete(fascia_test2);
  }

  @Test
  void tc_psr_2_1() throws SQLException {
    String fascia = "13:20";
    try {
      Mockito.doReturn(fascia).when(request).getParameter("fasciaOraria");

      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
    }
  }

  @Test
  void tc_psr_2_2() throws SQLException {
    String fascia = "9.30";
    try {
      Mockito.doReturn(fascia).when(request).getParameter("fasciaOraria");

      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
    }
  }

  @Test
  void tc_psr_2_3() throws SQLException, ServletException, IOException {
    String fascia = "13:40";
    try {
      Mockito.doReturn(fascia).when(request).getParameter("fasciaOraria");

      servlet.doPost(request, response);
      assertTrue(fasciaOrariaDao.doRetrieveByFascia(fascia) != null);
    } finally {
      fasciaOrariaDao.doDelete(fasciaOrariaDao.doRetrieveByFascia(fascia));
    }
  }

}
