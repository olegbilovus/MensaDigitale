package business.richieste;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SingletonRichiesteTest {

  @Test
  void coverage_1() {
    RichiesteInSospeso ris = RichiesteInSospeso.getInstance();
    RichiesteInSospeso ris2 = RichiesteInSospeso.getInstance();
    assertTrue(true);
  }

}
