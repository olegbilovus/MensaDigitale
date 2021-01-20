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
  public PiattoDao() {
  }

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @pre nome non e' null
   * @post se l'entita' esiste nel database il valore di ritorno non e' null
   * @param nome Nome del piatto da ricercare
   * @category Ricerca il piatto in base al nome del piatto
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
        bean.setPortata(rs.getString("portata"));
        bean.setCalorie(rs.getInt("calorie"));
        bean.setProteine(rs.getInt("proteine"));
        bean.setGrassi(rs.getInt("grassi"));
        bean.setSodio(rs.getInt("sodio"));
        bean.setCarboidrati(rs.getInt("carboidrati"));
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
   * @post restituisce la lista contenente tutti i piatti nel database. La lista puo' essere vuota 
   * @category Ritorna tutte i piatti
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
        bean.setPortata(rs.getString("portata"));
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
    return collection;
  }

  /**
   * Metodo utilizzato per salvare i valori contenuti in un bean all'interno di una tabella.
   *
   * @pre bean non e' null
   * @post il piatto rappresentato da bean e' stato salvato in modo persistente sul database
   * @param bean Piatto da salvare
   * @category Salva un piatto nel database
   */
  @Override
  public void doSave(PiattoBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO piatto VALUES (?,?,?,?,?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getNome());
      statement.setString(2, bean.getIngredienti());
      statement.setString(3, bean.getPortata());
      statement.setInt(4, bean.getCalorie());
      statement.setInt(5, bean.getProteine());
      statement.setInt(6, bean.getGrassi());
      statement.setInt(7, bean.getSodio());
      statement.setInt(8, bean.getCarboidrati());
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
   * @pre bean non e' null
   * @post il piatto rappresentato da bean e' aggiornato correttamente nel database
   * @param bean Piatto con contenuto aggiornato
   * @category Aggiorna un piatto
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
   * @pre bean non e' null
   * @post il piatto identificato da bean non e' piu' presente nel database
   * @param bean Indica il bean da eliminare
   * @category Cancella un piatto
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
