package storage.manager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DriverManagerConnectionPoolTest {


  @Test
  void testReleaseConnection() {
    int connectionsNumber = DriverManagerConnectionPool.getFreeDbConnections().size();
    DriverManagerConnectionPool.releaseConnection(null);
    assertTrue(DriverManagerConnectionPool.getFreeDbConnections().size() == connectionsNumber);
  }

  @Test
  void testgetConnection() throws SQLException {
    Connection con = DriverManagerConnectionPool.getConnection();
    try {
      con.close();
      Connection con2 = DriverManagerConnectionPool.getConnection();

      assertTrue(con.isClosed() && !con2.isClosed());

      DriverManagerConnectionPool.releaseConnection(con2);  
    } finally {
      DriverManagerConnectionPool.releaseConnection(con);
    }
  }

}
