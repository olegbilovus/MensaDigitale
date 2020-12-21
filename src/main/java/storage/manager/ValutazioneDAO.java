package storage.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import storage.interfaces.Valutazione;

public class ValutazioneDAO implements Valutazione<ValutazioneBean>{

	public void doSave(ValutazioneBean valutazione) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO valutazione value(?,?,?,?)");

			ps.setString(1, valutazione.getEmail());
			ps.setString(2, valutazione.getPiatto());
			ps.setInt(3, valutazione.getRecensione());
			ps.setDate(4, valutazione.getDataValutazione());

			ps.execute();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

			try {

				ps.close();
				DriverManagerConnectionPool.releaseConnection(con);

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
	}


	public ArrayList<ValutazioneBean> doRetrieveAll(String email, String piatto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void doUpdate(ValutazioneBean valutazione) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void doDelete(ValutazioneBean valutazione) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ValutazioneBean doRetrieveByKey(String email, String piatto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
