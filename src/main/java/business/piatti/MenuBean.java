package business.piatti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuBean implements Serializable {

  private List<PiattoBean> primi;
  private List<PiattoBean> secondi;
  private List<PiattoBean> contorni;

  /**
   * Costruttore per la classe MenuBean. Inizializza le liste che rappresentano i primi, i secondi e
   * i contorni.
   */
  public MenuBean() {
    primi = new ArrayList<PiattoBean>();
    secondi = new ArrayList<PiattoBean>();
    contorni = new ArrayList<PiattoBean>();
  }

  public List<PiattoBean> getPrimi() {
    return primi;
  }

  public List<PiattoBean> getSecondi() {
    return secondi;
  }

  public List<PiattoBean> getContorni() {
    return contorni;
  }

  /**
   * Aggiunge un piatto alla lista dei primi.
   *
   * @param primo il piatto da aggiungere alla lista dei primi
   */
  public void addPrimo(PiattoBean primo) {
    primi.add(primo);
  }

  /**
   * Aggiunge un piatto alla lista dei secondi.
   *
   * @param secondo il piatto da aggiungere alla lista dei secondi
   */
  public void addSecondo(PiattoBean secondo) {
    secondi.add(secondo);
  }

  /**
   * Aggiunge un piatto alla lista dei contorni.
   *
   * @param contorno il piatto da aggiungere alla lista dei contorni
   */
  public void addContorno(PiattoBean contorno) {
    contorni.add(contorno);
  }

  /**
   * Rimuove un piatto dalla lista dei primi.
   *
   * @param primo il piatto da rimuovere dalla lista dei primi
   */
  public void removePrimo(PiattoBean primo) {
    primi.remove(primo);
  }

  /**
   * Rimuove un piatto dalla lista dei secondi.
   *
   * @param secondo il piatto da rimuovere dalla lista dei secondi
   */
  public void removeSecondo(PiattoBean secondo) {
    secondi.remove(secondo);
  }

  /**
   * Rimuove un piatto dalla lista dei contorni.
   *
   * @param contorno il piatto da rimuovere dalla lista dei contorni
   */
  public void removeContorno(PiattoBean contorno) {
    contorni.remove(contorno);
  }

  /** Rimuove tutti i piatti dalla lista dei primi. */
  public void clearPrimi() {
    primi.clear();
  }

  /** Rimuove tutti i piatti dalla lista dei secondi. */
  public void clearSecondi() {
    secondi.clear();
  }

  /** Rimuove tutti i piatti dalla lista dei contorni. */
  public void clearContorni() {
    contorni.clear();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contorni == null) ? 0 : contorni.hashCode());
    result = prime * result + ((primi == null) ? 0 : primi.hashCode());
    result = prime * result + ((secondi == null) ? 0 : secondi.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    MenuBean other = (MenuBean) obj;
    return super.equals(other);
  }

  @Override
  public String toString() {
    return "MenuBean{" + "primi=" + primi + ", secondi=" + secondi + ", contorni=" + contorni + '}';
  }
}
