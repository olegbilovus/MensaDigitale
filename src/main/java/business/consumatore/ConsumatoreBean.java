package business.consumatore;

import java.io.Serializable;
import java.sql.Date;

public class ConsumatoreBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private String email;
  private String nome;
  private String cognome;
  private int statoServizi;
  private String codiceFiscale;
  private Date dataDiNascita;
  private String indirizzo;
  private String telefono;
  private String cellulare;
  private String comuneNascita;
  private String provinciaNascita;
  private String cittadinanza;
  private int rifugiato;
  private int residenzaNucleoFamiliare;
  private int saldo;
  private int fasciaPagamento;

  public ConsumatoreBean() {
    super();
  }

  /**
   * Costruttore del consumatore.
   * 
   * @param email L'email del consumatore
   * @param nome Nome del consumatore
   * @param cognome Cognome del consumatore
   * @param statoServizi Lo stato dei servizi
   * @param codiceFiscale Codice Fiscale consumaore
   * @param dataDiNascita Data di nascita del consumatore
   * @param indirizzo Indirizzo del consumatore
   * @param telefono Telefono del consumatore
   * @param cellulare Cellulare del consumatore
   * @param comuneNascita Comune di nascita del consumatore
   * @param provinciaNascita Provincia di nascita del consumatore
   * @param cittadinanza Cittadinanza del consumatore
   * @param rifugiato Indica se il consumatore e' rifugiato
   * @param residenzaNucleoFamiliare Indica la residenza
   * @param saldo Indica il saldo del consumatore
   * @param fasciaPagamento Fascia pagamento del consumatore
   */

  public ConsumatoreBean(String email, String nome, String cognome, int statoServizi,
      String codiceFiscale, Date dataDiNascita, String indirizzo, String telefono, String cellulare,
      String comuneNascita, String provinciaNascita, String cittadinanza, int rifugiato,
      int residenzaNucleoFamiliare, int saldo, int fasciaPagamento) {
    super();
    this.email = email;
    this.nome = nome;
    this.cognome = cognome;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
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

  public int getRifugiato() {
    return rifugiato;
  }

  public void setRifugiato(int rifugiato) {
    this.rifugiato = rifugiato;
  }

  public int getResidenzaNucleoFamiliare() {
    return residenzaNucleoFamiliare;
  }

  public void setResidenzaNucleoFamiliare(int residenzaNucleoFamiliare) {
    this.residenzaNucleoFamiliare = residenzaNucleoFamiliare;
  }

  public int getSaldo() {
    return saldo;
  }

  public void setSaldo(int saldo) {
    this.saldo = saldo;
  }

  public int getFasciaPagamento() {
    return fasciaPagamento;
  }

  public void setFasciaPagamento(int fasciaPagamento) {
    this.fasciaPagamento = fasciaPagamento;
  }

  public boolean isDocente() {
    return email.endsWith("@unisa.it");
  }

  @Override
  public String toString() {
    return "ConsumatoreBean [email=" + email + ", nome=" + nome + ", cognome=" + cognome + ","
        + " statoServizi=" + statoServizi + ", codiceFiscale=" + codiceFiscale + ","
        + " dataDiNascita=" + dataDiNascita + ", indirizzo=" + indirizzo + "," + " telefono="
        + telefono + ", cellulare=" + cellulare + ", comuneNascita=" + comuneNascita
        + ", provinciaNascita=" + provinciaNascita + ", cittadinanza=" + cittadinanza
        + ", rifugiato=" + rifugiato + ", residenzaNucleoFamiliare=" + residenzaNucleoFamiliare
        + ", saldo=" + saldo + ", fasciaPagamento=" + fasciaPagamento + "]";
  }



}
