package business.prenotazioni;

import com.google.zxing.WriterException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QRCodeServlet.
 */
public class QRCodeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("image/png");
    PrenotazioneBean<String> prenotazione =
        (PrenotazioneBean<String>) request.getSession().getAttribute("prenotazione");
    try {
      byte[] qr =
          QRCode.createQR(
              prenotazione.getIdentificativo().getIdentificativo(),
              Integer.parseInt(request.getParameter("height")),
              Integer.parseInt(request.getParameter("width")));
      response.setContentLength(qr.length);
      response.getOutputStream().write(qr);
    } catch (WriterException | IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
