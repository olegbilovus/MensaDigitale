package business.addetto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.consumatore.ConsumatoreBean;
import business.prenotazioni.FasciaOrariaBean;
import business.prenotazioni.PrenotazioneBean;
import business.prenotazioni.QRCode;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.manager.ConsumatoreDao;
import storage.manager.FasciaOrariaDao;
import storage.manager.PrenotazioneDao;

class PagamentoTest {

  private static final HttpSession session = Mockito.mock(HttpSession.class);
  private static final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static ConsumatoreBean consumatore = null;
  private static FasciaOrariaBean fascia = null;
  private static PrenotazioneBean<String> prenotazione = null;
  private final PrenotazioneDao dao = new PrenotazioneDao();
  private final FasciaOrariaDao daoF = new FasciaOrariaDao();
  private final ConsumatoreDao daoC = new ConsumatoreDao();
  private final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private final Pagamento servlet = new Pagamento();

  @BeforeAll
  public static void init() throws IOException, ServletException {
    Mockito.doReturn(session).when(request).getSession();
  }

  @BeforeEach
  public void initEach() {
    consumatore = new ConsumatoreBean("testerP10@unisa.it", "tester", "tester", 1, "tester",
        new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester", "tester",
        "tester", false, false, 50, 1);
    fascia = new FasciaOrariaBean(98, "11:40");
    prenotazione = new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
        new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
        consumatore.getEmail());
    Mockito.doReturn(prenotazione.getIdentificativo().getIdentificativo()).when(request)
        .getParameter("identificativo");
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse1()
      throws SQLException, ServletException, IOException {
    float saldoIniziale = consumatore.getSaldo();
    String costo = "5";
    try {
      daoF.doSave(fascia);
      daoC.doSave(consumatore);
      dao.doSave(prenotazione);
      Mockito.doReturn(costo).when(request).getParameter("costo");
      servlet.doPost(request, response);

    } catch (NullPointerException e) {
      assertTrue(daoC.doRetrieveByKey(consumatore.getEmail()).getSaldo() == saldoIniziale
          - Integer.parseInt(costo));
    } finally {
      dao.doDelete(prenotazione);
      daoF.doDelete(fascia);
      daoC.doDelete(consumatore);
    }
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse2()
      throws SQLException, ServletException, IOException {
    float saldoIniziale = consumatore.getSaldo();
    String costo = "5";
    try {
      daoF.doSave(fascia);
      daoC.doSave(consumatore);
      Mockito.doReturn(costo).when(request).getParameter("costo");
      servlet.doPost(request, response);

    } catch (NullPointerException e) {
      assertTrue(daoC.doRetrieveByKey(consumatore.getEmail()).getSaldo() == saldoIniziale);
    } finally {
      daoF.doDelete(fascia);
      daoC.doDelete(consumatore);
    }
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse3()
      throws SQLException, ServletException, IOException {
    float saldoIniziale = consumatore.getSaldo();
    String costo = "51";
    try {
      daoF.doSave(fascia);
      daoC.doSave(consumatore);
      dao.doSave(prenotazione);
      Mockito.doReturn(costo).when(request).getParameter("costo");
      servlet.doPost(request, response);

    } catch (NullPointerException e) {
      assertTrue(daoC.doRetrieveByKey(consumatore.getEmail()).getSaldo() == saldoIniziale
          - Integer.parseInt(costo));
    } finally {
      dao.doDelete(prenotazione);
      daoF.doDelete(fascia);
      daoC.doDelete(consumatore);
    }
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse4()
      throws SQLException, ServletException, IOException {
    float saldoIniziale = consumatore.getSaldo();
    consumatore.setEmail("tester@studente.unisa.it");
    prenotazione.setEmail(consumatore.getEmail());
    String costo = "51";
    try {
      daoF.doSave(fascia);
      daoC.doSave(consumatore);
      dao.doSave(prenotazione);
      Mockito.doReturn(costo).when(request).getParameter("costo");
      servlet.doPost(request, response);

    } catch (NullPointerException e) {
      assertTrue(daoC.doRetrieveByKey(consumatore.getEmail()).getSaldo() == saldoIniziale);
    } finally {
      dao.doDelete(prenotazione);
      daoF.doDelete(fascia);
      daoC.doDelete(consumatore);
    }
  }

}
