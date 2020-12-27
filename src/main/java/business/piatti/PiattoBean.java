package business.piatti;

import java.util.Objects;

public class PiattoBean {

  private String nome;
  private String ingredienti;
  private int calorie;
  private int proteine;
  private int grassi;
  private int sodio;
  private int carboidrati;

  public PiattoBean() {
  }

  /**
   * Costruttore della classe PiattoBean.
   *
   * @param nome il nome del Piatto
   * @param ingredienti una stringa che contiene tutti gli ingredienti
   * @param calorie numero di calorie
   * @param proteine numero di proteine (in g/porzione)
   * @param grassi numero di grassi (in g/porzione)
   * @param sodio numero di sodio (in g/porzione)
   * @param carboidrati numero di carboidrati (in g/porzione)
   */
  public PiattoBean(String nome, String ingredienti, int calorie, int proteine,
                    int grassi, int sodio, int carboidrati) {
    this.nome = nome;
    this.ingredienti = ingredienti;
    this.calorie = calorie;
    this.proteine = proteine;
    this.grassi = grassi;
    this.sodio = sodio;
    this.carboidrati = carboidrati;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getIngredienti() {
    return ingredienti;
  }

  public void setIngredienti(String ingredienti) {
    this.ingredienti = ingredienti;
  }

  public int getCalorie() {
    return calorie;
  }

  public void setCalorie(int calorie) {
    this.calorie = calorie;
  }

  public int getProteine() {
    return proteine;
  }

  public void setProteine(int proteine) {
    this.proteine = proteine;
  }

  public int getGrassi() {
    return grassi;
  }

  public void setGrassi(int grassi) {
    this.grassi = grassi;
  }

  public int getSodio() {
    return sodio;
  }

  public void setSodio(int sodio) {
    this.sodio = sodio;
  }

  public int getCarboidrati() {
    return carboidrati;
  }

  public void setCarboidrati(int carboidrati) {
    this.carboidrati = carboidrati;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PiattoBean)) {
      return false;
    }
    PiattoBean that = (PiattoBean) o;
    return getCalorie() == that.getCalorie() && getProteine() == that.getProteine()
            && getGrassi() == that.getGrassi() && getSodio() == that.getSodio()
            && getCarboidrati() == that.getCarboidrati() && getNome().equals(that.getNome())
            && getIngredienti().equals(that.getIngredienti());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNome(), getIngredienti(), getCalorie(),
            getProteine(), getGrassi(), getSodio(), getCarboidrati());
  }

  @Override
  public String toString() {
    return "PiattoBean{" + "nome='" + nome + '\'' + ", ingredienti='" + ingredienti
            + '\'' + ", calorie=" + ", proteine=" + calorie + proteine + ", grassi="
            + grassi + ", sodio=" + sodio + ", carboidrati=" + carboidrati + '}';
  }

}
