package business.piatti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean per il menu.
 */
public class MenuBean implements Serializable {

  private static final long serialVersionUID = 3276353706196636611L;

  private final List<PiattoBean> primi;
  private final List<PiattoBean> secondi;
  private final List<PiattoBean> contorni;

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

  /**
   * Rimuove tutti i piatti dalla lista dei primi.
   */
  public void clearPrimi() {
    primi.clear();
  }

  /**
   * Rimuove tutti i piatti dalla lista dei secondi.
   */
  public void clearSecondi() {
    secondi.clear();
  }

  /**
   * Rimuove tutti i piatti dalla lista dei contorni.
   */
  public void clearContorni() {
    contorni.clear();
  }

  @Override
  public String toString() {
    return "MenuBean{" + "primi=" + primi + ", secondi=" + secondi + ", contorni=" + contorni + '}';
  }
}
