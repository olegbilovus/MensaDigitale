package business.piatti;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.manager.PiattoDao;

public class MenuServletTest {

  private static final PiattoDao dao = new PiattoDao();
  private static final MenuServlet servlet = new MenuServlet();
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final RequestDispatcher reqdisp = Mockito.mock(RequestDispatcher.class);

  @BeforeAll
  public static void beforeAll() {
    Mockito.doReturn(reqdisp).when(request).getRequestDispatcher("index.jsp");
  }

  @Test
  void testAggiungiMenu() throws SQLException {
    /*--Variabili utili--*/
    String nome = "Testing";
    String portata = "primo";
    /*--action--*/
    Mockito.doReturn("aggiungiMenu").when(request).getParameter("action");
    /*--preparo il database--*/
    PiattoBean tmp = new PiattoBean(nome, "test", portata, 0, 0, 0, 0, 0);
    dao.doSave(tmp);
    /*--prpearo request--*/
    String[] piatti = new String[1];
    piatti[0] = nome;
    Mockito.doReturn(piatti).when(request).getParameterValues("piatti");
    try {
      assertDoesNotThrow(() -> servlet.doPost(request, response));
    } finally {
      dao.doDelete(tmp);
    }
  }

  @Test
  void testModificaMenu() throws SQLException {
    /*--Variabili utili--*/
    String nome1 = "Testing";
    String nome2 = "Testing2";
    String nome3 = "Testing3";
    String portata1 = "primo";
    String portata2 = "secondo";
    String portata3 = "contorno";
    /*--action--*/
    Mockito.doReturn("modificaMenu").when(request).getParameter("action");
    /*--preparo il database--*/
    PiattoBean tmp1 = new PiattoBean(nome1, "test", portata1, 0, 0, 0, 0, 0);
    PiattoBean tmp2 = new PiattoBean(nome2, "test", portata2, 0, 0, 0, 0, 0);
    PiattoBean tmp3 = new PiattoBean(nome3, "test", portata3, 0, 0, 0, 0, 0);
    dao.doSave(tmp1);
    dao.doSave(tmp2);
    dao.doSave(tmp3);
    /*--prpearo request--*/
    String[] piattiDaRimuovere = new String[3];
    piattiDaRimuovere[0] = nome1;
    piattiDaRimuovere[1] = nome2;
    piattiDaRimuovere[2] = nome3;
    String[] piattiDaAggiungere = new String[3];
    piattiDaAggiungere[0] = nome1;
    piattiDaAggiungere[1] = nome2;
    piattiDaAggiungere[2] = nome3;
    Mockito.doReturn(piattiDaRimuovere).when(request).getParameterValues("piattiDaRimuovere");
    Mockito.doReturn(piattiDaAggiungere).when(request).getParameterValues("piattiDaAggiungere");
    try {
      assertDoesNotThrow(() -> servlet.doPost(request, response));
    } finally {
      dao.doDelete(tmp1);
      dao.doDelete(tmp2);
      dao.doDelete(tmp3);
    }
  }

  @Test
  void testCancellaMenu() {
    Mockito.doReturn("cancellaMenu").when(request).getParameter("action");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
  }

  @Test
  void testGetMenu() throws SQLException {
    testAggiungiMenu();
    Mockito.doReturn("getMenu").when(request).getParameter("action");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
    testCancellaMenu();
  }

  @Test
  void testNullAction() {
    Mockito.doReturn(null).when(request).getParameter("action");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
  }

}
