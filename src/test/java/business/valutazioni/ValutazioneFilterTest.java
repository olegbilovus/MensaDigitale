package business.valutazioni;

import business.admin.AdministratorBean;
import business.consumatore.ConsumatoreBean;
import business.utente.Utente;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValutazioneFilterTest {

  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final HttpSession session = Mockito.mock(HttpSession.class);
  private static final FilterChain chain = Mockito.mock(FilterChain.class);
  private static final ValutazioneFilter servlet = new ValutazioneFilter();

  @BeforeAll
  public static void beforeAll() {
    Mockito.doReturn(session).when(request).getSession();
  }

  @Test
  public void testUtenteNonNull() throws ServletException, IOException {

    Utente tmp =
        new ConsumatoreBean(
            "testerPrenotazione@unisa.it",
            "tester",
            "tester",
            1,
            "tester",
            new Date(System.currentTimeMillis()),
            "tester",
            "tester",
            "tester",
            "tester",
            "tester",
            "tester",
            false,
            false,
            0,
            1);
    Mockito.doReturn(tmp).when(session).getAttribute("utente");
    try {
      servlet.doFilter(request, response, chain);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
}

  @Test
  public void testUtenteNull() throws ServletException, IOException {

    Utente tmp = null;
    Mockito.doReturn(tmp).when(session).getAttribute("utente");
    try {
      servlet.doFilter(request, response, chain);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testUtenteAdmin() throws ServletException, IOException {

    Utente tmp = new AdministratorBean("admin@gmail.com", "Ad", "Min");
    Mockito.doReturn(tmp).when(session).getAttribute("utente");
    try {
      servlet.doFilter(request, response, chain);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }

  }

}
