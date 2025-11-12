/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the memory module
 * Sources: none to the best of my memory
 */
public class memory extends module {
  private int[] displays;
  private int[] answers;
  private int[][] buttons;

  public memory(bomb bomb) {
    super.b = bomb;
    if (true) {
      displays = new int[5];
      answers = new int[5];
      buttons = new int[5][4];
      for (int i = 0; i < 5; i++) {
        displays[i] = (int) (Math.random() * 3 + 1); // generates number for display
        for (int j = 4; j > 0; j--) { // ensures each number can only be assigned to one button
          int randIndex = (int) (Math.random() * 4); // generates a random index to slot j into
          if (buttons[i][randIndex] == 0) { // ints are always initialized to 0
            buttons[i][randIndex] = j;
          } else {
            j++;
          }
        }
      }
      switch (displays[0]) { // generates solution based on rulebook
        case 1:
          answers[0] = 2;
          break;
        case 2:
          answers[0] = 2;
          break;
        case 3:
          answers[0] = 3;
          break;
        case 4:
          answers[0] = 4;
          break;
      }
      switch (displays[1]) {
        case 1:
          answers[1] = Main.findIndex(buttons[1], 4) + 1;
          break;
        case 2:
          answers[1] = answers[0];
          break;
        case 3:
          answers[1] = 1;
          break;
        case 4:
          answers[1] = answers[0];
          break;
      }
      switch (displays[2]) {
        case 1:
          answers[2] = Main.findIndex(buttons[2], buttons[1][answers[1] - 1]) + 1;
          break;
        case 2:
          answers[2] = Main.findIndex(buttons[2], buttons[0][answers[0] - 1]) + 1;
          break;
        case 3:
          answers[2] = 3;
          break;
        case 4:
          answers[2] = Main.findIndex(buttons[2], 4) + 1;
          break;
      }
      switch (displays[3]) {
        case 1:
          answers[3] = answers[0];
          break;
        case 2:
          answers[3] = 1;
          break;
        case 3:
          answers[3] = answers[1];
          break;
        case 4:
          answers[3] = answers[1];
          break;
      }
      switch (displays[4]) {
        case 1:
          answers[4] = Main.findIndex(buttons[4], buttons[0][answers[0] - 1]) + 1;
          break;
        case 2:
          answers[4] = Main.findIndex(buttons[4], buttons[1][answers[1] - 1]) + 1;
          break;
        case 3:
          answers[4] = Main.findIndex(buttons[4], buttons[2][answers[2] - 1]) + 1;
          break;
        case 4:
          answers[4] = Main.findIndex(buttons[4], buttons[3][answers[3] - 1]) + 1;
          break;
      }
    }
  }

  public void check() {
    for (int i = 0; i < 5; i += 0) {
      System.out.print("\nThe display reads " + displays[i] + "\nand the buttons are ");
      for (int j = 0; j < 4; j++) {
        System.out.print(buttons[i][j]);
      }
      System.out.println(
          "\nWhat button will you push? Answer as a number, with 1 being the number furthest left and 4 being the number furthest right.");
      try {
        String userInput = input.nextLine();
        if (stop) {
          return;
        }
        if (Integer.parseInt(userInput) == answers[i]) {
          i++;
        } else {
          super.b.strike();
        }
      } catch (NumberFormatException e) {
        System.out.println("Please input your answer as an integer");
      }
    }
    super.clear();
  }

  public String toString() {
    return ("memory's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }
}
