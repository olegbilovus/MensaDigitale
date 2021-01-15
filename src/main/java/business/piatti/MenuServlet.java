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
          break;
        case "modificaMenu":
          apriStreamsInput();
          try {
            menu = (MenuBean) inputStream.readObject();
            /* Rimuovo piatti vecchi */
            String[] piattiDaRimuovere = request.getParameterValues("piattiDaRimuovere");
            rimuoviPiatti(piattiDaRimuovere, menu);
            inputStream.close();
            /* Aggiungo i nuovi piatti */
            apriStreamsOutput();
            String[] piattiDaAggiungere = request.getParameterValues("piattiDaAggiungere");
            aggiungiPiatti(piattiDaAggiungere, menu);
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
          outputStream.writeObject(menu);
          request.setAttribute("menu", menu);
          outputStream.close();
          break;
        case "cancellaMenu":
          apriStreamsOutput();
          outputStream.writeObject(new MenuBean());
          request.setAttribute("menu", menu);
          outputStream.close();
          break;
        case "getMenu":
          try {
            apriStreamsInput();
            menu = (MenuBean) inputStream.readObject();
            System.out.println(menu);
            request.setAttribute("menu", menu);
            inputStream.close();
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

  private void rimuoviPiatti(String[] piattiDaRimuovere, MenuBean menu) {
    for (String p : piattiDaRimuovere) {
      try {
        PiattoBean tmp = dao.doRetrieveByKey(p);
        switch (tmp.getPortata()) {
          case "primo" -> menu.removePrimo(tmp);
          case "secondo" -> menu.removeSecondo(tmp);
          case "contorno" -> menu.removeContorno(tmp);
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
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
