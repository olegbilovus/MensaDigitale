package business.prenotazioni;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
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
import storage.manager.ConsumatoreDao;
import storage.manager.FasciaOrariaDao;
import storage.manager.PrenotazioneDao;

class PrenotazioneServletTest {

  private static ConsumatoreBean tester = new ConsumatoreBean("testerPrenotazione@unisa.it",
      "tester", "tester", 1, "tester", new Date(System.currentTimeMillis()), "tester", "tester",
      "tester", "tester", "tester", "tester", false, false, 0, 1);
  private ConsumatoreDao consumatoreDao = new ConsumatoreDao();
  private FasciaOrariaDao fasciaOrariaDao = new FasciaOrariaDao();
  private static HashMap<Integer, HashMap<Integer, Boolean>> saleDisponibili = new HashMap<>();
  private static HashMap<Integer, Integer> capienzaSale = new HashMap<>(5);
  private PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
  private static HttpSession session = Mockito.mock(HttpSession.class);
  private static HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static ServletContext ctx = Mockito.mock(ServletContext.class);
  private PrenotazioneServlet servlet = new PrenotazioneServlet() {
    public ServletContext getServletContext() {
      return ctx;
    }
  };

  @BeforeAll
  public static void init() {
    Mockito.doReturn(tester).when(session).getAttribute("consumatore");
    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn("invia").when(request).getParameter("action");
    Mockito.doReturn(100).when(ctx).getAttribute("numFasceOrarie");
    
    for (int i = 1; i <= 5; i++) {
      HashMap<Integer, Boolean> fasceOrarie = new HashMap<>(5);
      for (int j = 1; j <= 5; j++) {
        fasceOrarie.put(j, true);
      }
      saleDisponibili.put(i, fasceOrarie);
    }
    Mockito.doReturn(saleDisponibili).when(ctx).getAttribute("saleDisponibili");
    
    capienzaSale.put(1, 300);
    capienzaSale.put(2, 152);
    capienzaSale.put(3, 106);
    capienzaSale.put(4, 40);
    capienzaSale.put(5, 15);
    Mockito.doReturn(capienzaSale).when(ctx).getAttribute("capienzaSale");
  }

  @BeforeEach
  public void initEach() throws SQLException {
    consumatoreDao.doSave(tester);
  }

  @AfterEach
  public void destroyEach() throws SQLException {
    consumatoreDao.doDelete(tester);
  }

  @Test
  void tc_psr_1_1() throws ServletException, IOException, SQLException {
    String fasciaOraria = "101";
    FasciaOrariaBean fasciaOrariaBean =
        new FasciaOrariaBean(Integer.parseInt(fasciaOraria), "11:30");
    try {
      fasciaOrariaDao.doSave(fasciaOrariaBean);
      Mockito.doReturn(fasciaOraria).when(request).getParameter("fasciaOraria");

      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

    } finally {
      fasciaOrariaDao.doDelete(fasciaOrariaBean);
    }
  }
  
  @Test
  void tc_psr_1_2() throws ServletException, IOException, SQLException {
    String fasciaOraria = "99";
    FasciaOrariaBean fasciaOrariaBean =
        new FasciaOrariaBean(Integer.parseInt(fasciaOraria), "11:30");
    try {
      fasciaOrariaDao.doSave(fasciaOrariaBean);
      Mockito.doReturn(fasciaOraria).when(request).getParameter("fasciaOraria");
      Mockito.doReturn("7").when(request).getParameter("sala");

      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

    } finally {
      fasciaOrariaDao.doDelete(fasciaOrariaBean);
    }
  }

  @Test
  void tc_psr_1_3() throws ServletException, IOException, SQLException {
    String fasciaOraria = "99";
    FasciaOrariaBean fasciaOrariaBean =
        new FasciaOrariaBean(Integer.parseInt(fasciaOraria), "11:30");
    try {
      fasciaOrariaDao.doSave(fasciaOrariaBean);
      Mockito.doReturn(fasciaOraria).when(request).getParameter("fasciaOraria");
      Mockito.doReturn("3").when(request).getParameter("sala");

      servlet.doPost(request, response);

      assertTrue(prenotazioneDao.doRetrieveByDateAndFascia(new Date(System.currentTimeMillis()),
          tester.getEmail(), Integer.parseInt(fasciaOraria)) != null);
    } finally {
      fasciaOrariaDao.doDelete(fasciaOrariaBean);
    }
  }

}
