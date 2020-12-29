package business.prenotazioni;

import business.admin.AdministratorBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.interfaces.FasciaOrariaInterface;
import storage.manager.FasciaOrariaDao;

public class FasciaOrariaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static FasciaOrariaInterface<FasciaOrariaBean> fasciaOrariaDao = new FasciaOrariaDao();

  public FasciaOrariaServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String fasciaOraria = request.getParameter("fasciaOraria");
    String action = request.getParameter("action"); // Inserimento o cancellazione

    if (!(request.getSession().getAttribute("user") instanceof AdministratorBean)) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    Collection<FasciaOrariaBean> fasceOrarieEsistenti;
    try {
      fasceOrarieEsistenti = (Collection<FasciaOrariaBean>) fasciaOrariaDao.doRetrieveAll();
      int numFasceOrarie = ((int) request.getServletContext().getAttribute("numFasceOrarie"));
      if (action.equals("inserisci")) {
        if (!presente(fasciaOraria, fasceOrarieEsistenti)) {
          int id = getNewId(fasceOrarieEsistenti);
          FasciaOrariaBean nuovaFasciaOraria = new FasciaOrariaBean(id, fasciaOraria);
          fasciaOrariaDao.doSave(nuovaFasciaOraria);
          numFasceOrarie++;
          request.getServletContext().setAttribute("numFasceOrarie", numFasceOrarie);
        } else {
          /*
           * La fascia oraria che si vuole inserire è già presente, devo mandare un messaggio
           */
          System.out.println("#######Non lo so ancora");
        }
      } else if (action.equals("elimina")) {
        if (presente(fasciaOraria, fasceOrarieEsistenti)) {
          FasciaOrariaBean fasciaOrariaDeleted = fasciaOrariaDao.doRetrieveByFascia(fasciaOraria);
          fasciaOrariaDao.doDelete(fasciaOrariaDeleted);
          numFasceOrarie--;
          request.getServletContext().setAttribute("numFasceOrarie", numFasceOrarie);
        } else {
          /*
           * La fascia oraria che si vuole eliminare non è presente, devo mandare un messaggio
           */
          System.out.println("#######Non lo so ancora");
        }
      } else {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private Boolean presente(String fasciaOraria, Collection<FasciaOrariaBean> list) {
    /*
     * f = fasciaOraria: es. 11:00 - Durata 40 min Se x,y,z sono tre fasce e x < y < z allora y-x
     * >=40 e z-y >= 40
     */

    class Ora {
      private int ora;
      private int minuti;

      public Ora(int ora, int minuti) {
        this.ora = ora;
        this.minuti = minuti;
      }

      public boolean equals(Ora anotherOra) {
        if (this.ora == anotherOra.ora && this.minuti == anotherOra.minuti) {
          return true;
        }
        return false;
      }

      /**
       * Confronta due orari.
       * 
       * @return 1 se this > anotherOra
       * @return 0 se this == anotherOra
       * @return -1 se this < anotherOra
       */
      public int compareTo(Ora anotherOra) {
        if (this.ora > anotherOra.ora) {
          return 1;
        }
        if (this.ora < anotherOra.ora) {
          return -1;
        }
        if (this.minuti > anotherOra.minuti) {
          return 1;
        }
        if (this.minuti < anotherOra.minuti) {
          return -1;
        }
        return 0;
      }

      public Ora getFineFascia() {
        int minutiFine = this.minuti + 40;
        int oraFine = this.ora;
        if (minutiFine - 60 > 0) {
          oraFine++;
          minutiFine = minutiFine - 60;
        }
        return new Ora(oraFine, minutiFine);
      }
    }

    Ora nuovaFascia = new Ora(Integer.parseInt(fasciaOraria.subSequence(0, 2).toString()),
        Integer.parseInt(fasciaOraria.subSequence(3, 5).toString()));

    for (FasciaOrariaBean oldFascia : list) {
      Ora vecchiaFascia =
          new Ora(Integer.parseInt(oldFascia.getFascia().subSequence(0, 2).toString()),
              Integer.parseInt(oldFascia.getFascia().subSequence(3, 5).toString()));

      if (nuovaFascia.equals(vecchiaFascia)) {
        return true;
      }
      if (nuovaFascia.ora == vecchiaFascia.ora) {
        /*
         * Se l'ora è uguale controllo se la distanza tra le due fasce orarie è di meno di 40 minuti
         */
        if (Math.abs(nuovaFascia.minuti - vecchiaFascia.minuti) < 40) {
          return true;
        }
      }
      if (nuovaFascia.ora > vecchiaFascia.ora) {
        /*
         * Se l'ora è diversa controllo se l'inizio della fascia con l'ora più alta è precedente
         * alla fine dell'altra fascia
         */
        if (nuovaFascia.compareTo(vecchiaFascia.getFineFascia()) < 0) {
          return true;
        }
      } else {
        if (nuovaFascia.getFineFascia().compareTo(vecchiaFascia) > 0) {
          return true;
        }
      }
    }
    return false;
  }

  private int getNewId(Collection<FasciaOrariaBean> list) {
    boolean[] array = new boolean[list.size() + 1];
    array[0] = false; // Evitiamo di usare un id = 0
    for (int i = 1; i < array.length; i++) {
      array[i] = true;
    }
    for (FasciaOrariaBean fascia : list) {
      if (fascia.getId() <= list.size()) {
        array[fascia.getId()] = false;
      }
    }
    for (int i = 1; i < array.length; i++) {
      if (array[i]) {
        return i;
      }
    }
    return array.length;
  }

}
