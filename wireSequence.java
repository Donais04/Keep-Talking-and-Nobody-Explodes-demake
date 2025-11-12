/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the wire sequence module
 * Sources: none to the best of my memory
 */
public class wireSequence extends module {
  private String[] colorConnected;
  private boolean[] solution;
  private int currWire;

  public wireSequence(bomb bomb) {
    super.b = bomb;
    final String[] rbb = { "Red", "Blue", "Black" };
    final String[] abc = { "A", "B", "C" };
    final int[][][] key = { { { 3 }, { 2 }, { 1 }, { 1, 3 }, { 2 }, { 1, 3 }, { 1, 2, 3 }, { 1, 2 }, { 2 } },
        { { 2 }, { 1, 3 }, { 2 }, { 1 }, { 2 }, { 2, 3 }, { 3 }, { 1, 3 }, { 1 } },
        { { 1, 2, 3 }, { 1, 3 }, { 2 }, { 1, 3 }, { 2 }, { 2, 3 }, { 1, 2 }, { 3 }, { 3 } } }; // contains which wires
                                                                                               // can be cut
    int[] i = { 0, 0, 0 }; // counts how many of each wire there are
    int total = (int) (Math.random() * 14 + 4); // determines how many wires there will be, between 4 and 18
    int k = 0; // determines how many wires there currently are in the code
    colorConnected = new String[total];
    solution = new boolean[total];
    while (k < total) {
      int color = (int) (Math.random() * 3 + 1); // picks random color
      int connection = (int) (Math.random() * 3 + 1); // picks random connection
      solution[k] = false;
      for (int j = 0; j < key[color - 1][i[color - 1]].length; j++) { // for each posible cut
        if (key[color - 1][i[color - 1]][j] == connection) { // is the current connection a cut?
          solution[k] = true;
        }
      }
      colorConnected[k] = (rbb[color - 1] + " connected to " + abc[connection - 1]);
      i[color - 1]++;
      k++;
    }
    currWire = 0;
  }

  public void check() {
    while (currWire++ < solution.length) {
      System.out.println("The next wire is " + colorConnected[currWire] + ". Do you cut (Y/N).");
      String userInput = input.nextLine();
      if (stop) {
        return;
      }
      if (!((userInput.toLowerCase().charAt(0) == 'y') == solution[currWire])) {
        super.b.strike();
      }
    }
    super.clear();
  }

  public String toString() {
    return ("wire sequence's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }
}
