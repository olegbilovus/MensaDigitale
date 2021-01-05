package business.prenotazioni;

import java.io.Serializable;

public abstract class Identificativo<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  public abstract T getIdentificativo();

  public abstract T setIdentificativo(T identificativo);

//  @Override
//  public boolean equals(Object other) {
//    if (other == null || other.getClass() != getClass()) {
//      return false;
//    }
//    Identificativo<T> id = (Identificativo<T>) other;
//    return this.getIdentificativo().equals(id.getIdentificativo());
//  }
}
