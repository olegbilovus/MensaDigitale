package storage.manager;

import business.richieste.RichiestaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import storage.interfaces.RichiestaInterface;

/**
 * Classe DAO per la gestione di RichiestaBean
 */
public class RichiestaDao implements RichiestaInterface<RichiestaBean> {

  /*
   * Costruttore per RichiestaDao.
   */
  public RichiestaDao() {}

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @post restituisce la richiesta identificata dall'id passato come parametro se presente nel
   *       database
   * @param id id della richiesta da ricercare
   * @category Ricerca la richiesta in base all'id della richiesta
   */
  @Override
  public RichiestaBean doRetrieveByKey(int id) throws SQLException {
    RichiestaBean bean = null;
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM richiesta WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, id);
      System.out.println("DoRetrieveByKey" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean = new RichiestaBean();
        bean.setId(rs.getInt("id"));
        bean.setEmail(rs.getString("email"));
        bean.setEsito(rs.getInt("esito"));
        bean.setValutatore(rs.getString("valutatore"));
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
   * @post restituisce la lista contenente tutte le richieste presenti nel database
   * @category Ritorna tutte le richieste
   */
  @Override
  public Collection<RichiestaBean> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM richiesta";
    ArrayList<RichiestaBean> collection = new ArrayList<RichiestaBean>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        RichiestaBean bean = new RichiestaBean();
        bean.setId(rs.getInt("id"));
        bean.setEmail(rs.getString("email"));
        bean.setEsito(rs.getInt("esito"));
        bean.setValutatore(rs.getString("valutatore"));
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
   * @post la richiesta identificata da bean e' correttamente salvata dal database
   * @param bean Richiesta da salvare
   * @category Salva una richiesta nel database
   */
  @Override
  public void doSave(RichiestaBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO richiesta VALUES (?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, bean.getId());
      statement.setString(2, bean.getEmail());
      statement.setInt(3, bean.getEsito());
      statement.setString(4, bean.getValutatore());
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
   * @post la richiesta identificata da bean e' correttamente aggiornata sul database
   * @param bean Richiesta con contenuto aggiornato
   * @category Aggiorna una richiesta
   */
  @Override
  public void doUpdate(RichiestaBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE richiesta SET esito=?, valutatore=? WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, bean.getEsito());
      statement.setString(2, bean.getValutatore());
      statement.setInt(3, bean.getId());
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
   * @post la richiesta identificata da bean non e' piu' presente sul database
   * @param bean Indica il bean da eliminare
   * @category Cancella una richiesta
   */
  @Override
  public void doDelete(RichiestaBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM richiesta WHERE id=?";
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
   * Metodo invocato per ottenere la lista delle richieste a cui non � ancora stato dato un esito.
   *
   * @post restituisce una lista contenente tutte le richieste con esito = 0 presenti nel database
   * @return collection di richieste in sospeso
   */
  public Collection<RichiestaBean> doRetrieveInSospeso() {
    Connection con = null;
    PreparedStatement statement = null;
    Collection<RichiestaBean> listaRichieste = new ArrayList<RichiestaBean>();
    String sql = "SELECT * FROM richiesta WHERE esito = 0";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while (result.next()) {
        int id = result.getInt("id");
        String email = result.getString("email");

        RichiestaBean richiesta = new RichiestaBean(id, email);
        listaRichieste.add(richiesta);
      }
      return listaRichieste;

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
    return listaRichieste;
  }
}
