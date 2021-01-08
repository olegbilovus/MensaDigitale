package business.richieste;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.ConsumatoreDao;
import storage.manager.RichiestaDao;

public class SubmitRichiestaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static RichiestaDao richiestaDao = new RichiestaDao();
  private static ConsumatoreDao consumatoreDao = new ConsumatoreDao();
  private final int annoInizio = 1900;
  private final int annoFine = 2020;
  private final String[] listaProvince = {"AG", "AL", "AN", "AO", "AR", "AP", "AT", "AV", "BA",
      "BT", "BL", "BN", "BG", "BI", "BO", "BZ", "BS", "BR", "CA", "CL", "CB", "CE", "CT", "CZ",
      "CH", "CO", "CS", "CR", "KR", "CN", "EN", "FM", "FE", "FI", "FG", "FC", "FR", "GE", "GO",
      "GR", "IM", "IS", "AQ", "SP", "LT", "LE", "LC", "LI", "LO", "LU", "MC", "MN", "MS", "MT",
      "ME", "MI", "MO", "MB", "NA", "NO", "NU", "OR", "PA", "PD", "PR", "PV", "PG", "PU", "PE",
      "PC", "PI", "PT", "PN", "PZ", "PO", "RC", "RG", "RA", "RE", "RI", "RN", "RM", "RO", "SA",
      "SS", "SV", "SI", "SR", "SO", "SU", "TA", "TE", "TR", "TO", "TP", "TN", "TV", "TS", "UD",
      "VA", "VE", "VB", "VC", "VR", "VV", "VI", "VT"};

  public SubmitRichiestaServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String cognome = request.getParameter("cognome");
    String nome = request.getParameter("nome");
    String dataDiNascita = request.getParameter("dataDiNascita");
    String provinciaDiNascita = request.getParameter("provinciaDiNascita");
    String comuneDiNascita = request.getParameter("comuneDiNascita");
    String codiceFiscale = request.getParameter("codiceFiscale");
    String cittadinanza = request.getParameter("cittadinanza");
    boolean rifugiato = Boolean.getBoolean(request.getParameter("rifugiato"));
    boolean residenzaNucleo = Boolean.getBoolean(request.getParameter("residenzaNucleo"));
    String indirizzo = request.getParameter("indirizzo");
    String telefono = request.getParameter("telefono"); // Can be null
    String cellulare = request.getParameter("cellulare"); // Can be null
    String email = request.getParameter("email");
    String confermaEmail = request.getParameter("confermaEmail");

    boolean prelazione = Boolean.valueOf(request.getParameter("prelazione"));
    boolean responsabilita = Boolean.valueOf(request.getParameter("responsabilita"));

    boolean checkCognome = cognome != null && (cognome.length() >= 2 && cognome.length() <= 50)
        && onlyLetters(cognome);
    boolean checkNome =
        nome != null && (nome.length() >= 2 && nome.length() <= 50) && onlyLetters(nome);
    boolean checkDataDiNascita =
        dataDiNascita != null && dataDiNascita.length() == 10 && validateData(dataDiNascita);
    boolean checkProvinciaDiNascita =
        provinciaDiNascita != null && validateProvincia(provinciaDiNascita);
    boolean checkComuneDiNascita =
        comuneDiNascita != null && (comuneDiNascita.length() >= 2 && comuneDiNascita.length() <= 50)
            && onlyLetters(comuneDiNascita);
    boolean checkCodiceFiscale = codiceFiscale != null && validateCodiceFiscale(codiceFiscale);
    boolean checkCittadinanza = cittadinanza != null
        && (cittadinanza.length() >= 2 && cittadinanza.length() <= 50) && onlyLetters(cittadinanza);
    boolean checkIndirizzo =
        indirizzo != null && (indirizzo.length() >= 4 && indirizzo.length() <= 100);
    boolean checkTelefono = telefono == null || (telefono.length() >= 6 && telefono.length() <= 20);
    boolean checkCellulare =
        cellulare == null || (cellulare.length() >= 6 && cellulare.length() <= 20);
    boolean checkEmail =
        email != null && validateEmail(email) && email.equalsIgnoreCase(confermaEmail);

    if (!(checkCognome && checkNome && checkDataDiNascita && checkProvinciaDiNascita
        && checkComuneDiNascita && checkCodiceFiscale && checkCittadinanza && checkIndirizzo
        && checkTelefono && checkCellulare && checkEmail)) {
      throw new IllegalArgumentException();
    }

    if (!prelazione || !responsabilita) {
      throw new IllegalArgumentException();
    }

    try {
      ConsumatoreBean studente = null;
      studente = (ConsumatoreBean) request.getSession().getAttribute("utente");
      if ((studente.isDocente())) {
        return;
      }
      studente.setDataDiNascita(new SimpleDateFormat("yyyy/MM/dd").parse(dataDiNascita));
      studente.setProvinciaNascita(provinciaDiNascita);
      studente.setComuneNascita(comuneDiNascita);
      studente.setCittadinanza(cittadinanza);
      studente.setRifugiato(rifugiato);
      studente.setResidenzaNucleoFamiliare(residenzaNucleo);
      studente.setIndirizzo(indirizzo);
      studente.setTelefono(telefono);
      studente.setCellulare(cellulare);


      RichiestaBean nuovaRichiesta = new RichiestaBean(email);
      if (consumatoreDao.doRetrieveByKey(studente.getEmail()) != null) {
        consumatoreDao.doUpdate(studente);
      } else {
        consumatoreDao.doSave(studente);
      }

      richiestaDao.doSave(nuovaRichiesta);

      /*
       * Check if correct
       */
      if (response.getWriter() != null) {
        PrintWriter out = response.getWriter();
        if (richiestaDao.doRetrieveByKey(nuovaRichiesta.getId()) != null) {
          out.println("<script type=\"text/javascript\">");
          out.println("alert(\"La richiesta è stata inoltrata con successo\")");
          out.println("</script>");
        } else {
          out.println("<script type=\"text/javascript\">");
          out.println("alert(\"Inoltro della richiesta fallito. Ritentare più tardi\")");
          out.println("</script>");
        }
      }
    } catch (SQLException | ParseException e) {
      e.printStackTrace();
    }
  }

  private boolean onlyLetters(String word) {
    char[] array = word.toCharArray();
    for (char c : array) {
      if (!Character.isLetter(c)) {
        return false;
      }
    }
    return true;
  }

  private boolean validateData(String data) {
    if (data.charAt(2) != '/' || data.charAt(5) != '/') {
      return false;
    }
    int day = Integer.parseInt(data.subSequence(0, 2).toString());
    int month = Integer.parseInt(data.substring(3, 5));
    int year = Integer.parseInt(data.substring(6, 10));
    if (year < annoInizio || year > annoFine) {
      return false;
    }
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
        || month == 12) { // mese di 31
      if (!(day >= 1 && day <= 31)) {
        return false;
      }
    } else if (month == 11 || month == 4 || month == 6 || month == 9) { // mese di 30
      if (!(day >= 1 && day <= 30)) {
        return false;
      }
    } else if (month == 2) { // mese == febbraio
      if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) { // anno bisestile
        if (!(day >= 1 && day <= 29)) {
          return false;
        }
      } else {
        if (!(day >= 1 && day <= 28)) {
          return false;
        }
      }
    } else {
      return false;
    }
    return true;
  }

  private boolean validateProvincia(String provincia) {
    for (String esistente : listaProvince) {
      if (provincia.equalsIgnoreCase(esistente)) {
        return true;
      }
    }
    return false;
  }

  private boolean validateCodiceFiscale(String cf) {
    if (!cf.matches("^[0-9A-Z]{16}$")) {
      return false;
    }

    int s = 0;
    String evenMap = "BAFHJNPRTVCESULDGIMOQKWZYX";

    for (int i = 0; i < 15; i++) {
      int c = cf.charAt(i);

      int n;

      if ('0' <= c && c <= '9') {
        n = c - '0';
      } else {
        n = c - 'A';
      }

      if ((i & 1) == 0) {
        n = evenMap.charAt(n) - 'A';
      }
      s += n;
    }

    if (s % 26 + 'A' != cf.charAt(15)) {
      return false;
    }
    return true;
  }

  private boolean validateEmail(String email) {
    String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    if (!(email.matches(regex))) {
      return false;
    }

    return true;
  }

}
