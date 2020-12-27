package business.piatti;

import java.util.ArrayList;

public class Teeeest {

  public static void main(String[] args) {
    ArrayList<PiattoBean> tmp = new ArrayList<PiattoBean>();
    tmp.add(new PiattoBean("Piatto1", "Pane, Cipolla", 1, 1, 1, 1, 1));
    tmp.add(new PiattoBean("Cotoletta", "Pane, Cipolla", 1, 1, 1, 1, 1));
    tmp.add(new PiattoBean("Formaggio", "Pane, Cipolla", 1, 1, 1, 1, 1));
    System.out.println(tmp);
  }

}
