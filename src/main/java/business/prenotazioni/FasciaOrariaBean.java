package business.prenotazioni;

/**
 * Bean per la fascia oraria.
 *
 */
public class FasciaOrariaBean {

  private int id;
  private String fascia;

  /**
   * Bean della fascia oraria.
   *
   * @param id     l'id della fascia oraria
   * @param fascia la stringa che rappresenta la fascia oraria
   */
  public FasciaOrariaBean(int id, String fascia) {
    this.id = id;
    this.fascia = fascia;
  }

  public FasciaOrariaBean() {
    super();
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
