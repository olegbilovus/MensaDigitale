package business.prenotazioni;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.interfaces.ConsumatoreInterface;
import storage.manager.ConsumatoreDao;

class FilterResetSaleTest {

  private static final HttpSession session = Mockito.mock(HttpSession.class);
  private static final ServletContext ctx = Mockito.mock(ServletContext.class);
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final FilterChain chain = Mockito.mock(FilterChain.class);
  private static ConsumatoreBean tester;
  private final ConsumatoreInterface<ConsumatoreBean> dao = new ConsumatoreDao();
  private final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private final FilterResetSale servlet = new FilterResetSale();

  @BeforeAll
  public static void init() {
    Mockito.doReturn(ctx).when(request).getServletContext();
    Mockito.doReturn(session).when(request).getSession();
  }

  @BeforeEach
  public void initEach() {
    tester = new ConsumatoreBean("testerP10@unisa.it", "tester", "tester", 1, "tester",
        new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester", "tester",
        "tester", false, false, 50, 1);
  }

  @Test
  public void testDoFilter1() throws SQLException, IOException, ServletException {
    try {
      dao.doSave(tester);
      Mockito.doReturn(new Date(System.currentTimeMillis() - 1000000000L)).when(ctx)
          .getAttribute("dataSaleReset");
      Mockito.doReturn(5).when(ctx).getAttribute("numFasceOrarie");
      Mockito.doReturn(tester).when(session).getAttribute("utente");

      servlet.doFilter(request, response, chain);
    } finally {
      dao.doDelete(tester);
    }
  }

  @Test
  public void testDoFilter2() throws SQLException, IOException, ServletException {
    try {
      dao.doSave(tester);
      Mockito.doReturn(new Date(System.currentTimeMillis())).when(ctx)
          .getAttribute("dataSaleReset");
      Mockito.doReturn(5).when(ctx).getAttribute("numFasceOrarie");
      Mockito.doReturn(null).when(session).getAttribute("utente");

      servlet.doFilter(request, response, chain);
    } catch (NullPointerException e) {

    } finally {
      dao.doDelete(tester);
    }
  }

}
