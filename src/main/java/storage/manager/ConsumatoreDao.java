package storage.manager;

import business.consumatore.ConsumatoreBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import storage.interfaces.ConsumatoreInterface;

public class ConsumatoreDao implements ConsumatoreInterface<ConsumatoreBean> {


  /**
   * Costruttore per ConsumatoreDao.
   */
  public ConsumatoreDao() {}

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   * 
   * @category Ricerca il consumatore in base all'email del consumatore
   * 
   * @param email email del consumatore da ricercare
   */

  @Override
  public ConsumatoreBean doRetrieveByKey(String email) throws SQLException {
    ConsumatoreBean bean = null;
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM consumatore WHERE email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, email);
      System.out.println("DoRetrieveByKey" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean = new ConsumatoreBean();
        bean.setEmail(rs.getString("email"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setStatoServizi(rs.getInt("statoServizi"));
        bean.setCodiceFiscale(rs.getString("codiceFiscale"));
        bean.setDataDiNascita(rs.getDate("dataDiNascita"));
        bean.setIndirizzo(rs.getString("indirizzo"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setCellulare(rs.getString("cellulare"));
        bean.setComuneNascita(rs.getString("comuneNascita"));
        bean.setProvinciaNascita(rs.getString("provinciaNascita"));
        bean.setCittadinanza(rs.getString("cittadinanza"));
        bean.setRifugiato(rs.getBoolean("rifugiato"));
        bean.setResidenzaNucleoFamiliare(rs.getBoolean("residenzaNucleoFamiliare"));
        bean.setSaldo(rs.getInt("saldo"));
        bean.setFasciaPagamento(rs.getInt("fasciaPagamento"));
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
   * @category Ritorna tutti i consumatori
   * 
   */

  @Override
  public Collection<ConsumatoreBean> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM consumatore";
    ArrayList<ConsumatoreBean> collection = new ArrayList<>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        ConsumatoreBean bean = new ConsumatoreBean();
        bean.setEmail(rs.getString("email"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setStatoServizi(rs.getInt("statoServizi"));
        bean.setCodiceFiscale(rs.getString("codiceFiscale"));
        bean.setDataDiNascita(rs.getDate("dataDiNascita"));
        bean.setIndirizzo(rs.getString("indirizzo"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setCellulare(rs.getString("cellulare"));
        bean.setComuneNascita(rs.getString("comuneNascita"));
        bean.setProvinciaNascita(rs.getString("provinciaNascita"));
        bean.setCittadinanza(rs.getString("cittadinanza"));
        bean.setRifugiato(rs.getBoolean("rifugiato"));
        bean.setResidenzaNucleoFamiliare(rs.getBoolean("residenzaNucleoFamiliare"));
        bean.setSaldo(rs.getInt("saldo"));
        bean.setFasciaPagamento(rs.getInt("fasciaPagamento"));
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
  public void doSave(ConsumatoreBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO consumatore VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getEmail());
      statement.setString(2, bean.getNome());
      statement.setString(3, bean.getCognome());
      statement.setInt(4, bean.getStatoServizi());
      statement.setString(5, bean.getCodiceFiscale());
      if (bean.getDataDiNascita() != null) {
        statement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(bean.getDataDiNascita()));
      } else {
        statement.setString(6, null);
      }
      statement.setString(7, bean.getIndirizzo());
      statement.setString(8, bean.getTelefono());
      statement.setString(9, bean.getCellulare());
      statement.setString(10, bean.getComuneNascita());
      statement.setString(11, bean.getProvinciaNascita());
      statement.setString(12, bean.getCittadinanza());
      statement.setBoolean(13, bean.getRifugiato());
      statement.setBoolean(14, bean.getResidenzaNucleoFamiliare());
      statement.setInt(15, bean.getSaldo());
      statement.setInt(16, bean.getFasciaPagamento());
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
   * @category Aggiorna un consumatore
   * 
   * @param bean Consumatore con contenuto aggiornato
   */

  @Override
  public void doUpdate(ConsumatoreBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE consumatore SET statoServizi=? WHERE email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, bean.getStatoServizi());
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
   * @category Cancella un consumatore
   * 
   * @param bean Indica il bean da eliminare
   */

  @Override
  public void doDelete(ConsumatoreBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM consumatore WHERE email=?";
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

  /**
   * Metodo utilizzato per effettuare il tracciamento dei contatti.
   * 
   * @category Trova tutti i consumatori entrati in contatto con il consumatore di cui si cerca il
   *           codice fiscale Il risultato e' una lista di stringhe della forma:
   *           nome|cognome|email|fasciaoraria|sala|data
   * 
   * @param codiceFiscale e' il codice fiscale del consumatore
   * @param dataIniziale e' la data (14gg antecedente a quella odierna)
   */
  public Collection<String> doRetrieveForTracciamento(String codiceFiscale, String dataIniziale) {
    Connection con = null;
    PreparedStatement statement = null;
    ArrayList<String> listaRisultati = new ArrayList<>();
    // codicefiscale, data, fascia, boolean
    String table = "consumatore c1 JOIN prenotazione p1 ON p.email = c.email";
    String dateMalate =
        "SELECT dataPrenotazione FROM " + table + " WHERE codicefiscale = ? AND entrato = true";
    String table2 =
        "(consumatore c2 JOIN prenotazione p2 on p.email = c.email) pr JOIN fasciaoraria f "
            + "ON pr.fasciaoraria = f.id";
    String consumatoriEntrati =
        "SELECT email, nome, cognome, fascia, sala, dataPrenotazione, c2.entrato FROM " + table2
            + " WHERE dataPrenotazione in (" + dateMalate + ")";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(consumatoriEntrati);
      statement.setString(1, codiceFiscale);
      ResultSet results = statement.executeQuery();
      System.out.println("doRetrieveForTracciamento=" + statement);
      while (results.next()) {
        String dataPrenotazione = results.getString("dataPrenotazione");
        if (fourteenDaysBetween(dataIniziale, dataPrenotazione)) {
          if (results.getBoolean("c2.entrato")) {
            String risultato =
                String.join("|", results.getString("nome"), results.getString("cognome"),
                    results.getString("email"), results.getString("fascia"),
                    results.getString("sala"), results.getString("dataPrenotazione"));
            listaRisultati.add(risultato);
          }
        }
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
    return listaRisultati;
  }

  private boolean fourteenDaysBetween(String data1, String data2) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date dataInizio = df.parse(data1);
    Date dataPrenotazione = df.parse(data2);
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(dataInizio);
    c2.setTime(dataPrenotazione);
    if (c2.before(c1)) {
      return false;
    }
    return true;
  }
}
