package storage.manager;

import business.prenotazioni.FasciaOrariaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import storage.interfaces.FasciaOrariaInterface;

public class FasciaOrariaDao implements FasciaOrariaInterface<FasciaOrariaBean> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @param id id della fascia oraria da ricercare
   * @category Ricerca la fascia oraria in base all'id della fascia oraria
   */
  @Override
  public FasciaOrariaBean doRetrieveByKey(int id) throws SQLException {
    FasciaOrariaBean bean = new FasciaOrariaBean();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM fasciaoraria WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, id);
      System.out.println("DoRetrieveByKey" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setId(rs.getInt("id"));
        bean.setFascia(rs.getString("fascia"));
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
   * @category Ritorna tutte le fasce orarie
   */
  @Override
  public Collection<FasciaOrariaBean> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM fasciaoraria";
    ArrayList<FasciaOrariaBean> collection = new ArrayList<>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        FasciaOrariaBean bean = new FasciaOrariaBean();
        bean.setId(rs.getInt("id"));
        bean.setFascia(rs.getString("fascia"));
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
   * @param bean Fascia oraria da salvare
   * @category Salva una fascia oraria nel database
   */
  @Override
  public void doSave(FasciaOrariaBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO fasciaoraria VALUES (?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, bean.getId());
      statement.setString(2, bean.getFascia());
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
   * @param bean Fascia oraria con contenuto aggiornato
   * @category Aggiorna una fascia oraria
   */
  @Override
  public void doUpdate(FasciaOrariaBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE fasciaoraria SET fascia=? WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getFascia());
      statement.setInt(2, bean.getId());
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
   * @param bean Indica il bean da eliminare
   * @category Cancella una fascia oraria
   */
  @Override
  public void doDelete(FasciaOrariaBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM fasciaoraria WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, bean.getId());
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
   * Metodo utilizzato per trovare una fascia oraria identificata dalla fascia.
   *
   * @param fasciaOraria indica la fascia oraria da cercare
   * @category Restituisce una fascia oraria
   */
  public FasciaOrariaBean doRetrieveByFascia(String fasciaOraria) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM fasciaoraria WHERE fascia = ?";
    FasciaOrariaBean result = null;
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, fasciaOraria);
      System.out.println("doRetrieveByFascia=" + statement);
      ResultSet results = statement.executeQuery();
      if (!results.next()) {
        return null;
      }
      result = new FasciaOrariaBean(results.getInt("id"), results.getString("fascia"));
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

    return result;
  }
}
