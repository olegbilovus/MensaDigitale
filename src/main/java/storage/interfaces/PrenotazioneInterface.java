package storage.interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;


public interface PrenotazioneInterface<T> extends ModelInterface<T> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @param id La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  public T doRetrieveByKey(String id) throws SQLException;

  public T doRetrieveByDateAndFascia(Date date, String email, int fasciaOraria) throws SQLException;

  public Collection<T> doRetrieveByDateSalaFascia(Date date, int sala, int fascia)
      throws SQLException;
}
