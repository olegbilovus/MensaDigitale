package business.prenotazioni;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import business.consumatore.ConsumatoreBean;

class PrenotazioneFilterTest {

  private static ConsumatoreBean tester = new ConsumatoreBean("testerPrenotazione@unisa.it",
      "tester", "tester", 1, "tester", new Date(System.currentTimeMillis()), "tester", "tester",
      "tester", "tester", "tester", "tester", false, false, 0, 1);
  private PrenotazioneBean<String> prenotazione =
      new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
          new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, 99, tester.getEmail());
  private static HttpSession session = Mockito.mock(HttpSession.class);
  private static HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private PrenotazioneFilter servlet = new PrenotazioneFilter();
  private static FilterChain chain = Mockito.mock(FilterChain.class);

  @BeforeAll
  public static void init() throws IOException, ServletException {
    Mockito.doReturn(session).when(request).getSession();
    Mockito.doNothing().when(chain).doFilter(Mockito.any(), Mockito.any());
  }

  @Test
  void testDoFilter1() throws IOException, ServletException {
    ArgumentCaptor<Boolean> error = ArgumentCaptor.forClass(Boolean.class);

    Mockito.doReturn(tester).when(session).getAttribute("utente");
    Mockito.doReturn("POST").when(request).getMethod();
    Mockito.doNothing().when(request).setAttribute(Mockito.eq("error"), error.capture());

    servlet.doFilter(request, response, chain);

    assertThrows(MockitoException.class, () -> error.getValue());

  }

  @Test
  void testDoFilter2() throws IOException, ServletException {
    ArgumentCaptor<Boolean> error = ArgumentCaptor.forClass(Boolean.class);
    Mockito.doReturn(tester).when(session).getAttribute("utente");
    Mockito.doReturn("GET").when(request).getMethod();
    Mockito.doReturn(prenotazione).when(session).getAttribute("prenotazione");
    Mockito.doNothing().when(request).setAttribute(Mockito.eq("error"), error.capture());

    servlet.doFilter(request, response, chain);

    assertThrows(MockitoException.class, () -> error.getValue());

  }

  @Test
  void testDoFilter3() throws IOException, ServletException {
    ArgumentCaptor<Boolean> error = ArgumentCaptor.forClass(Boolean.class);
    try {
      Mockito.doReturn(tester).when(session).getAttribute("utente");
      Mockito.doReturn("GET").when(request).getMethod();
      Mockito.doReturn(null).when(session).getAttribute("prenotazione");
      Mockito.doNothing().when(request).setAttribute(Mockito.eq("error"), error.capture());

      servlet.doFilter(request, response, chain);
    } catch (NullPointerException e) {
      assertTrue(error.getValue());
    }
  }

  @Test
  void testDoFilter4() throws IOException, ServletException {
    ArgumentCaptor<Boolean> error = ArgumentCaptor.forClass(Boolean.class);
    try {
      Mockito.doReturn(null).when(session).getAttribute("utente");
      Mockito.doReturn("GET").when(request).getMethod();
      Mockito.doReturn(null).when(session).getAttribute("prenotazione");
      Mockito.doNothing().when(request).setAttribute(Mockito.eq("error"), error.capture());

      servlet.doFilter(request, response, chain);
    } catch (NullPointerException e) {
      assertTrue(error.getValue());
    }
  }

  @Test
  void testDoFilter5() throws IOException, ServletException {
    ArgumentCaptor<Boolean> error = ArgumentCaptor.forClass(Boolean.class);
    try {
      Mockito.doReturn(tester).when(session).getAttribute("utente");
      Mockito.doReturn("POST").when(request).getMethod();
      Mockito.doReturn(prenotazione).when(session).getAttribute("prenotazione");
      Mockito.doNothing().when(request).setAttribute(Mockito.eq("error"), error.capture());

      servlet.doFilter(request, response, chain);
    } catch (NullPointerException e) {
      assertTrue(error.getValue());
    }
  }

}
