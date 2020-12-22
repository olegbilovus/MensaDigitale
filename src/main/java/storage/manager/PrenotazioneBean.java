package storage.manager;

import java.sql.Date;

public class PrenotazioneBean {

  private String id;
  private String email;
  private Date dataPrenotazione;
  private int sala;
  private int fasciaOraria;


  public PrenotazioneBean() {
    super();
  }

  /**
   * Costruttore della prenotazione.
   * 
   * @param email L'email dell'utente
   * @param id Identificatore della richiesta
   * @param dataPrenotazione Data della prenotazione
   * @param sala Sala prenotata
   * @param fasciaOraria Fascia oraria della prenotazione
   */

  public PrenotazioneBean(String id, String email, Date dataPrenotazione, int sala,
      int fasciaOraria) {
    super();
    this.id = id;
    this.email = email;
    this.dataPrenotazione = dataPrenotazione;
    this.sala = sala;
    this.fasciaOraria = fasciaOraria;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDataPrenotazione() {
    return dataPrenotazione;
  }

  public void setDataPrenotazione(Date dataPrenotazione) {
    this.dataPrenotazione = dataPrenotazione;
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

  @Override
  public String toString() {
    return "PrenotazioneBean [id=" + id + ", email=" + email + ", dataPrenotazione="
        + dataPrenotazione + ", sala=" + sala + ", fasciaOraria=" + fasciaOraria + "]";
  }



}
