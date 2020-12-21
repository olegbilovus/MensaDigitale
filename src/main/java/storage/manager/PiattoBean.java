package storage.manager;

public class PiattoBean {

  private String nome;
  private String ingredienti;
  private int calorie;
  private int proteine;
  private int grassi;
  private int sodio;
  private int carboidrati;

  public PiattoBean() {
    super();
  }

  /**
   * Costruttore del piatto.
   * @param nome Nome del piatto
   * @param ingredienti Ingredienti del piatto
   * @param calorie Calorie del piatto
   * @param proteine Proteine del piatto
   * @param grassi Grassi del piatto
   * @param sodio Sodio del piatto
   * @param carboidrati Carboidrati del piatto
   */

  
  public PiattoBean(String nome, String ingredienti, int calorie, 
      int proteine, int grassi, int sodio, int carboidrati) {
    super();
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
  public String toString() {
    return "PiattoBeans [nome=" + nome + ", ingredienti=" + ingredienti + ", calorie="
      + calorie + ", proteine=" + proteine + ", grassi=" + grassi + ", sodio="
      + sodio + ", carboidrati=" + carboidrati + "]";
  }
  
  
  
}
