package business.consumatore;

import business.utente.Utente;
import java.io.Serializable;
import java.util.Date;

/**
 * Bean per il consumatore.
 */
public class ConsumatoreBean extends Utente{

  private static final long serialVersionUID = 1L;
  private int statoServizi;
  private String codiceFiscale;
  private Date dataDiNascita;
  private String indirizzo;
  private String telefono;
  private String cellulare;
  private String comuneNascita;
  private String provinciaNascita;
  private String cittadinanza;
  private boolean rifugiato;
  private boolean residenzaNucleoFamiliare;
  private float saldo;
  private int fasciaPagamento;

  public ConsumatoreBean() {
    super();
  }

  /**
   * Costruttore del consumatore.
   *
   * @param email                    L'email del consumatore
   * @param nome                     Nome del consumatore
   * @param cognome                  Cognome del consumatore
   * @param statoServizi             Lo stato dei servizi
   * @param codiceFiscale            Codice Fiscale consumaore
   * @param dataDiNascita            Data di nascita del consumatore
   * @param indirizzo                Indirizzo del consumatore
   * @param telefono                 Telefono del consumatore
   * @param cellulare                Cellulare del consumatore
   * @param comuneNascita            Comune di nascita del consumatore
   * @param provinciaNascita         Provincia di nascita del consumatore
   * @param cittadinanza             Cittadinanza del consumatore
   * @param rifugiato                Indica se il consumatore e' rifugiato
   * @param residenzaNucleoFamiliare Indica la residenza
   * @param saldo                    Indica il saldo del consumatore
   * @param fasciaPagamento          Fascia pagamento del consumatore
   */
  public ConsumatoreBean(
      String email,
      String nome,
      String cognome,
      int statoServizi,
      String codiceFiscale,
      Date dataDiNascita,
      String indirizzo,
      String telefono,
      String cellulare,
      String comuneNascita,
      String provinciaNascita,
      String cittadinanza,
      boolean rifugiato,
      boolean residenzaNucleoFamiliare,
      float saldo,
      int fasciaPagamento) {
    super(email, nome, cognome);
    this.statoServizi = statoServizi;
    this.codiceFiscale = codiceFiscale;
    this.dataDiNascita = dataDiNascita;
    this.indirizzo = indirizzo;
    this.telefono = telefono;
    this.cellulare = cellulare;
    this.comuneNascita = comuneNascita;
    this.provinciaNascita = provinciaNascita;
    this.cittadinanza = cittadinanza;
    this.rifugiato = rifugiato;
    this.residenzaNucleoFamiliare = residenzaNucleoFamiliare;
    this.saldo = saldo;
    this.fasciaPagamento = fasciaPagamento;
  }

  public ConsumatoreBean(String email) {
    this.email = email;
  }

  public int getStatoServizi() {
    return statoServizi;
  }

  public void setStatoServizi(int statoServizi) {
    this.statoServizi = statoServizi;
  }

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  public Date getDataDiNascita() {
    return dataDiNascita;
  }

  public void setDataDiNascita(Date dataDiNascita) {
    this.dataDiNascita = dataDiNascita;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCellulare() {
    return cellulare;
  }

  public void setCellulare(String cellulare) {
    this.cellulare = cellulare;
  }

  public String getComuneNascita() {
    return comuneNascita;
  }

  public void setComuneNascita(String comuneNascita) {
    this.comuneNascita = comuneNascita;
  }

  public String getProvinciaNascita() {
    return provinciaNascita;
  }

  public void setProvinciaNascita(String provinciaNascita) {
    this.provinciaNascita = provinciaNascita;
  }

  public String getCittadinanza() {
    return cittadinanza;
  }

  public void setCittadinanza(String cittadinanza) {
    this.cittadinanza = cittadinanza;
  }

  public boolean getRifugiato() {
    return rifugiato;
  }

  public void setRifugiato(boolean rifugiato) {
    this.rifugiato = rifugiato;
  }

  public boolean getResidenzaNucleoFamiliare() {
    return residenzaNucleoFamiliare;
  }

  public void setResidenzaNucleoFamiliare(boolean residenzaNucleoFamiliare) {
    this.residenzaNucleoFamiliare = residenzaNucleoFamiliare;
  }

  public float getSaldo() {
    return saldo;
  }

  public void setSaldo(float f) {
    this.saldo = f;
  }

  public int getFasciaPagamento() {
    return fasciaPagamento;
  }

  public void setFasciaPagamento(int fasciaPagamento) {
    this.fasciaPagamento = fasciaPagamento;
  }

  public boolean isDocente() {
    return super.getEmail().endsWith("@unisa.it");
  }

  @Override
  public String toString() {
    return "ConsumatoreBean{"
        + "statoServizi="
        + statoServizi
        + ", codiceFiscale='"
        + codiceFiscale
        + '\''
        + ", dataDiNascita="
        + dataDiNascita
        + ", indirizzo='"
        + indirizzo
        + '\''
        + ", telefono='"
        + telefono
        + '\''
        + ", cellulare='"
        + cellulare
        + '\''
        + ", comuneNascita='"
        + comuneNascita
        + '\''
        + ", provinciaNascita='"
        + provinciaNascita
        + '\''
        + ", cittadinanza='"
        + cittadinanza
        + '\''
        + ", rifugiato="
        + rifugiato
        + ", residenzaNucleoFamiliare="
        + residenzaNucleoFamiliare
        + ", saldo="
        + saldo
        + ", fasciaPagamento="
        + fasciaPagamento
        + "} "
        + super.toString();
  }
}
