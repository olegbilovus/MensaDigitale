package business.richieste;

import java.util.Collection;
import storage.manager.RichiestaDao;

/**
 * Singleton per le richieste i sospeso.
 */
public class RichiesteInSospeso {

  private static RichiesteInSospeso instance = null;
  private Collection<RichiestaBean> listaRichieste;

  private RichiesteInSospeso() {
    this.listaRichieste = null;
    instance = this;
  }

  /**
   * Metodo per ottenere l'unica instanza del Singleton RichiesteInSospeso.
   *
   * @return l'unica instanza di RichiesteInSospeso
   */
  public static RichiesteInSospeso getInstance() {
    if (instance == null) {
      return new RichiesteInSospeso();
    } else {
      return instance;
    }
  }

  public Collection<RichiestaBean> getListaRichieste() {
    return this.listaRichieste;
  }

  public void load() {
    RichiestaDao richiestaDao = new RichiestaDao();
    this.listaRichieste = richiestaDao.doRetrieveInSospeso();
  }
}
