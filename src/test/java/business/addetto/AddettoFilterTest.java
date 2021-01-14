package business.addetto;

import business.consumatore.ConsumatoreBean;
import business.utente.Utente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AddettoFilterTest {

  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final HttpSession session = Mockito.mock(HttpSession.class);
  private static final FilterChain chain = Mockito.mock(FilterChain.class);
  private static final AddettoFilter servlet = new AddettoFilter();

  @BeforeAll
  public static void beforeAll() {
    Mockito.doReturn(session).when(request).getSession();
  }

  @Test
  public void testNonAddetto() {

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
  public void testAddetto() {

    Utente tmp = new AddettoBean("addetto@gmail.com", "Add", "Etto", 1);
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
