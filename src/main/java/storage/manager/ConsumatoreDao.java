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

/**
 * Classe DAO per la gestione di ConsumatoreBean
 */
public class ConsumatoreDao implements ConsumatoreInterface<ConsumatoreBean> {

  /**
   * Costruttore per ConsumatoreDao.
   */
  public ConsumatoreDao() {
  }

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @pre email e' non null
   * @post se l'entita' esiste nel database il valore di ritorno e' diverso da null
   * @param email email del consumatore da ricercare
   * @category Ricerca il consumatore in base all'email del consumatore
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
        bean.setSaldo(rs.getFloat("saldo"));
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
   * @post se la table corrispondente contiene entita', la lista di ritorno non e' vuota
   * @category Ritorna tutti i consumatori
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
        bean.setSaldo(rs.getFloat("saldo"));
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
   * @pre bean e' un ConsumatoreBean valido e non null
   * @post bean e' reso persistente nel database
   * @param bean Recensione da salvare
   * @category Salva una recensione nel database
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
      statement.setFloat(15, bean.getSaldo());
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
   * @pre bean e' un ConsumatoreBean valido, non null, gia' esistente nel database
   * @post l'entita' corrispondente nel database rispecchia lo stato di bean
   * @param bean Consumatore con contenuto aggiornato
   * @category Aggiorna un consumatore
   */
  @Override
  public void doUpdate(ConsumatoreBean bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE consumatore SET statoServizi=?, saldo=? WHERE email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setInt(1, bean.getStatoServizi());
      statement.setFloat(2, bean.getSaldo());
      statement.setString(3, bean.getEmail());
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
   * @pre bean e' un Administrator valido, non null, gia' esistente nel database
   * @post l'entita' corrispondente nel database viene eliminata
   * @param bean Indica il bean da eliminare
   * @category Cancella un consumatore
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
   * @pre codiceFiscale e' non null e dataIniziale e' non null
   * @post la lista di ritorno contiene strighe con informazioni sui consumatori entrati in contatto col consumatore che ha il codice fiscale uguale a codiceFiscale
   * @param codiceFiscale e' il codice fiscale del consumatore
   * @param dataIniziale  e' la data (14gg antecedente a quella odierna)
   * @category Trova tutti i consumatori entrati in contatto con il consumatore di cui si cerca il
   * codice fiscale Il risultato e' una lista di stringhe della forma:
   * nome|cognome|email|fasciaoraria|sala|data
   */
  public Collection<String> doRetrieveForTracciamento(String codiceFiscale, String dataIniziale) {
    Connection con = null;
    PreparedStatement statement = null;
    ArrayList<String> listaRisultati = new ArrayList<>();
    // codicefiscale, data, fascia, boolean
    String table = "consumatore c1 JOIN prenotazione p1 ON p1.email = c1.email";
    String dateMalate =
        "SELECT dataPrenotazione FROM " + table + " WHERE codicefiscale = ? AND entrato = true";
    String table2 =
        "(consumatore c2 JOIN prenotazione p2 on p2.email = c2.email) JOIN fasciaoraria f "
            + "ON p2.fasciaoraria = f.id";
    String consumatoriEntrati =
        "SELECT c2.email, nome, cognome, fascia, sala, dataPrenotazione, p2.entrato FROM "
            + table2
            + " WHERE dataPrenotazione in ("
            + dateMalate
            + ")";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(consumatoriEntrati);
      statement.setString(1, codiceFiscale);
      ResultSet results = statement.executeQuery();
      System.out.println("doRetrieveForTracciamento=" + statement);
      while (results.next()) {
        String dataPrenotazione = results.getString("dataPrenotazione");
        if (fourteenDaysBetween(dataIniziale, dataPrenotazione)) {
          if (results.getBoolean("p2.entrato")) {
            String risultato =
                String.join(
                    "|",
                    results.getString("nome"),
                    results.getString("cognome"),
                    results.getString("email"),
                    results.getString("fascia"),
                    results.getString("sala"),
                    results.getString("dataPrenotazione"));
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
    return !c2.before(c1);
  }

}
