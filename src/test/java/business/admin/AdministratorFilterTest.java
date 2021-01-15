package business.admin;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import business.consumatore.ConsumatoreBean;
import business.utente.Utente;
import java.sql.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AdministratorFilterTest {

  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final HttpSession session = Mockito.mock(HttpSession.class);
  private static final FilterChain chain = Mockito.mock(FilterChain.class);
  private static final AdministratorFilter servlet = new AdministratorFilter();

  @BeforeAll
  public static void beforeAll() {
    Mockito.doReturn(session).when(request).getSession();
  }

  @Test
  public void testNonAdmin() {

    Utente tmp =
        new ConsumatoreBean(
            "testerPrenotazione@studenti.unisa.it",
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
    assertDoesNotThrow(() -> servlet.doFilter(request, response, chain));

  }

  @Test
  public void testAdmin() {

    Utente tmp = new AdministratorBean("admin@gmail.com", "Ad", "Min");
    Mockito.doReturn(tmp).when(session).getAttribute("utente");
    assertDoesNotThrow(() -> servlet.doFilter(request, response, chain));

  }

  @Test
  public void testNull() {

    Utente tmp = null;
    Mockito.doReturn(tmp).when(session).getAttribute("utente");
    assertDoesNotThrow(() -> servlet.doFilter(request, response, chain));

  }

}
