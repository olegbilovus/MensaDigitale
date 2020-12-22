package storage.interfaces;

import java.sql.SQLException;
import storage.manager.ConsumatoreBean;

public interface ConsumatoreInterface<T> extends ModelInterface<ConsumatoreBean> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @param email La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  public T doRetrieveByKey(String email) throws SQLException;
}
