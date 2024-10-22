package business.utente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import business.admin.AdministratorBean;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginFilterTest {

  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final FilterChain chain = Mockito.mock(FilterChain.class);
  private static final LoginFilter servlet = new LoginFilter();
  private static final HttpSession session = Mockito.mock(HttpSession.class);

  @Test
  public void testOk() {
    Mockito.doReturn("loginGoogle").when(request).getParameter("action");
    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(new AdministratorBean("admin@gmail.com", "Ad", "Min")).when(session)
        .getAttribute("utente");
    assertDoesNotThrow(() -> servlet.doFilter(request, response, chain));
  }

  @Test
  public void testNullAction() {
    Mockito.doReturn(null).when(request).getParameter("action");
    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(new AdministratorBean("admin@gmail.com", "Ad", "Min")).when(session)
        .getAttribute("utente");
    assertDoesNotThrow(() -> servlet.doFilter(request, response, chain));
  }

  @Test
  public void testNullUser() {
    Mockito.doReturn("loginGoogle").when(request).getParameter("action");
    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(null).when(session).getAttribute("utente");
    assertDoesNotThrow(() -> servlet.doFilter(request, response, chain));
  }

  @Test
  public void testLogOut() {
    Mockito.doReturn("logOut").when(request).getParameter("action");
    Mockito.doReturn(session).when(request).getSession();
    Mockito.doReturn(new AdministratorBean("admin@gmail.com", "Ad", "Min")).when(session)
        .getAttribute("utente");
    assertDoesNotThrow(() -> servlet.doFilter(request, response, chain));
  }


}
