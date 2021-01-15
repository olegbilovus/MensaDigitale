package business.valutazioni;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import business.consumatore.ConsumatoreBean;
import business.piatti.PiattoBean;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.manager.ConsumatoreDao;
import storage.manager.PiattoDao;
import storage.manager.ValutazioneDao;

public class ValutazioneServletTest {

  private static final ValutazioneDao valutazioneDao = new ValutazioneDao();
  private static final PiattoDao piattoDao = new PiattoDao();
  private static final ConsumatoreDao consumatoreDao = new ConsumatoreDao();

  private static final PiattoBean piattoBean = new PiattoBean("PATATINE", "patate", null, 0, 0, 0,
      0, 0);
  private static final ConsumatoreBean consumatoreBean = new ConsumatoreBean(
      "a.cacciapuoti3@studenti.unisa.it", "Antonio", "Cacciapuoti", 1, "CCCNTNT99999999X", null,
      null, null, null, null, null, null, false, false, 100, 1);

  private static final ValutazioneServlet servlet = new ValutazioneServlet();
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

  @BeforeAll
  public static void beforeAll() throws SQLException {
    Mockito.doReturn("aggiungiValutazione").when(request).getParameter("action");
    piattoDao.doSave(piattoBean);
    consumatoreDao.doSave(consumatoreBean);

  }

  @AfterAll
  public static void afterAll() throws SQLException {
    piattoDao.doDelete(piattoBean);
    consumatoreDao.doDelete(consumatoreBean);
  }

  @Test
  void tc_pm_2_1() {

    String nome = "tartarre di tonno";
    Mockito.doReturn(nome).when(request).getParameter("piatto");

    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void tc_pm_2_2() {

    String nome = "patatine";
    Mockito.doReturn(nome).when(request).getParameter("piatto");

    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void tc_pm_2_3() {

    String nome = "PATATINE";
    Mockito.doReturn(nome).when(request).getParameter("piatto");

    String valutazione = "6";
    Mockito.doReturn(valutazione).when(request).getParameter("valutazione");

    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void tc_pm_2_4() {

    String nome = "PATATINE";
    Mockito.doReturn(nome).when(request).getParameter("piatto");

    String valutazione = "5";
    Mockito.doReturn(valutazione).when(request).getParameter("valutazione");

    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void tc_pm_2_5() throws SQLException {

    String nome = "PATATINE";
    Mockito.doReturn(nome).when(request).getParameter("piatto");

    String valutazione = "5";
    Mockito.doReturn(valutazione).when(request).getParameter("valutazione");

    String email = "a.cacciapuoti3@studenti.unisa.it";
    Mockito.doReturn(email).when(request).getParameter("email");

    assertDoesNotThrow(() -> servlet.doPost(request, response));

    ValutazioneBean tmp = new ValutazioneBean(email, nome, Integer.parseInt(valutazione), null);
    valutazioneDao.doDelete(tmp);

  }

}
