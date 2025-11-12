/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the complex wires module
 * Sources: none to the best of my memory
 */
public class complexWires extends module {
  private String[] modifiers;
  private boolean[][] setupArray;
  private int[] answers;
  private int currWire;

  public complexWires(bomb bomb) {
    super.b = bomb;
    final String[] modHelper = { "LED", "red", "blue", "star" };
    modifiers = modHelper; // contains the string representation of the modifiers each wire can have
    setupArray = new boolean[6][4]; // the first axis is each wire, the second is which modifiers each has
    answers = new int[6]; // contains whether to cut each wire
    for (int i = 0; i < 6; i++) {
      final boolean[] setup = { Main.tf(), Main.tf(), Main.tf(), Main.tf() };
      int x, y;
      final int[][] board = { { 0, 2, 1, 3 }, { 2, 2, 4, 2 }, { 0, 1, 4, 3 }, { 0, 3, 4, 1 } };
      // contains which answers corospond to which combinations of modifiers
      // ... don't ask me how I made this
      if (setup[1]) { // this whole section checks what the answer should be
        if (setup[3]) {
          y = 3;
        } else {
          y = 1;
        }
      } else {
        if (setup[3]) {
          y = 2;
        } else {
          y = 0;
        }
      }
      if (setup[2]) {
        if (setup[0]) {
          x = 3;
        } else {
          x = 1;
        }
      } else {
        if (setup[0]) {
          x = 2;
        } else {
          x = 0;
        }
      }
      setupArray[i] = setup;
      answers[i] = board[y][x];
    }
    currWire = 0;
  }

  public void check() {
    for (currWire += 0; currWire < 6; currWire++) { // goes through wires
      System.out.println("The next wire is: ");
      for (int i = 0; i < 4; i++) { // prints which modifiers each wire has
        if (setupArray[currWire][i]) {
          System.out.println(modifiers[i]);
        }
      }
      boolean ans;
      while (true) { // gets answer
        try {
          System.out.println("Do you cut this wire (Y/N)?");
          String userInput = input.nextLine();
          if (stop) {
            return;
          }
          ans = (userInput.toLowerCase().charAt(0) == 'y');
          break;
        } catch (Exception e) {
        }
      }
      switch (answers[currWire]) {
        // checks if answer is correct. If practice mode is enabled, also prints which
        // answer would be correct
        case 0:
          if (Main.practiceMode) {
            System.out.println("c");
          }
          if (!ans) {
            super.b.strike();
          }
          break;
        case 1:
          if (Main.practiceMode) {
            System.out.println("d");
          }
          if (ans) {
            super.b.strike();
          }
          break;
        case 2:
          if (Main.practiceMode) {
            System.out.println("s");
          }
          // if the final digit of the serial number is even
          if (!((!ans && Character.isLetter(super.b.getSerial().charAt(7)))
              || ans == (super.b.getSerial().charAt(7) % 2 == 0))) {
            super.b.strike();
          }
          break;
        case 3:
          if (Main.practiceMode) {
            System.out.println("p");
          }
          boolean parPort = false;
          // if there's a parallel port
          for (int i = 0; i < super.b.getPorts().length; i++) {
            if (super.b.getPorts()[i].equals("Parallel")) {
              parPort = true;
            }
          }
          if (!(ans == parPort)) {
            super.b.strike();
          }
          break;
        case 4:
          if (Main.practiceMode) {
            System.out.println("b");
          }
          // if there's 2 or more batteries
          if (!(ans == (super.b.getBatteries() > 1))) {
            super.b.strike();
          }
          break;
        default:
          throw new ArithmeticException("No case found for wire");
      }
    }
    super.clear();
  }

  public String toString() {
    return ("complex wire's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }
}