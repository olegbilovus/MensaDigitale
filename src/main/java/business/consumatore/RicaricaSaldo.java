package business.consumatore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.ConsumatoreDao;

/**
 * Servlet implementation class RicaricaSaldo
 */
public class RicaricaSaldo extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public RicaricaSaldo() {
    super();
    // TODO Auto-generated constructor stub
  }

  private static ConsumatoreDao dao = new ConsumatoreDao();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String transactionId = request.getParameter("transaction_id");
    float importo = Float.parseFloat(request.getParameter("amount"));
    ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("utente");
    System.out.println("l'importo è" + importo);
    System.out.println("\n l'id è" + transactionId);

    consumatore.setSaldo(consumatore.getSaldo() + importo);

    try {
      dao.doUpdate(consumatore);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }



}


