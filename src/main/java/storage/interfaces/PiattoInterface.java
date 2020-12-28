package storage.interfaces;

import business.piatti.PiattoBean;
import java.sql.SQLException;

public interface PiattoInterface<T> extends ModelInterface<PiattoBean> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @param nome La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  public T doRetrieveByKey(String nome) throws SQLException;

}
