package business.addetto;

import business.utente.Utente;

public class AddettoBean extends Utente {

  private static final long serialVersionUID = 1L;
  private int lvlPermessi;

  /**
   * Bean per l'addetto.
   */
  public AddettoBean() {
    super();
  }

  /**
   * Costruttore della classe AddettoBean.
   *
   * @param email       L'email dell'addetto
   * @param nome        Nome dell'addetto
   * @param cognome     Cognome dell'addetto
   * @param lvlPermessi Livello permessi dell'addetto
   */
  public AddettoBean(String email, String nome, String cognome, int lvlPermessi) {
    super(email, nome, cognome);
    this.lvlPermessi = lvlPermessi;
  }

  public int getLvlPermessi() {
    return lvlPermessi;
  }

  public void setLvlPermessi(int lvlPermessi) {
    this.lvlPermessi = lvlPermessi;
  }

  @Override
  public String toString() {
    return "AddettoBean [email="
        + email
        + ", nome="
        + nome
        + ", cognome="
        + cognome
        + ", lvlPermessi="
        + lvlPermessi
        + "]";
  }
}
