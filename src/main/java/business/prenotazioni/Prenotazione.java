package business.prenotazioni;

// Test
import java.sql.Timestamp;

public class Prenotazione<T> {

  private Timestamp dataPrenotazione;
  private Identificativo<T> identificativo;
  private int sala;
  private int fasciaOraria;
  private String email;

  public Prenotazione(Timestamp dataPrenotazione, Identificativo<T> identificativo, int sala,
      int fasciaOraria, String email) {
    this.dataPrenotazione = dataPrenotazione;
    this.identificativo = identificativo;
    this.sala = sala;
    this.fasciaOraria = fasciaOraria;
    this.email = email;
  }

  public Timestamp getDataPrenotazione() {
    return dataPrenotazione;
  }

  public void setDataPrenotazione(Timestamp dataPrenotazione) {
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
