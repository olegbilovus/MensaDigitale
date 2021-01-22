package business.utente;

import java.io.Serializable;

/**
 * Classe abstract per l'utente.
 */
public abstract class Utente implements Serializable {

  private static final long serialVersionUID = 1L;
  protected String email;
  protected String nome;
  protected String cognome;

  /**
   * Utente rappresenta l'utente generico del sistema.
   *
   * @param email   e' l'email dell'utente
   * @param nome    e' il nome dell'utente
   * @param cognome e' il cognome dell'utente
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
