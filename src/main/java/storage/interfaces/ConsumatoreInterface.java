package storage.interfaces;

import business.consumatore.ConsumatoreBean;
import java.sql.SQLException;
import java.util.Collection;

public interface ConsumatoreInterface<T> extends ModelInterface<ConsumatoreBean> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @param email La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */   
  public T doRetrieveByKey(String email) throws SQLException;

  public Collection<ConsumatoreBean> doRetrieveForTracciamento(String codiceFiscale, String dataIniziale);
}
