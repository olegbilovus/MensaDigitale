package business.piatti;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PiattoServletTest {
  
  private PiattoServlet servlet = new PiattoServlet();
  private static HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  
  @BeforeAll
  public static void beforeAll() {
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
  }

  @Test
  void tc_pm_1_1() {
    String ingredienti = "abc";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }
  
  @Test
  void tc_pm_1_2() {
    String ingredienti = "latte,farina";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }
  
  @Test
  void tc_pm_1_3() {
    String ingredienti = "LATTE,FARINA";
    String calorie = "-1";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    Mockito.doReturn(calorie).when(request).getParameter("calorie");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }
  
  @Test
  void tc_pm_1_4() {
    String ingredienti = "LATTE,FARINA";
    String calorie = "1";
    String proteine = "-1";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    Mockito.doReturn(calorie).when(request).getParameter("calorie");
    Mockito.doReturn(proteine).when(request).getParameter("proteine");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }
  
  @Test
  void tc_pm_1_5() {
    String ingredienti = "LATTE,FARINA";
    String calorie = "1";
    String proteine = "1";
    String grassi = "-1";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    Mockito.doReturn(calorie).when(request).getParameter("calorie");
    Mockito.doReturn(proteine).when(request).getParameter("proteine");
    Mockito.doReturn(grassi).when(request).getParameter("grassi");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }
  
  @Test
  void tc_pm_1_6() {
    String ingredienti = "LATTE,FARINA";
    String calorie = "1";
    String proteine = "1";
    String grassi = "1";
    String sodio = "-1";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    Mockito.doReturn(calorie).when(request).getParameter("calorie");
    Mockito.doReturn(proteine).when(request).getParameter("proteine");
    Mockito.doReturn(grassi).when(request).getParameter("grassi");
    Mockito.doReturn(sodio).when(request).getParameter("sodio");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }
  
  @Test
  void tc_pm_1_7() {
    String ingredienti = "LATTE,FARINA";
    String calorie = "1";
    String proteine = "1";
    String grassi = "1";
    String sodio = "1";
    String carboidrati = "-1";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    Mockito.doReturn(calorie).when(request).getParameter("calorie");
    Mockito.doReturn(proteine).when(request).getParameter("proteine");
    Mockito.doReturn(grassi).when(request).getParameter("grassi");
    Mockito.doReturn(sodio).when(request).getParameter("sodio");
    Mockito.doReturn(carboidrati).when(request).getParameter("carboidrati");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }
  
  @Test
  void tc_pm_1_8() {
    String nome = "Testing";
    String ingredienti = "LATTE,FARINA";
    String calorie = "1";
    String proteine = "1";
    String grassi = "1";
    String sodio = "1";
    String carboidrati = "1";
    Mockito.doReturn(nome).when(request).getParameter("nomePiatto");
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    Mockito.doReturn(calorie).when(request).getParameter("calorie");
    Mockito.doReturn(proteine).when(request).getParameter("proteine");
    Mockito.doReturn(grassi).when(request).getParameter("grassi");
    Mockito.doReturn(sodio).when(request).getParameter("sodio");
    Mockito.doReturn(carboidrati).when(request).getParameter("carboidrati");
    assertDoesNotThrow(() -> servlet.doPost(request, response));
    
  }

}

















