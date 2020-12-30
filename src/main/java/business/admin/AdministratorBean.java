package business.admin;

import business.utente.Utente;

public class AdministratorBean extends Utente {

  public AdministratorBean() {
    super();
  }

  /**
   * Costruttore dell'admin.
   * 
   * @param email L'email dell'admin
   * @param nome Nome dell'admin
   * @param cognome Cognome dell'admin
   */

  public AdministratorBean(String email, String nome, String cognome) {
    super(email, nome, cognome);
  }

  @Override
  public String toString() {
    return "AdministratorBean [email=" + getEmail() + ", nome=" + getNome() + ", cognome="
        + getCognome() + "]";
  }



}
