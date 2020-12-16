package business.prenotazioni;

public class FasciaOraria {

  private int id;
  private String fascia;

  public FasciaOraria(int id, String fascia) {
    this.id = id;
    this.fascia = fascia;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFascia() {
    return fascia;
  }

  public void setFascia(String fascia) {
    this.fascia = fascia;
  }

  @Override
  public String toString() {
    return this.getClass().getName() + "[id=" + id + ", fascia=" + fascia + "]";
  }


}
