package storage.interfaces;

import business.prenotazioni.FasciaOrariaBean;
import java.sql.SQLException;

public interface FasciaOrariaInterface<T> extends ModelInterface<T> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @param id La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  T doRetrieveByKey(int id) throws SQLException;

  FasciaOrariaBean doRetrieveByFascia(String fasciaOraria) throws SQLException;
}
