package storage.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import storage.interfaces.ValutazioneInterface;


public class ValutazioneDao implements ValutazioneInterface<ValutazioneBean> {


  /*
   * Costruttore per ValutazioneDao.
   */
  public ValutazioneDao() {}

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @category Ricerca la valutazione in base all'email dell'utente
   * 
   * @param email email dell'utente da ricercare
   */

  @Override
  public ValutazioneBean doRetrieveByKey(String email, String piatto) throws SQLException {
    ValutazioneBean bean = new ValutazioneBean();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM valutazione WHERE email=? AND piatto=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, email);
      statement.setString(2, piatto);
      System.out.println("DoRetrieveByKey" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setEmail(rs.getString("email"));
        bean.setPiatto(rs.getString("piatto"));
        bean.setRecensione(rs.getInt("recensione"));
        bean.setDataValutazione(rs.getDate("dataValutazione"));
        return bean;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }

    }
    return null;

  }

  /**
   * Metodo da utilizzare per prelevare tutte le entry di un elemento in una tabella.
   * 
   * @category Ritorna tutte le valutazioni
   * 
   */

  @Override
  public Collection<ValutazioneBean> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM valutazione";
    ArrayList<ValutazioneBean> collection = new ArrayList<ValutazioneBean>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        ValutazioneBean bean = new ValutazioneBean();
        bean.setEmail(rs.getString("email"));
        bean.setPiatto(rs.getString("piatto"));
        bean.setRecensione(rs.getInt("recensione"));
        bean.setDataValutazione(rs.getDate("dataValutazione"));
        collection.add(bean);
      }
      return collection;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }

    }
    return collection;

  }

  /**
   * Metodo utilizzato per salvare i valori contenuti in un bean all'interno di una tabella.
   * 
   * @category Salva una recensione nel database
   * 
   * @param bean Recensione da salvare
   */

  @Override
  public void doSave(ValutazioneBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO valutazione VALUES (?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getEmail());
      statement.setString(2, bean.getPiatto());
      statement.setInt(3, bean.getRecensione());
      statement.setDate(4, bean.getDataValutazione());
      System.out.println("doSave=" + statement);
      statement.executeUpdate();
      con.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }

    }

  }

  /**
   * Metodo utilizzato per aggiornare i valori di un bean all'interno del database.
   * 
   * @category Aggiorna una recensione
   * 
   * @param bean Recensione con contenuto aggiornato
   */

  @Override
  public void doUpdate(ValutazioneBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE valutazione SET recensione=? WHERE email=? AND piatto=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, bean.getRecensione());
      statement.setString(2, bean.getEmail());
      statement.setString(3, bean.getPiatto());
      System.out.println("doUpdate=" + statement);
      statement.executeUpdate();
      con.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }

    }
  }

  /**
   * Metodo utilizzato per eliminare una riga identificata da un bean all'interno del databse.
   * 
   * @category Cancella una recensione
   * 
   * @param bean Indica il bean da eliminare
   */

  @Override
  public void doDelete(ValutazioneBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM valutazione WHERE email=? AND piatto=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getEmail());
      statement.setString(1, bean.getPiatto());
      System.out.println("doUpdate=" + statement);
      statement.executeUpdate();
      con.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }

    }
  }

}

