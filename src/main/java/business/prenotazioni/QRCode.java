package business.prenotazioni;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class QRCode implements Identificativo<String> {

  private String identificativo;

  /**
   * Costruttore utilizzato per la creazione del QRCode.
   * 
   * @param identificativo la stringa che rappresente l'identificativo
   */
  public QRCode(String identificativo) {
    this.identificativo = identificativo;
  }

  public String getIdentificativo() {
    return this.identificativo;
  }

  public String setIdentificativo(String identificativo) {
    this.identificativo = identificativo;
    return this.identificativo;
  }

  @Override
  public String toString() {
    return this.getClass().getName() + "[identificativo=" + identificativo + "]";
  }

  /**
   * Create a QRCode and get the bytes.
   * 
   * @param height l'altezza del QRCode
   * @param width la largezza del QRCode
   * @return array di byte del QRCode
   * @throws WriterException errore nella scrittura
   * @throws IOException errore di IO
   * @post InputStream.string = identificativo
   */
  public static InputStream createQR(String identificativo, int height, int width)
      throws WriterException, IOException {

    Map<EncodeHintType, ErrorCorrectionLevel> hashMap =
        new HashMap<EncodeHintType, ErrorCorrectionLevel>();
    hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

    String charset = "UTF-8";

    BitMatrix matrix =
        new MultiFormatWriter().encode(new String(identificativo.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, width, height);

    File file = new File(identificativo);

    MatrixToImageWriter.writeToPath(matrix, "png", file.toPath());

    byte[] dataByte = Files.readAllBytes(file.toPath());
    file.delete();

    return new ByteArrayInputStream(dataByte);
  }

}
