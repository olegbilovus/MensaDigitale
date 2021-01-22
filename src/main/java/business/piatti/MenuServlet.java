package business.piatti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import storage.manager.PiattoDao;

/**
 * Servlet implementation class MenuServlet.
 */
public class MenuServlet extends HttpServlet {

  private final PiattoDao dao = new PiattoDao();
  private final File myFile;
  private ObjectInputStream inputStream;
  private ObjectOutputStream outputStream;
  private MenuBean menu;

  public MenuServlet() {
    myFile = new File("menuSerializzato.txt");
    try {
      myFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("action");

    if (action != null) {
      switch (action) {
        case "aggiungiMenu":
          apriStreamsOutput();
          menu = new MenuBean();
          String[] piatti = request.getParameterValues("piatti");
          aggiungiPiatti(piatti, menu);
          outputStream.writeObject(menu);
          request.setAttribute("menu", menu);
          outputStream.close();
          response.sendRedirect(request.getContextPath() + "/index.jsp");
          break;
        case "modificaMenu":
          /* Rimuovo piatti vecchi */
          rimuoviMenu(request, response);
          /* Aggiungo i nuovi piatti */
          String[] piattiDaAggiungere = request.getParameterValues("piatti");
          aggiungiPiatti(piattiDaAggiungere, menu);
          apriStreamsOutput();
          outputStream.writeObject(menu);
          outputStream.close();
          request.setAttribute("menu", menu);
          response.sendRedirect(request.getContextPath() + "/index.jsp");
          break;
        case "cancellaMenu":
          rimuoviMenu(request, response);
          response.sendRedirect("./visualizzaMenu.jsp");
          break;
        case "getMenu":
          try {
            apriStreamsInput();
            menu = (MenuBean) inputStream.readObject();
            System.out.println(menu);
            request.setAttribute("menu", menu);
            inputStream.close();
            String destination = request.getParameter("destination");
            request.getRequestDispatcher(response.encodeURL(destination))
                .forward(request, response);
            return;
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
          break;
      }
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  private void aggiungiPiatti(String[] piatti, MenuBean menu) {
    for (String p : piatti) {
      try {
        PiattoBean tmp = dao.doRetrieveByKey(p);
        System.out.println("Nome piatto: " + tmp.getNome());
        System.out.println("Portata piatto: " + tmp.getPortata());
        switch (tmp.getPortata()) {
          case "primo" -> menu.addPrimo(tmp);
          case "secondo" -> menu.addSecondo(tmp);
          case "contorno" -> menu.addContorno(tmp);
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }

  private void rimuoviMenu(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    menu = new MenuBean();
    apriStreamsOutput();
    outputStream.writeObject(menu);
    request.setAttribute("menu", menu);
    outputStream.close();
  }

  private void apriStreamsInput() throws IOException {
    FileInputStream fileInput = new FileInputStream(myFile);
    inputStream = new ObjectInputStream(fileInput);
  }

  private void apriStreamsOutput() throws IOException {
    FileOutputStream fileOutput = new FileOutputStream(myFile, false);
    outputStream = new ObjectOutputStream(fileOutput);
  }

}
