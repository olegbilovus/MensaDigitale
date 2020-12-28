package storage.manager;

import business.piatti.PiattoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import storage.interfaces.PiattoInterface;

public class PiattoDao implements PiattoInterface<PiattoBean> {


  /*
   * Costruttore per PiattoDao.
   */
  public PiattoDao() {}

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @category Ricerca il piatto in base al nome del piatto
   * 
   * @param nome Nome del piatto da ricercare
   */

  @Override
  public PiattoBean doRetrieveByKey(String nome) throws SQLException {
    PiattoBean bean = new PiattoBean();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM piatto WHERE nome=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, nome);
      System.out.println("DoRetrieveByKey" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setNome(rs.getString("nome"));
        bean.setIngredienti(rs.getString("ingredienti"));
        bean.setCalorie(rs.getInt("calorie"));
        bean.setProteine(rs.getInt("proteine"));
        bean.setGrassi(rs.getInt("grassi"));
        bean.setSodio(rs.getInt("sodio"));
        bean.setCarboidrati(rs.getInt("carboidrati"));
      }
      return bean;
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
   * @category Ritorna tutte i piatti
   * 
   */

  @Override
  public Collection<PiattoBean> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM piatto";
    ArrayList<PiattoBean> collection = new ArrayList<PiattoBean>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        PiattoBean bean = new PiattoBean();
        bean.setNome(rs.getString("nome"));
        bean.setIngredienti(rs.getString("ingredienti"));
        bean.setCalorie(rs.getInt("calorie"));
        bean.setProteine(rs.getInt("proteine"));
        bean.setGrassi(rs.getInt("grassi"));
        bean.setSodio(rs.getInt("sodio"));
        bean.setCarboidrati(rs.getInt("carboidrati"));
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
   * @category Salva un piatto nel database
   * 
   * @param bean Piatto da salvare
   */

  @Override
  public void doSave(PiattoBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO piatto VALUES (?,?,?,?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getNome());
      statement.setString(2, bean.getIngredienti());
      statement.setInt(3, bean.getCalorie());
      statement.setInt(4, bean.getProteine());
      statement.setInt(5, bean.getGrassi());
      statement.setInt(6, bean.getSodio());
      statement.setInt(7, bean.getCarboidrati());
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
   * @category Aggiorna un piatto
   * 
   * @param bean Piatto con contenuto aggiornato
   */

  @Override
  public void doUpdate(PiattoBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE piatto SET ingredienti=? WHERE nome=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getIngredienti());
      statement.setString(2, bean.getNome());
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
   * @category Cancella un piatto
   * 
   * @param bean Indica il bean da eliminare
   */

  @Override
  public void doDelete(PiattoBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM piatto WHERE nome=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getNome());
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
