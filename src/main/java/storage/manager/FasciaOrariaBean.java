package storage.manager;

public class FasciaOrariaBean {

  private int id;
  private String fascia;

  public FasciaOrariaBean() {
    super();
  }

  /**
   * Costruttore della richiesta.
   * @param id Identificatore della fascia oraria
   * @param fascia Fascia Oraria della richiesta
   */
  
  public FasciaOrariaBean(int id, String fascia) {
    super();
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
    return "FasciaOrariaBean [id=" + id + ", fascia=" + fascia + "]";
  }
  
  
  
  
}
