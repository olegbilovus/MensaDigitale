package business.utente;

public abstract class Utente {
  private String email;
  private String nome;
  private String cognome;

  /**
   * Utente rappresenta l'utente generico del sistema.
   * 
   * @param email è l'email dell'utente
   * @param nome è il nome dell'utente
   * @param cognome è il cognome dell'utente
   */
  public Utente(String email, String nome, String cognome) {
    this.email = email;
    this.nome = nome;
    this.cognome = cognome;
  }

  public Utente() {
    super();
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

}
