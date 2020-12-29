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
   * @category Ricerca la prenotazione in base all'id della prenotazione
   *
   * @param id id della prenotazione da ricercare
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
   *
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
   * @category Salva una prenotazione nel database
   *
   * @param bean Prenotazione da salvare
   */

  @Override
  public void doSave(PrenotazioneBean<String> bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO prenotazione VALUES(?,?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getIdentificativo().getIdentificativo());
      statement.setString(2, bean.getEmail());
      statement.setDate(3, bean.getDataPrenotazione());
      statement.setInt(4, bean.getSala());
      statement.setInt(5, bean.getFasciaOraria());
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
   * @category Aggiorna una prenotazione
   *
   * @param bean Prenotazione con contenuto aggiornato
   */

  @Override
  public void doUpdate(PrenotazioneBean<String> bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE prenotazione SET dataPrenotazione=? WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, bean.getDataPrenotazione());
      statement.setString(2, bean.getIdentificativo().getIdentificativo());
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
   * @category Cancella una prenotazione
   *
   * @param bean Indica il bean da eliminare
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
  public PrenotazioneBean<String> doRetrieveByDateAndFascia(Date date, String email,
      int fasciaOraria) throws SQLException {
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

}
