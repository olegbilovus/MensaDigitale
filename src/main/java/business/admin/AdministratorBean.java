package business.admin;

public class AdministratorBean {

  private String email;
  private String nome;
  private String cognome;

  public AdministratorBean() {
    super();
  }

  /**
   * Costruttore dell'admin.
   * @param email L'email dell'admin
   * @param nome Nome dell'admin
   * @param cognome Cognome dell'admin
   */
  
  public AdministratorBean(String email, String nome, String cognome) {
    super();
    this.email = email;
    this.nome = nome;
    this.cognome = cognome;
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

  @Override
  public String toString() {
    return "AdministratorBean [email=" + email + ", nome=" + nome + ", cognome=" + cognome + "]";
  }
  
  
  
  
  
  
  
}
