package storage.manager;

import business.prenotazioni.PrenotazioneBean;
import business.prenotazioni.QRCode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import storage.interfaces.PrenotazioneInterface;

public class PrenotazioneDao implements PrenotazioneInterface<PrenotazioneBean<String>> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @param id id della prenotazione da ricercare
   * @category Ricerca la prenotazione in base all'id della prenotazione
   */
  @Override
  public PrenotazioneBean<String> doRetrieveByKey(String id) throws SQLException {
    PrenotazioneBean<String> bean = new PrenotazioneBean<>();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM prenotazione WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, id);
      System.out.println("DoRetrieveByKey" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
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
   * @category Ritorna tutte le prenotazioni
   */
  @Override
  public Collection<PrenotazioneBean<String>> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM prenotazione";
    ArrayList<PrenotazioneBean<String>> collection = new ArrayList<>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        PrenotazioneBean<String> bean = new PrenotazioneBean<>();
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
        collection.add(bean);
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
    return collection;
  }

  /**
   * Metodo utilizzato per salvare i valori contenuti in un bean all'interno di una tabella.
   *
   * @param bean Prenotazione da salvare
   * @category Salva una prenotazione nel database
   */
  @Override
  public void doSave(PrenotazioneBean<String> bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO prenotazione VALUES(?,?,?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getIdentificativo().getIdentificativo());
      statement.setString(2, bean.getEmail());
      statement.setDate(3, bean.getDataPrenotazione());
      statement.setInt(4, bean.getSala());
      statement.setInt(5, bean.getFasciaOraria());
      statement.setBoolean(6, bean.isEntrato());
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
   * @param bean Prenotazione con contenuto aggiornato
   * @category Aggiorna una prenotazione
   */
  @Override
  public void doUpdate(PrenotazioneBean<String> bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE prenotazione SET dataPrenotazione=?, entrato = ? WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, bean.getDataPrenotazione());
      statement.setBoolean(2, bean.isEntrato());
      statement.setString(3, bean.getIdentificativo().getIdentificativo());
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
   * @category Cancella una prenotazione
   */
  @Override
  public void doDelete(PrenotazioneBean<String> bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM prenotazione WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getIdentificativo().getIdentificativo());
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

  @Override
  public PrenotazioneBean<String> doRetrieveByDateAndFascia(
      Date date, String email, int fasciaOraria) throws SQLException {
    PrenotazioneBean<String> bean = new PrenotazioneBean<>();
    Connection con = null;
    PreparedStatement statement = null;
    String sql =
        "SELECT * FROM prenotazione WHERE dataPrenotazione=? and email=? and fasciaOraria=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, date);
      statement.setString(2, email);
      statement.setInt(3, fasciaOraria);
      System.out.println("doRetrieveByDateAndFascia" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
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

  @Override
  public PrenotazioneBean<String> doRetrieveByDateAndMail(Date date, String email)
      throws SQLException {
    PrenotazioneBean<String> bean = new PrenotazioneBean<>();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM prenotazione WHERE dataPrenotazione=? and email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, date);
      statement.setString(2, email);
      System.out.println("doRetrieveByDateAndMail" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
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

  @Override
  public Collection<PrenotazioneBean<String>> doRetrieveByDateSalaFascia(
      Date date, int sala, int fascia) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql =
        "SELECT * FROM prenotazione WHERE dataPrenotazione=? and sala=? and fasciaOraria=?";
    ArrayList<PrenotazioneBean<String>> collection = new ArrayList<>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, date);
      statement.setInt(2, sala);
      statement.setInt(3, fascia);
      System.out.println("doRetrieveByDateSalaFascia" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        PrenotazioneBean<String> bean = new PrenotazioneBean<>();
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
        collection.add(bean);
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
    return collection;
  }
}
