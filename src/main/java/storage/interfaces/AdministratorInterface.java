package storage.interfaces;

import business.admin.AdministratorBean;
import java.sql.SQLException;

public interface AdministratorInterface<T> extends ModelInterface<AdministratorBean> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @param email La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  T doRetrieveByKey(String email) throws SQLException;
}
