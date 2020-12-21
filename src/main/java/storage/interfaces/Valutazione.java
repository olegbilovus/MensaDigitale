package storage.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Valutazione<T> {

	public void doSave(T valutazione) throws SQLException;
	public void doUpdate(T valutazione) throws SQLException;
	public void doDelete(T valutazione) throws SQLException;
	public T doRetrieveByKey(String email, String piatto) throws SQLException;
	public ArrayList<T> doRetrieveAll(String email, String piatto) throws SQLException;
}
