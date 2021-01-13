package storage.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import business.consumatore.ConsumatoreBean;
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  PrintWriter out = response.getWriter();      
	  String transactionId=request.getParameter("transaction_id");
	  float importo= Float.parseFloat(request.getParameter("amount"));
	  ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("utente");
	  System.out.println("l'importo è" + importo);
	  System.out.println("\n l'id è" + transactionId);
	  
	  try {
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mensadigitale?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "esame", "esame");
	    Statement st = conn.createStatement();
        st.executeUpdate("UPDATE `mensadigitale`.`consumatore` SET `saldo` = \""+importo+"\" WHERE (`email` = \""+consumatore.getEmail()+"\");");
	  }
        catch (ClassNotFoundException | SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          out.println("<p>"+ e + "</p>");
          
      }
	  }
	  
	  
	  /* try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mensadigitale?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "esame", "esame");
        Statement st = conn.createStatement();
        float importo = Float.parseFloat(request.getParameter("importo"));
        System.out.println("<p>"+ importo + "</p>");
         il codice è sbagliato ma è solo per capire a livello logico if(PayPal.checkout = Done){
           conn = executequery(Update Saldo From consumatore where email =? ) e metto i request dispatcher forward e close  
        RequestDispatcher rds = request.getRequestDispatcher("Ricarica.jsp");
        rds.forward(request, response);
        st.close(); 
      } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        out.println("<p>"+ e + "</p>");
    }
*/
      
 
    

}




