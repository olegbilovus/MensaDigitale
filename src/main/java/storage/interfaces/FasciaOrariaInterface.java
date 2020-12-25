package storage.interfaces;

import java.sql.SQLException;
import storage.manager.FasciaOrariaBean;

public interface FasciaOrariaInterface<T> extends ModelInterface<FasciaOrariaBean> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @param id La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  public T doRetrieveByKey(int id) throws SQLException;
}