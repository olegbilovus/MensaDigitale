package storage.manager;

public class AddettoBean {

  private String email;
  private String nome;
  private String cognome;
  private int lvlPermessi;

  
  public AddettoBean() {
    super();
  }


  /**
   * Costruttore dell'addetto.
   * @param email L'email dell'addetto
   * @param nome Nome dell'addetto
   * @param cognome Cognome dell'addetto
   * @param lvlPermessi Livello permessi dell'addetto
   */

  public AddettoBean(String email, String nome, String cognome, int lvlPermessi) {
    super();
    this.email = email;
    this.nome = nome;
    this.cognome = cognome;
    this.lvlPermessi = lvlPermessi;
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


  public int getLvlPermessi() {
    return lvlPermessi;
  }


  public void setLvlPermessi(int lvlPermessi) {
    this.lvlPermessi = lvlPermessi;
  }


  @Override
  public String toString() {
    return "AddettoBean [email=" + email + ", nome=" + nome + ", cognome=" + cognome + ","
       + " lvlPermessi=" + lvlPermessi + "]";
  }
  
  
  
}
