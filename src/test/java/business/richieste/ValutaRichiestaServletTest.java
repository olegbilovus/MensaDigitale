package business.richieste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import business.addetto.AddettoBean;
import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.manager.AddettoDao;
import storage.manager.ConsumatoreDao;
import storage.manager.RichiestaDao;

class ValutaRichiestaServletTest {
  private static RichiestaDao richiestaDao = new RichiestaDao();
  private static ServletContext ctx = Mockito.mock(ServletContext.class);
  private static HttpSession session = Mockito.mock(HttpSession.class);
  private static HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static AddettoBean addetto =
      new AddettoBean("addetto@unisa.it", "Addetto", "Lavorante", 1);
  private static RichiestaBean richiesta = new RichiestaBean(1, "m.rossi999@studenti.unisa.it");
  private static ConsumatoreBean studente = new ConsumatoreBean("m.rossi999@studenti.unisa.it",
      "Mario", "Rossi", 1, "", null, "", "", "", "", "", "", false, false, 0, 0);
  private static AddettoDao addettoDao = new AddettoDao();
  private static ConsumatoreDao consumatoreDao = new ConsumatoreDao();

  private ValutaRichiestaServlet servlet = new ValutaRichiestaServlet() {
    public ServletContext getServletContext() {
      return ctx;
    }
  };

  @BeforeAll
  public static void init() throws SQLException {
    addettoDao.doSave(addetto);
    consumatoreDao.doSave(studente);
    richiestaDao.doSave(richiesta);

    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(addetto).when(session).getAttribute("utente");
  }

  @AfterAll
  public static void clean() throws SQLException {
    addettoDao.doDelete(addetto);
    consumatoreDao.doDelete(studente);
    richiestaDao.doDelete(richiesta);
  }

  @Test
  void tc_ars_2_1() {
    /*
     * Valore esito non valido
     */
    int esito = 3;
    Mockito.doReturn(String.valueOf(esito)).when(request).getParameter("esito");
    Mockito.doReturn(String.valueOf(richiesta.getId())).when(request).getParameter("idRichiesta");

    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }

  @Test
  void tc_ars_2_2() throws SQLException, ServletException, IOException {
    /*
     * Valore esito ok
     */
    int esito = 1;
    Mockito.doReturn(String.valueOf(esito)).when(request).getParameter("esito");
    Mockito.doReturn(String.valueOf(richiesta.getId())).when(request).getParameter("idRichiesta");

    servlet.doPost(request, response);
    assertEquals(esito, richiestaDao.doRetrieveByKey(richiesta.getId()).getEsito());
  }

  @Test
  void coverage_1() throws SQLException, ServletException, IOException {
    /*
     * Valore esito ok
     */
    int esito = 2;
    Mockito.doReturn(String.valueOf(esito)).when(request).getParameter("esito");
    Mockito.doReturn(String.valueOf(richiesta.getId())).when(request).getParameter("idRichiesta");

    servlet.doPost(request, response);
    assertEquals(esito, richiestaDao.doRetrieveByKey(richiesta.getId()).getEsito());
  }

}
