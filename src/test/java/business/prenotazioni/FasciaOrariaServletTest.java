package business.prenotazioni;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.mockito.internal.matchers.Null;
import storage.manager.FasciaOrariaDao;

import static org.junit.jupiter.api.Assertions.*;

class FasciaOrariaServletTest {

  private static final FasciaOrariaBean fascia_test1 = new FasciaOrariaBean(1, "13:00");
  private static final FasciaOrariaBean fascia_test2 = new FasciaOrariaBean(2, "14:20");
  private static final FasciaOrariaDao fasciaOrariaDao = new FasciaOrariaDao();
  private static final HttpSession session = Mockito.mock(HttpSession.class);
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final ServletContext ctx = Mockito.mock(ServletContext.class);
  private static final PrintWriter writer = Mockito.mock(PrintWriter.class);
  private final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private final FasciaOrariaServlet servlet = new FasciaOrariaServlet() {
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
      if (fasciaOrariaDao.doRetrieveByFascia(fascia) != null) {
        fasciaOrariaDao.doDelete(fasciaOrariaDao.doRetrieveByFascia(fascia));
      }
    }
  }

  @Test
  void tc_psr_2_2() throws SQLException {
    String fascia = "9.30";
    try {
      Mockito.doReturn(fascia).when(request).getParameter("fasciaOraria");

      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      if (fasciaOrariaDao.doRetrieveByFascia(fascia) != null) {
        fasciaOrariaDao.doDelete(fasciaOrariaDao.doRetrieveByFascia(fascia));
      }
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

  @Test
  void writerNotNull() throws SQLException, IOException {
    String fascia = "18:00";
    try {
      Mockito.doReturn(fascia).when(request).getParameter("fasciaOraria");
      Mockito.doReturn("inserisci").when(request).getParameter("action");
      Mockito.doReturn(new PrintWriter(System.out)).when(response).getWriter();

      assertDoesNotThrow(() -> servlet.doPost(request, response));
    } finally {
      if (fasciaOrariaDao.doRetrieveByFascia(fascia) != null) {
        fasciaOrariaDao.doDelete(fasciaOrariaDao.doRetrieveByFascia(fascia));
      }
    }
  }

  @Test
  void actionElimina() throws SQLException {
    Mockito.doReturn("elimina").when(request).getParameter("action");
    Mockito.doReturn(ctx).when(request).getServletContext();
    String fascia = "13:40";
    FasciaOrariaBean tmp = new FasciaOrariaBean(99, "13:40");
    try {
      fasciaOrariaDao.doSave(tmp);
      Mockito.doReturn(fascia).when(request).getParameter("fasciaOraria");
      assertDoesNotThrow(() -> servlet.doPost(request, response));
    } finally {
      if (fasciaOrariaDao.doRetrieveByFascia(fascia) != null) {
        fasciaOrariaDao.doDelete(fasciaOrariaDao.doRetrieveByFascia(fascia));
      }
      fasciaOrariaDao.doDelete(tmp);
    }
  }

  @Test
  void actionEliminaWriterNotNull() throws SQLException, IOException {
    Mockito.doReturn("elimina").when(request).getParameter("action");
    Mockito.doReturn(new PrintWriter(System.out)).when(response).getWriter();
    String fascia = "13:40";
    FasciaOrariaBean tmp = new FasciaOrariaBean(99, "13:40");
    try {
      fasciaOrariaDao.doSave(tmp);
      Mockito.doReturn(fascia).when(request).getParameter("fasciaOraria");
      assertDoesNotThrow(() -> servlet.doPost(request, response));
    } finally {
      if (fasciaOrariaDao.doRetrieveByFascia(fascia) != null) {
        fasciaOrariaDao.doDelete(fasciaOrariaDao.doRetrieveByFascia(fascia));
      }
      fasciaOrariaDao.doDelete(tmp);
    }
  }

  @Test
  void actionEliminaFasciaNonPresente() throws SQLException, IOException {
    Mockito.doReturn("elimina").when(request).getParameter("action");
    Mockito.doReturn(new PrintWriter(System.out)).when(response).getWriter();
    String fascia = "13:40";
    try {
      Mockito.doReturn(fascia).when(request).getParameter("fasciaOraria");
      assertThrows(IllegalArgumentException.class ,() -> servlet.doPost(request, response));
    } finally {
      if (fasciaOrariaDao.doRetrieveByFascia(fascia) != null) {
        fasciaOrariaDao.doDelete(fasciaOrariaDao.doRetrieveByFascia(fascia));
      }
    }
  }

  @Test
  void actionNonPrevista() throws SQLException, IOException {
    Mockito.doReturn("hahaha").when(request).getParameter("action");
//    Mockito.doReturn(new PrintWriter(System.out)).when(response).getWriter();
    String fascia = "13:40";
    assertDoesNotThrow(() -> servlet.doPost(request, response));
  }

}
