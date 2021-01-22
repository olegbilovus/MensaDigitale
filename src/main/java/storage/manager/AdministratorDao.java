package storage.manager;

import business.admin.AdministratorBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import storage.interfaces.AdministratorInterface;

/**
 * Classe DAO per la gestione di AdministratorBean.
 */
public class AdministratorDao implements AdministratorInterface<AdministratorBean> {

  /*
   * Costruttore per AdministratorDao.
   */
  public AdministratorDao() {
  }

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @param email email dell'amministratore da ricercare
   * @pre email e' non null
   * @post se l'entita' esiste nel database il valore di ritorno e' diverso da null
   * @category Ricerca un amministratore in base all'email dell'amministratore
   */
  @Override
  public AdministratorBean doRetrieveByKey(String email) throws SQLException {
    AdministratorBean bean = new AdministratorBean();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM administrator WHERE email=?";
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
   * @post se la table corrispondente contiene entita', la lista di ritorno non e' vuota
   * @category Ritorna tutti gli administrator
   */
  @Override
  public Collection<AdministratorBean> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM administrator";
    ArrayList<AdministratorBean> collection = new ArrayList<>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        AdministratorBean bean = new AdministratorBean();
        bean.setEmail(rs.getString("email"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
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
   * @param bean Amministratore da salvare
   * @pre bean e' un AdministratorBean valido e non null
   * @post bean e' reso persistente nel database
   * @category Salva un amministratore nel database
   */
  @Override
  public void doSave(AdministratorBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO administrator VALUES (?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getEmail());
      statement.setString(2, bean.getNome());
      statement.setString(3, bean.getCognome());
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
   * @param bean Amministratore con contenuto aggiornato
   * @pre bean e' un AdministratorBean valido, non null, gia' esistente nel database
   * @post l'entita' corrispondente nel database rispecchia lo stato di bean
   * @category Aggiorna un amministratore
   */
  @Override
  public void doUpdate(AdministratorBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE administrator SET nome=? WHERE email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getNome());
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
   * @param bean Indica il bean da eliminare
   * @pre bean e' un Administrator valido, non null, gia' esistente nel database
   * @post l'entita' corrispondente nel database viene eliminata
   * @category Cancella un amministratore
   */
  @Override
  public void doDelete(AdministratorBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM administrator WHERE email=?";
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
