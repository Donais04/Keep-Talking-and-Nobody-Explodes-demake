/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the wires module
 * Sources: none to the best of my memory
 */
public class wires extends module {

  private String[] wires;
  private int solution;

  public wires(bomb bomb) {
    super.b = bomb;
    wires = new String[(int) (Math.random() * 3 + 3)]; // sets a random amount of wires between 3 and 6
    final String[] colors = { "red", "yellow", "blue", "white", "black" };
    solution = -1;
    for (int i = 0; i < wires.length; i++) {
      wires[i] = colors[(int) (Math.random() * 4)];
    }
    // initializes a bunch of relevant variables
    int reds = 0;
    int blues = 0;
    int yellows = 0;
    int blacks = 0;
    int whites = 0;
    int lastBlue = -1;
    int lastRed = -1;
    for (int i = 0; i < wires.length; i++) { // sets previous variables up
      if (wires[i].equals("red")) {
        reds++;
        lastRed = i;
      } else if (wires[i].equals("blue")) {
        blues++;
        lastBlue = i;
      } else if (wires[i].equals("yellow")) {
        yellows++;
      } else if (wires[i].equals("black")) {
        blacks++;
      } else if (wires[i].equals("white")) {
        whites++;
      }
    }
    switch (wires.length) { // checks what the solution should be. Read manual for explanation of each
      case 3:
        if (reds == 0) {
          solution = 1;
        } else if (wires[2].equals("white")) {
          solution = 2;
        } else if (blues > 1) {
          solution = lastBlue + 1;
        } else {
          solution = 2;
        }
        break;
      case 4:
        if (reds > 1 && Character.isDigit(bomb.getSerial().charAt(7)) && bomb.getSerial().charAt(7) % 2 == 0) {
          solution = lastRed + 1;
        } else if (wires[3] == "yellow" && reds == 0) {
          solution = 0;
        } else if (blues == 1) {
          solution = 0;
        } else if (yellows > 1) {
          solution = 3;
        } else {
          solution = 1;
        }
        break;
      case 5:
        if (wires[4] == "black" && Character.isDigit(bomb.getSerial().charAt(7))
            && bomb.getSerial().charAt(7) % 2 == 1) {
          solution = 3;
        } else if (reds == 1 && yellows > 1) {
          solution = 0;
        } else if (blacks == 0) {
          solution = 1;
        } else {
          solution = 0;
        }
        break;
      case 6:
        if (yellows == 0 && Character.isDigit(bomb.getSerial().charAt(7)) && bomb.getSerial().charAt(7) % 2 == 1) {
          solution = 2;
        } else if (yellows == 1 && whites > 1) {
          solution = 3;
        } else if (reds == 0) {
          solution = 5;
        } else {
          solution = 4;
        }
    }

  }

  public void check() {
    while (true) {
      System.out.println("Your wires are:");
      for (int i = 0; i < wires.length; i++) {
        System.out.println(wires[i]);
      }
      System.out.println("Which one do you cut? Answer as an number (with 1 being the first wire)");
      String userInput = input.nextLine();
      if (stop) {
        return;
      }
      try {
        if (solution == (Integer.parseInt(userInput) - 1)) {
          break;
        } else if (Integer.parseInt(userInput) > wires.length || Integer.parseInt(userInput) - 1 < 0) {
          System.out.println("That is not a wire.");
        } else {
          super.b.strike();
        }
      } catch (NumberFormatException e) {
        System.out.println("Please input as an integer");
      }
    }
    super.clear();
  }

  public String toString() {
    return ("wires's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }
}
