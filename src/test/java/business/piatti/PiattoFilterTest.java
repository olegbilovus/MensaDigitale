package business.piatti;

import business.addetto.AddettoBean;
import business.admin.AdministratorBean;
import business.consumatore.ConsumatoreBean;
import business.utente.LoginFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PiattoFilterTest {

  private static final HttpSession session = Mockito.mock(HttpSession.class);
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final FilterChain chain = Mockito.mock(FilterChain.class);
  private static final PiattoFilter servlet = new PiattoFilter();

  @Test
  public void utenteNull() {

    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(null).when(session).getAttribute("utente");
    assertDoesNotThrow(()->servlet.doFilter(request, response, chain));

  }

  @Test
  public void utenteAddetto() {

    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(new AddettoBean("addetto@gmail.com", "Ad", "Etto", 1)).when(session).getAttribute("utente");
    assertDoesNotThrow(()->servlet.doFilter(request, response, chain));

  }

  @Test
  public void utenteAdmin() {

    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(new AdministratorBean("admin@gmail.com", "Ad", "Min")).when(session).getAttribute("utente");
    assertDoesNotThrow(()->servlet.doFilter(request, response, chain));

  }

  @Test
  public void utenteStudente() {

    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(new ConsumatoreBean("lol@gmail.com", "Cons", "Umatore", 0, "CCCNTN999999999X", null, null, null, null, null, null, null, false, false, 100, 1)).when(session).getAttribute("utente");
    assertDoesNotThrow(()->servlet.doFilter(request, response, chain));

  }

}
