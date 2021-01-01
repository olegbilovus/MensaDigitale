package business.utente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServletTest {

  private static final String rightEmail = "a.cacciapuoti3@studenti.unisa.it";
  private static final String badEmail = "yantcaccia@gmail.com";

  private static final LoginServlet servlet = new LoginServlet();
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final HttpSession session = Mockito.mock(HttpSession.class);

  @BeforeAll
  static void beforeAll(){
    Mockito.doReturn("loginGoogle").when(request).getParameter("action");
    Mockito.doReturn(session).when(request).getSession();
  }

  @Test
  public void tc_gu_1_1() {

    Mockito.doReturn(badEmail).when(request).getParameter("email");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  public void tc_gu_1_2() {

    Mockito.doReturn(rightEmail).when(request).getParameter("email");
    assertDoesNotThrow(() -> servlet.doPost(request, response));

  }

}
