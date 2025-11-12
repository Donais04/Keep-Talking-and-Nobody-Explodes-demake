/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: allows for indicators to be more easily created
 * Sources: none to the best of my memory
 */
public class indicator {
  private String label;
  private boolean lit;

  public indicator(String la, boolean li) {
    label = la;
    lit = li;
  }

  public indicator() {
    String[] indics = { "SND", "CLR", "CAR", "IND", "FRQ", "SIG", "NSA", "MSA", "TRN", "BOB", "FRK" };
    label = indics[(int) (Math.random() * (indics.length - 1) + 1)];
    lit = Main.tf();

  }

  public String getLabel() {
    return label;
  }

  public boolean getLit() {
    return lit;
  }

  public String toString() {
    return (label + " is" + ((lit) ? ("n't") : ("")) + " lit");
  }
}
