package business.prenotazioni;

import java.sql.Date;

public class PrenotazioneBean<T> {

  private Date dataPrenotazione;
  private Identificativo<T> identificativo;
  private int sala;
  private int fasciaOraria;
  private String email;

  public PrenotazioneBean() {
    super();
  }

  /**
   * Bean della prenotazione.
   * 
   * @param dataPrenotazione la data della prenotazione
   * @param identificativo l'identificativo della prenotazione
   * @param sala la sala della prenotazione
   * @param fasciaOraria la fascia oraria della prenotazione
   * @param email l'email del prenotante
   */
  public PrenotazioneBean(Date dataPrenotazione, Identificativo<T> identificativo, int sala,
      int fasciaOraria, String email) {
    this.dataPrenotazione = dataPrenotazione;
    this.identificativo = identificativo;
    this.sala = sala;
    this.fasciaOraria = fasciaOraria;
    this.email = email;
  }

  public Date getDataPrenotazione() {
    return dataPrenotazione;
  }

  public void setDataPrenotazione(Date dataPrenotazione) {
    this.dataPrenotazione = dataPrenotazione;
  }

  public Identificativo<T> getIdentificativo() {
    return identificativo;
  }

  public void setIdentificativo(Identificativo<T> identificativo) {
    this.identificativo = identificativo;
  }

  public int getSala() {
    return sala;
  }

  public void setSala(int sala) {
    this.sala = sala;
  }

  public int getFasciaOraria() {
    return fasciaOraria;
  }

  public void setFasciaOraria(int fasciaOraria) {
    this.fasciaOraria = fasciaOraria;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return this.getClass().getName() + "[dataPrenotazione=" + dataPrenotazione + ", identificativo="
        + identificativo + ", sala=" + sala + ", fasciaOraria=" + fasciaOraria + ", email=" + email
        + "]";
  }

}
