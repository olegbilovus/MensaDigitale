package business.valutazioni;

import java.sql.Date;

/**
 * Bean per la valutazione.
 *
 */
public class ValutazioneBean {

  private String email;
  private String piatto;
  private int recensione;
  private Date dataValutazione;

  public ValutazioneBean() {
    super();
  }

  /**
   * Costruttore della valutazione.
   *
   * @param email           L'email del consumatore che effettua la valutazione
   * @param piatto          Il piatto da valutare
   * @param recensione      La recensione effettuata dal consumatore
   * @param dataValutazione La data in cui e' stata effettuata la valutazione
   */
  public ValutazioneBean(String email, String piatto, int recensione, Date dataValutazione) {
    super();
    this.email = email;
    this.piatto = piatto;
    this.recensione = recensione;
    this.dataValutazione = dataValutazione;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPiatto() {
    return piatto;
  }

  public void setPiatto(String piatto) {
    this.piatto = piatto;
  }

  public int getRecensione() {
    return recensione;
  }

  public void setRecensione(int recensione) {
    this.recensione = recensione;
  }

  public Date getDataValutazione() {
    return dataValutazione;
  }

  public void setDataValutazione(Date dataValutazione) {
    this.dataValutazione = dataValutazione;
  }

  @Override
  public String toString() {
    return "ValutazioneBean [email="
        + email
        + ", piatto="
        + piatto
        + ", recensione="
        + recensione
        + ", dataValutazione="
        + dataValutazione
        + "]";
  }
}
