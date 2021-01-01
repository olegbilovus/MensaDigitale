package business.utente;

import business.addetto.AddettoBean;
import business.consumatore.ConsumatoreBean;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import storage.manager.AddettoDao;
import storage.manager.ConsumatoreDao;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

  ConsumatoreDao consumatoreDao = new ConsumatoreDao();
  AddettoDao addettoDao = new AddettoDao();

  private final GoogleIdTokenVerifier verifier;

  /**
   * Costruttore per LoginServlet.
   * Inizializza il verifier per utilizzare il sistema di autenticazione fornito da Google
   */
  public LoginServlet() {
    verifier =
        new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
            .setAudience(
                Collections.singletonList(
                    "112892589115-l441sfpsdcid969k4b23jva30fs8igod.apps.googleusercontent.com"))
            .build();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String idTokenString = request.getParameter("tokenId");

    GoogleIdToken idToken = null;
    try {
      idToken = verifier.verify(idTokenString);
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }

    if (idToken != null) {

      Payload payload = idToken.getPayload();

      // dal payload accedo all'email
      String email = payload.getEmail();
      String dominio = email.split("@")[1];
      System.out.println("Dominio: " + dominio);

      switch (dominio) {
        case ("studenti.unisa.it") -> verificaStudenti(email, request, response);
        case ("unisa.it") -> verificaDocenteAddetto(email, request, response);
        default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Questo dominio non appartiene all'insieme dei domini UNISA");
      }

    } else {
      System.out.println("LoginServlet: Token ID non valido.");
    }
  }

  /**
   * Verifica se l'indirizzo email passato come parametro e' presente nel database.
   * Se lo e', setta lo studente come attributo di sessione
   * @param email l'indirizzo email di uno studente
   * @param request la request http
   * @param response la response http
   */
  private void verificaStudenti(
      String email, HttpServletRequest request, HttpServletResponse response) {

    try {
      ConsumatoreBean tmp = consumatoreDao.doRetrieveByKey(email);

      if (tmp == null) {
        // login tentato da uno studente, ma l'indirizzo email non è presente nel db mensadigitale
        response.sendRedirect(response.encodeURL(request.getContextPath() + "/attivazione.jsp"));

      } else {
        // login effettuato con successo da parte di uno studente
        request.getSession().setAttribute("consumatore", tmp);
        response.sendRedirect(response.encodeURL(request.getContextPath() + "/index.jsp"));
      }

    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }

  }

  /**
   * Verifica se l'indirizzo email passato come parametro e' presente nel database.
   * Se lo e', setta il docente o l'addetto come attributo di sessione
   * @param email l'indirizzo email di un docente o di un addetto
   * @param request la request http
   * @param response la response http
   */
  private void verificaDocenteAddetto(String email, HttpServletRequest request, HttpServletResponse response) {

    try {
      // e' un docente?
      ConsumatoreBean consumatore = consumatoreDao.doRetrieveByKey(email);
      if (consumatore != null) {
        // si, era un docente
        request.getSession().setAttribute("consumatore", consumatore);
        response.sendRedirect(response.encodeURL(request.getContextPath() + "/index.jsp"));

      } else {
        // non era un docente. e' un addetto?
        AddettoBean addetto = addettoDao.doRetrieveByKey(email);
        if (addetto != null) {
          // si, era un addetto
          request.getSession().setAttribute("consumatore", addetto);
          response.sendRedirect(response.encodeURL(request.getContextPath() + "/index.jsp"));

        } else {
          // non è nessuno
          response.sendError(HttpServletResponse.SC_FORBIDDEN, "Impossibile trovavare l'indirizzo " + email + " nel database di MensaDigitale");
        }
      }

    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }

  }

}
