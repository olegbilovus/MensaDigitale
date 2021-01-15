package business.addetto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import business.consumatore.ConsumatoreBean;
import business.prenotazioni.FasciaOrariaBean;
import business.prenotazioni.PrenotazioneBean;
import business.prenotazioni.QRCode;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import storage.interfaces.ConsumatoreInterface;
import storage.interfaces.FasciaOrariaInterface;
import storage.interfaces.PrenotazioneInterface;
import storage.manager.ConsumatoreDao;
import storage.manager.FasciaOrariaDao;
import storage.manager.PrenotazioneDao;

class GestioneIdentificativoTest {

  private static final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private static final PrintWriter writer = Mockito.mock(PrintWriter.class);
  private final PrenotazioneInterface<PrenotazioneBean<String>> dao = new PrenotazioneDao();
  private final FasciaOrariaInterface<FasciaOrariaBean> daoF = new FasciaOrariaDao();
  private final ConsumatoreInterface<ConsumatoreBean> daoC = new ConsumatoreDao();
  private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private final GestioneIdentificativo servlet = new GestioneIdentificativo();
  private ConsumatoreBean consumatore;
  private FasciaOrariaBean fascia = new FasciaOrariaBean(98, "11:40");
  private PrenotazioneBean<String> bean;

  @BeforeAll
  public static void init() throws IOException {
    Mockito.doReturn(writer).when(response).getWriter();
  }

  @BeforeEach
  public void initEach() {
    consumatore = new ConsumatoreBean("testerP10@unisa.it", "tester", "tester", 1, "tester",
        new Date(System.currentTimeMillis()), "tester", "tester", "tester", "tester", "tester",
        "tester", false, false, 50, 1);
    fascia = new FasciaOrariaBean(98, "11:40");
    bean = new PrenotazioneBean<>(new Date(System.currentTimeMillis()),
        new QRCode(UUID.randomUUID().toString().replace("-", "")), 2, fascia.getId(),
        consumatore.getEmail());
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse1()
      throws SQLException, ServletException, IOException {
    ArgumentCaptor<String> jsonS = ArgumentCaptor.forClass(String.class);
    try {
      daoF.doSave(fascia);
      daoC.doSave(consumatore);
      dao.doSave(bean);

      Mockito.doReturn(bean.getIdentificativo().getIdentificativo()).when(request)
          .getParameter("identificativo");
      Mockito.doReturn("controllo").when(request).getParameter("action");
      Mockito.doNothing().when(writer).println(jsonS.capture());

      servlet.doPost(request, response);

      JSONObject json = new JSONObject();
      json.put("response", "200");
      json.put("prenotazione", new JSONObject(bean));
      json.put("consumatore", new JSONObject(consumatore));
      assertTrue(json.toString().equals(jsonS.getValue()));

    } finally {
      dao.doDelete(bean);
      daoF.doDelete(fascia);
      daoC.doDelete(consumatore);
    }
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse2()
      throws SQLException, ServletException, IOException {
    ArgumentCaptor<String> jsonS = ArgumentCaptor.forClass(String.class);
    try {
      daoF.doSave(fascia);
      daoC.doSave(consumatore);
      dao.doSave(bean);

      Mockito.doReturn(bean.getIdentificativo().getIdentificativo()).when(request)
          .getParameter("identificativo");
      Mockito.doReturn("segnala").when(request).getParameter("action");
      Mockito.doNothing().when(writer).println(jsonS.capture());

      servlet.doPost(request, response);

      assertTrue(dao.doRetrieveByKey(bean.getIdentificativo().getIdentificativo()).isEntrato());

    } finally {
      dao.doDelete(bean);
      daoF.doDelete(fascia);
      daoC.doDelete(consumatore);
    }
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse3()
      throws SQLException, ServletException, IOException {
    ArgumentCaptor<String> jsonS = ArgumentCaptor.forClass(String.class);
    try {
      daoF.doSave(fascia);
      daoC.doSave(consumatore);
      dao.doSave(bean);

      Mockito.doReturn(bean.getIdentificativo().getIdentificativo()).when(request)
          .getParameter("identificativo");
      Mockito.doReturn("abc").when(request).getParameter("action");
      Mockito.doNothing().when(writer).println(jsonS.capture());

      servlet.doPost(request, response);

      JSONObject json = new JSONObject();
      json.put("response", "400");

      assertTrue(json.toString().equals(jsonS.getValue()));

    } finally {
      dao.doDelete(bean);
      daoF.doDelete(fascia);
      daoC.doDelete(consumatore);
    }
  }

  @Test
  void testDoPostHttpServletRequestHttpServletResponse4()
      throws SQLException, ServletException, IOException {
    ArgumentCaptor<String> jsonS = ArgumentCaptor.forClass(String.class);
    try {

      Mockito.doReturn(UUID.randomUUID().toString()).when(request)
          .getParameter("identificativo");
      Mockito.doReturn("abc").when(request).getParameter("action");
      Mockito.doNothing().when(writer).println(jsonS.capture());

      servlet.doPost(request, response);

      JSONObject json = new JSONObject();
      json.put("response", "404");

      assertTrue(json.toString().equals(jsonS.getValue()));

    } finally {
    }
  }

}
