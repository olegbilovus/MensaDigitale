package business.piatti;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.PiattoDao;

/**
 * Servlet implementation class PiattoServlet.
 */
public class PiattoServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public PiattoServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String destination = request.getParameter("destination");
    if (destination == null) {
      destination = "index.jsp";
    }

    String action = request.getParameter("action");
    System.out.println("Action is " + action);
    PiattoDao dao = new PiattoDao();

    if (action != null) {
      switch (action) {
        case "aggiungiPiatto": {
          PiattoBean newPiatto = createNewPiatto(request, response);
          try {
            dao.doSave(newPiatto);
            if (response.getWriter() != null) {
              PrintWriter out = response.getWriter();
              out.println("<script type=\"text/javascript\">");
              out.println("alert(\"Il piatto si trova ora nel database!\")");
              out.println("window.location.href = \"compilaMenu.jsp\"");
              out.println("</script>");
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
          break;
        }
        case "modificaPiatto": {
          PiattoBean newPiatto = createNewPiatto(request, response);
          try {
            dao.doUpdate(newPiatto);
          } catch (SQLException e) {
            e.printStackTrace();
          }
          response.sendRedirect(request.getContextPath() + "/index.jsp");
          break;
        }
        case "rimuoviPiatto": {
          PiattoBean newPiatto = createNewPiatto(request, response);
          try {
            dao.doDelete(newPiatto);
          } catch (SQLException e) {
            e.printStackTrace();
          }
          response.sendRedirect(request.getContextPath() + "/index.jsp");
          break;
        }
        case "getPiatto": {
          String nomePiatto = request.getParameter("nomePiatto");
          try {
            PiattoBean newPiatto = dao.doRetrieveByKey(nomePiatto);
            request.setAttribute("piatto", newPiatto);
            request.getRequestDispatcher(destination).forward(request, response);
          } catch (SQLException e) {
            e.printStackTrace();
          }
          break;
        }
        case "getTuttiPiatti": {
          try {
            ArrayList<PiattoBean> list = new ArrayList<PiattoBean>(dao.doRetrieveAll());
            request.setAttribute("tuttiPiatti", list);
            request.getRequestDispatcher(response.encodeURL(destination)).forward(request,
                response);
          } catch (SQLException e) {
            e.printStackTrace();
          }
          break;
        }
        default:
          throw new IllegalArgumentException();
      }
    }
  }

  private PiattoBean createNewPiatto(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String nomePiatto = request.getParameter("nomePiatto");
    String ingredienti = request.getParameter("ingredienti");
    String portata = request.getParameter("portata");
    int calorie = Integer.parseInt(request.getParameter("calorie"));
    int proteine = Integer.parseInt(request.getParameter("proteine"));
    int grassi = Integer.parseInt(request.getParameter("grassi"));
    int sodio = Integer.parseInt(request.getParameter("sodio"));
    int carboidrati = Integer.parseInt(request.getParameter("carboidrati"));
    if (ingredienti == null || ingredienti.length() < 4 || ingredienti.length() > 200
        || !ingredienti.matches("^([A-Z,])+$") || calorie < 1 || calorie > 2000 || proteine < 0
        || grassi < 0 || sodio < 0 || carboidrati < 0) {
      throw new IllegalArgumentException();
    }
    if (nomePiatto == null || nomePiatto.trim().equals("")) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST,
          "Errori nei parametri della richiesta!");
      throw new IllegalArgumentException();
    } else {
      return new PiattoBean(nomePiatto, ingredienti, portata, calorie, proteine, grassi, sodio,
          carboidrati);
    }
  }
}
