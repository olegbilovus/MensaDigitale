package business.prenotazioni;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;

class QRcodeTest {

  @Test
  void testCreateQR() {
    QRCode qr = new QRCode(UUID.randomUUID().toString().replace("-", ""));

    try {
      BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
          new BufferedImageLuminanceSource(ImageIO.read(qr.createQR(400, 400)))));
      Result result = new MultiFormatReader().decode(binaryBitmap);

      assertTrue(qr.getIdentificativo().equals(result.getText()));
    } catch (WriterException | IOException | NotFoundException e) {
      e.printStackTrace();
    }
  }

}