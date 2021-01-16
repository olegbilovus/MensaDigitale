package business.utente;

import business.addetto.AddettoBean;
import business.admin.AdministratorBean;
import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.AddettoDao;
import storage.manager.AdministratorDao;
import storage.manager.ConsumatoreDao;

public class LoginServlet extends HttpServlet {

  private static final ConsumatoreDao consumatoreDao = new ConsumatoreDao();
  private static final AdministratorDao administratorDao = new AdministratorDao();
  private static final AddettoDao addettoDao = new AddettoDao();

  /**
   * Costruttore per LoginServlet. Inizializza il verifier per utilizzare il sistema di
   * autenticazione fornito da Google
   */
  public LoginServlet() {
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("action");

    if (action != null) {

      switch (action) {

        case "loginGoogle" -> loginGoogle(request, response);

        case "logOut" -> {
          request.getSession().invalidate();
          response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

      }

    }

  }

  /**
   * Verifica se l'indirizzo email passato come parametro e' presente nel database. Se lo e', setta
   * lo studente come attributo di sessione
   *
   * @param email    l'indirizzo email di uno studente
   * @param request  la request http
   * @param response la response http
   */
  private void verificaStudenti(
      String email, HttpServletRequest request, HttpServletResponse response) {

    try {
      ConsumatoreBean consumatore = consumatoreDao.doRetrieveByKey(email);

      if (consumatore == null) {
        // login tentato da uno studente, ma l'indirizzo email non e' presente nel db mensadigitale
        request.getSession().setAttribute("utente", new ConsumatoreBean(email));
        response
            .sendRedirect(response.encodeURL(request.getContextPath() + "/attivazione.jsp"));

      } else {
        // login effettuato con successo da parte di uno studente
        request.getSession().setAttribute("utente", consumatore);
        response.sendRedirect(response.encodeURL(request.getContextPath() + "/index.jsp"));
      }

    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }

  }

  /**
   * Verifica se l'indirizzo email passato come parametro e' presente nel database. Se lo e', setta
   * il docente come attributo di sessione
   *
   * @param email    l'indirizzo email di un docente
   * @param request  la request http
   * @param response la response http
   */
  private void verificaDocente(String email, HttpServletRequest request,
      HttpServletResponse response) {

    try {
      // e' un docente?
      ConsumatoreBean consumatore = consumatoreDao.doRetrieveByKey(email);
      if (consumatore != null) {
        // si, era un docente
        request.getSession().setAttribute("utente", consumatore);
        response.sendRedirect(response.encodeURL(request.getContextPath() + "/index.jsp"));

      } else {
        response.sendError(HttpServletResponse.SC_FORBIDDEN,
            "Impossibile trovavare l'indirizzo " + email + " nel database di MensaDigitale");
      }

    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }

  }

  /**
   * Verifica se l'indirizzo email passato come parametro e' presente nel database. Se lo e', setta
   * l'administrator o l'addetto come attributo di sessione
   *
   * @param email    l'email di un administrator o di un addetto
   * @param request  la request http
   * @param response la response http
   */
  private void verificaAdminAddetto(String email, HttpServletRequest request,
      HttpServletResponse response) {

    try {

      // e' un admin?
      AdministratorBean administratorBean = administratorDao.doRetrieveByKey(email);
      if (administratorBean != null) {
        // si, e' un admin
        request.getSession().setAttribute("utente", administratorBean);
        response.sendRedirect(response.encodeURL(request.getContextPath() + "/index.jsp"));

      } else {
        // e' un addetto?
        AddettoBean addettoBean = addettoDao.doRetrieveByKey(email);
        if (addettoBean != null) {
          // si, e' un addetto
          request.getSession().setAttribute("utente", addettoBean);
          response.sendRedirect(response.encodeURL(request.getContextPath() + "/index.jsp"));

        } else {
          // non e' niente
          response.sendError(HttpServletResponse.SC_BAD_REQUEST,
              "Questa email non e' presente nel database.");
          throw new IllegalArgumentException("Questa email non e' presente nel database.");
        }
      }
    } catch (SQLException | IOException throwables) {
      throwables.printStackTrace();
    }

  }

  /**
   * Responsabile della logica di business per gestire il login attraverso il dominio Google.
   *
   * @param request  la request http
   * @param response la response http
   */
  private void loginGoogle(HttpServletRequest request, HttpServletResponse response) {

    String email = request.getParameter("email");

    if (email != null) {

      String dominio = email.split("@")[1];

      switch (dominio) {
        case ("studenti.unisa.it") -> verificaStudenti(email, request, response);
        case ("unisa.it") -> verificaDocente(email, request, response);
        default -> verificaAdminAddetto(email, request, response);
      }

    } else {
      System.out.println("LoginServlet: Token ID non valido.");
    }

  }

  //TOO EASY

}
