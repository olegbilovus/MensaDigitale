package business.richieste;

public class RichiestaBean {

  private int id;
  private String email;
  private int esito;
  private String valutatore;

  public RichiestaBean() {
    super();
  }

  /**
   * Costruttore della richiesta.
   *
   * @param email      L'email dell'utente
   * @param id         Identificatore della richiesta
   * @param esito      Esito della richiesta
   * @param valutatore Ente che ha valutato la richiesta
   */
  public RichiestaBean(int id, String email, int esito, String valutatore) {
    super();
    this.id = id;
    this.email = email;
    this.esito = esito;
    this.valutatore = valutatore;
  }

  /**
   * Costruttore della nuova richiesta da salvare su Database con chiave autogenerata.
   *
   * @param email L'email dell'utente
   */
  public RichiestaBean(String email) {
    super();
    this.email = email;
    this.esito = 0;
    this.valutatore = null;
  }

  /**
   * Costruttore della richiesta non ancora esitata ottenuta.
   *
   * @param id    l'id della richiesta
   * @param email l'email del richiedente
   */
  public RichiestaBean(int id, String email) {
    this.id = id;
    this.email = email;
    this.esito = 0;
    this.valutatore = null;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getEsito() {
    return esito;
  }

  public void setEsito(int esito) {
    this.esito = esito;
  }

  public String getValutatore() {
    return valutatore;
  }

  public void setValutatore(String valutatore) {
    this.valutatore = valutatore;
  }

  @Override
  public String toString() {
    return "RichiestaBean [id="
        + id
        + ", email="
        + email
        + ", esito="
        + esito
        + ", valutatore="
        + valutatore
        + "]";
  }
}
