package business.addetto;

public class AddettoBean {
  
  private String email;
  private String nome;
  private String cognome;
  private int lvlPermessi;

  
  public AddettoBean() {
    super();
  }


  /**
   * Costruttore della classe AddettoBean.
   * @param email L'email dell'addetto
   * @param nome Nome dell'addetto
   * @param cognome Cognome dell'addetto
   * @param lvlPermessi Livello permessi dell'addetto
   */

  public AddettoBean(String email, String nome, String cognome, int lvlPermessi) {
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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + lvlPermessi;
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AddettoBean other = (AddettoBean) obj;
    if (cognome == null) {
      if (other.cognome != null) {
        return false;
      }
    } else if (!cognome.equals(other.cognome)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (lvlPermessi != other.lvlPermessi) {
      return false;
    }
    if (nome == null) {
      if (other.nome != null) {
        return false;
      }
    } else if (!nome.equals(other.nome)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "AddettoBean [email=" + email + ", nome=" + nome + ", cognome=" + cognome
        + ", lvlPermessi=" + lvlPermessi + "]";
  }  
  
}
