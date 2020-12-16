package business.prenotazioni;

public class QRCode implements Identificativo<String> {

  private String identificativo;

  public QRCode(String identificativo) {
    this.identificativo = identificativo;
  }

  public String getIdentificativo() {
    return this.identificativo;
  }

  public String setIdentificativo(String identificativo) {
    this.identificativo = identificativo;
    return this.identificativo;
  }

  @Override
  public String toString() {
    return this.getClass().getName() + "[identificativo=" + identificativo + "]";
  }

}
