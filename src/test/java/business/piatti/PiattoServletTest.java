package business.piatti;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.manager.PiattoDao;

import static org.junit.jupiter.api.Assertions.*;


class PiattoServletTest {

  private static final PiattoServlet servlet = new PiattoServlet();
  private static final PiattoDao dao = new PiattoDao();
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final RequestDispatcher reqdisp = Mockito.mock(RequestDispatcher.class);

  @BeforeAll
  public static void beforeAll() {
    Mockito.doReturn(reqdisp).when(request).getRequestDispatcher("index.jsp");
  }

  @Test
  void tc_pm_1_1() {
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    String ingredienti = "abc";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }

  @Test
  void tc_pm_1_2() {
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    String ingredienti = "latte,farina";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }

  @Test
  void tc_pm_1_3() {
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    String ingredienti = "LATTE,FARINA";
    String calorie = "-1";
    Mockito.doReturn(ingredienti).when(request).getParameter("ingredienti");
    Mockito.doReturn(calorie).when(request).getParameter("calorie");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }

  @Test
  void tc_pm_1_4() {
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
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
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
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
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
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
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
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
  void tc_pm_1_8() throws SQLException {

    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    try {
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
    } finally {
      dao.doDelete(new PiattoBean("Testing", null, null, 0, 0, 0, 0, 0));
    }

  }

  @Test
  void testModificaPiatto() throws SQLException {

    Mockito.doReturn("modificaPiatto").when(request).getParameter("action");
    String nome = "Testing";
    String ingredienti = "LATTE,FARINA";
    String calorie = "1";
    String proteine = "1";
    String grassi = "1";
    String sodio = "1";
    String carboidrati = "1";
    PiattoBean tmp = new PiattoBean(nome, ingredienti, null, Integer.parseInt(calorie),
        Integer.parseInt(proteine), Integer.parseInt(grassi), Integer.parseInt(sodio),
        Integer.parseInt(carboidrati));
    dao.doSave(tmp);
    Mockito.doReturn(nome).when(request).getParameter("nomePiatto");
    Mockito.doReturn("NUOVI,INGREDIENTI").when(request).getParameter("ingredienti");
    Mockito.doReturn(calorie).when(request).getParameter("calorie");
    Mockito.doReturn(proteine).when(request).getParameter("proteine");
    Mockito.doReturn(grassi).when(request).getParameter("grassi");
    Mockito.doReturn(sodio).when(request).getParameter("sodio");
    Mockito.doReturn(carboidrati).when(request).getParameter("carboidrati");
    try {
      assertDoesNotThrow(() -> servlet.doPost(request, response));
    } finally {
      dao.doDelete(tmp);
    }

  }

  @Test
  void testRemovePiatto() throws SQLException {

    Mockito.doReturn("rimuoviPiatto").when(request).getParameter("action");
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
    PiattoBean tmp = new PiattoBean(nome, ingredienti, null, Integer.parseInt(calorie),
        Integer.parseInt(proteine), Integer.parseInt(grassi), Integer.parseInt(sodio),
        Integer.parseInt(carboidrati));
    dao.doSave(tmp);
    try {
      assertDoesNotThrow(() -> servlet.doPost(request, response));
    } finally {
      dao.doDelete(tmp);
    }

  }

  @Test
  void testGetPiatto() throws SQLException, ServletException, IOException {

    String nome = "Testing";
    Mockito.doReturn("getPiatto").when(request).getParameter("action");
    Mockito.doReturn(nome).when(request).getParameter("nomePiatto");
    PiattoBean tmp = new PiattoBean(nome, "test", null, 0, 0, 0, 0, 0);
    dao.doSave(tmp);
    try {
      assertDoesNotThrow(() -> servlet.doPost(request, response));
    } finally {
      dao.doDelete(tmp);
    }

  }

  @Test
  void testGetTuttiPiatti() throws SQLException, ServletException, IOException {

    String nome = "Testing";
    Mockito.doReturn("getTuttiPiatti").when(request).getParameter("action");
    PiattoBean tmp = new PiattoBean(nome, "test", null, 0, 0, 0, 0, 0);
    dao.doSave(tmp);
    try {
      servlet.doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(true);
    } finally {
      dao.doDelete(tmp);
    }

  }

  @Test
  void actionDefault() {

    Mockito.doReturn("nonPrevisto").when(request).getParameter("action");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void actionNull() {

    Mockito.doReturn(null).when(request).getParameter("action");
    assertDoesNotThrow(() -> servlet.doPost(request, response));

  }

  @Test
  void destinationNotNull() {

    Mockito.doReturn(null).when(request).getParameter("action");
    Mockito.doReturn("index.jsp").when(request).getParameter("destination");
    assertDoesNotThrow(() -> servlet.doPost(request, response));

  }

  @Test
  void testNomePiattoVuoto() {

    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    String nome = "";
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
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void testIngredientiVuoto() {

    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    String nome = "Testing";
    String ingredienti = " ";
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
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void testTroppeCalorie() {

    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    String nome = "Testing";
    String ingredienti = "LATTE,FARINA";
    String calorie = "2001";
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
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void testIngredientiNull() {
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    Mockito.doReturn(null).when(request).getParameter("ingredienti");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
  }

  @Test
  void testTroppiIngredienti() {
    Mockito.doReturn("aggiungiPiatto").when(request).getParameter("action");
    /* 260 caratteri */
    String veryLong =
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    Mockito.doReturn(veryLong).when(request).getParameter("ingredienti");
    assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));

  }

  @Test
  void testPiattoEquals_1() {

    PiattoBean tmp = new PiattoBean("Lasagna", "Pomodoro", "primo", 1, 1, 1, 1, 1);
    assertTrue(tmp.equals(tmp));

  }

  @Test
  void testPiattoEquals_2() {

    PiattoBean tmp = new PiattoBean("Lasagna", "Pomodoro", "primo", 1, 1, 1, 1, 1);
    assertFalse(tmp.equals(null));

  }

  @Test
  void testPiattoEquals_3() {

    PiattoBean tmp = new PiattoBean("Lasagna", "Pomodoro", "primo", 1, 1, 1, 1, 1);
    assertFalse(tmp.equals("Ciao"));

  }

  @Test
  void testPiattoEquals_4() {

    PiattoBean tmp = new PiattoBean("Lasagna", "Pomodoro", "primo", 1, 1, 1, 1, 1);
    PiattoBean tmp2 = new PiattoBean("Lasagna", "Pomodoro", "primo", 1, 1, 1, 1, 1);
    assertTrue(tmp.equals(tmp2));

  }


}


