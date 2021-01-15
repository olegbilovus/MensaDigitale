package business.prenotazioni;

/**
 * Interfaccia per l'identificativo.
 *
 * @param <T> type dell'identificativo
 */
public interface Identificativo<T> {

  T getIdentificativo();

  T setIdentificativo(T identificativo);

}
