/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the password module
 * Sources: none to the best of my memory
 */
public class password extends module {
  private final String cPass;
  private char[][] display; // represents what could be displayed
  private int[] currDisplay; // represents what index of display is currently on the screen

  public password(bomb bomb) {
    super.b = bomb;
    final String[] passes = { "about", "after", "again", "below", "could", "every", "first", "found", "great", "house",
        "large", "learn", "never", "other", "place", "plant", "point", "right", "small", "sound", "spell",
        "still", "study", "their", "there", "these", "thing", "think", "three", "water", "where", "which",
        "world", "would", "write" };
    final char[] abcs = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
        't', 'u', 'v', 'w', 'x', 'y', 'z' };
    final char[][] display2 = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0 } };
    display = display2; // ensures all indexes of display are initialized to 0
    cPass = passes[(int) (Math.random() * passes.length)]; // picks a random word to be the correct pass
    for (int j = 0; j < 5; j++) {
      String usedLetters = cPass.charAt(j) + "";
      display[j][(int) (Math.random() * 6)] = cPass.charAt(j); // inserts the answer to a random space
      for (int i = 0; i < 5; i++) { // assigns random letters to the other 5/6 items in display[j]
        while (true) {
          int letterPicked = (int) (Math.random() * abcs.length);
          int k = (int) (Math.random() * 6);
          if (display[j][k] == 0 && !(usedLetters.contains(abcs[letterPicked] + ""))) {
            display[j][k] = abcs[letterPicked];
            usedLetters += abcs[letterPicked];
            break;

          }
        }
      }
    } // inserts the cPass's characters in
    for (int j = 0; j < 5; j++) {
      for (int i = 0; i < 6; i++) {
        if (display[j][i] == 0) {

        }
      }
    }
    currDisplay = new int[5];
    for (int j = 0; j < display.length; j++) { // makes display start in random order
      currDisplay[j] = (int) (Math.random() * 4);
    }
  }

  public void check() {
    for (int i = 0; i < 5; i++) { // displays current status
      System.out.print((display[i][currDisplay[i]] + " ").toUpperCase());
    }
    while (true) {
      System.out.println("\n\nPlease enter which index you'd like to scroll through.");
      String userInput = input.nextLine();
      if (stop) {
        return;
      }
      try { // scrolls down, unless it gets to the bottom, in which case it goes back to the
            // top
        currDisplay[Integer.parseInt(userInput) - 1]++;
        if (currDisplay[Integer.parseInt(userInput) - 1] > 5) {
          currDisplay[Integer.parseInt(userInput) - 1] = 0;
        }
      } catch (Exception e) {
        System.out.println("Please ender your answer as an integer between 1 and 5");
      }
      for (int i = 0; i < 5; i++) { // displays current status
        System.out.print((display[i][currDisplay[i]] + " ").toUpperCase());
      }
      boolean checking = true; // checks if you have gotten it correct
      for (int i = 0; i < 5; i++) {
        if (!(display[i][currDisplay[i]] == cPass.toCharArray()[i])) {
          checking = false;
        }
      }
      if (checking) {
        break;
      }
    }
    System.out.println("\n\n");
  }

  public String toString() {
    return ("password's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }
}
