package storage.manager;

import business.addetto.AddettoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import storage.interfaces.AddettoInterface;

public class AddettoDao implements AddettoInterface<AddettoBean> {

  
  /*
   * Costruttore per AddettoDao.
   */
  public AddettoDao() {}

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @category Ricerca l'addetto in base all'email dell'addetto
   * 
   * @param email email dell'addettoda ricercare
   */

  @Override
  public AddettoBean doRetrieveByKey(String email) throws SQLException {
    AddettoBean bean = new AddettoBean();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM addetto WHERE email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, email);
      System.out.println("DoRetrieveByKey" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setEmail(rs.getString("email"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setLvlPermessi(rs.getInt("lvlPermessi"));
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
   * @category Ritorna tutti gli addetti
   * 
   */

  @Override
  public Collection<AddettoBean> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM addetto";
    ArrayList<AddettoBean> collection = new ArrayList<AddettoBean>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        AddettoBean bean = new AddettoBean();
        bean.setEmail(rs.getString("email"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setLvlPermessi(rs.getInt("lvlPermessi"));
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
    return null;

  }

  /**
   * Metodo utilizzato per salvare i valori contenuti in un bean all'interno di una tabella.
   * 
   * @category Salva un addetto nel database
   * 
   * @param bean Addetto da salvare
   */

  @Override
  public void doSave(AddettoBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO addetto VALUES (?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getEmail());
      statement.setString(2, bean.getNome());
      statement.setString(3, bean.getCognome());
      statement.setInt(4, bean.getLvlPermessi());
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
   * @category Aggiorna un addetto
   * 
   * @param bean Addetto con contenuto aggiornato
   */

  @Override
  public void doUpdate(AddettoBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE addetto SET lvlPermessi=? WHERE email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, bean.getLvlPermessi());
      statement.setString(2, bean.getEmail());
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
   * @category Cancella un addetto
   * 
   * @param bean Indica il bean da eliminare
   */

  @Override
  public void doDelete(AddettoBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM addetto WHERE email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getEmail());
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
