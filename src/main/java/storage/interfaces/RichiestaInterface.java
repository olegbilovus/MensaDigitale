package storage.interfaces;

import business.richieste.RichiestaBean;
import java.sql.SQLException;

public interface RichiestaInterface<T> extends ModelInterface<RichiestaBean> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @param id La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  public T doRetrieveByKey(int id) throws SQLException;
  
}
