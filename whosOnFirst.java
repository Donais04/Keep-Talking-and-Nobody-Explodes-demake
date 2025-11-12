
/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the who's on first module
 * Sources: none to the best of my memory
 */
import java.util.HashMap;
import java.util.Map;

public class whosOnFirst extends module {
  private String[] displays;
  private String[][] buttons;
  private int[] answers;
  private int currStage;

  public whosOnFirst(bomb bomb) {
    super.b = bomb;
    final String[] displayWords = { "", "BLANK", "C", "CEE", "DISPLAY", "FIRST", "HOLD ON", "LEAD", "LED", "LEED", "NO",
        "NOTHING", "OKAY", "READ", "RED", "REED", "SAYS", "SEE", "THEIR", "THERE", "THEY ARE", "THEY'RE", "UR",
        "YES", "YOU", "YOU ARE", "YOUR", "YOU'RE" };
    final String[] buttWords = { "READY", "FIRST", "NO", "BLANK", "NOTHING", "YES", "WHAT", "UHHH", "LEFT", "RIGHT",
        "MIDDLE", "OKAY", "WAIT", "PRESS", "YOU", "YOU ARE", "YOUR", "YOU'RE", "UR", "U", "UH HUH", "UH UH",
        "WHAT?", "DONE", "NEXT", "HOLD", "SURE", "LIKE" };
    Map<String, Integer> displayStrings = new HashMap<String, Integer>(); // maps the string to the button needed to be
                                                                          // read
    displayStrings.put("", 5);
    displayStrings.put("BLANK", 4);
    displayStrings.put("C", 2);
    displayStrings.put("CEE", 6);
    displayStrings.put("DISPLAY", 6);
    displayStrings.put("FIRST", 2);
    displayStrings.put("HOLD ON", 6);
    displayStrings.put("LEAD", 6);
    displayStrings.put("LED", 3);
    displayStrings.put("LEED", 5);
    displayStrings.put("NO", 6);
    displayStrings.put("NOTHING", 3);
    displayStrings.put("OKAY", 2);
    displayStrings.put("READ", 4);
    displayStrings.put("RED", 4);
    displayStrings.put("REED", 5);
    displayStrings.put("SAYS", 6);
    displayStrings.put("SEE", 6);
    displayStrings.put("THEIR", 4);
    displayStrings.put("THERE", 6);
    displayStrings.put("THEY ARE", 3);
    displayStrings.put("THEY'RE", 5);
    displayStrings.put("UR", 1);
    displayStrings.put("YES", 3);
    displayStrings.put("YOU", 4);
    displayStrings.put("YOU ARE", 6);
    displayStrings.put("YOUR", 4);
    displayStrings.put("YOU'RE", 6);
    HashMap<String, String[]> wordList = new HashMap<String, String[]>(); // Just... look at the second page of the
                                                                          // manual to explain this
    wordList.put("READY",
        new String[] { "YES", "OKAY", "WHAT", "MIDDLE", "LEFT", "PRESS", "RIGHT", "BLANK", "READY" });
    wordList.put("FIRST", new String[] { "LEFT", "OKAY", "YES", "MIDDLE", "NO", "RIGHT", "NOTHING", "UHHH",
        "WAIT", "READY", "BLANK", "WHAT", "PRESS", "FIRST" });
    wordList.put("NO", new String[] { "BLANK", "UHHH", "WAIT", "FIRST", "WHAT", "READY", "RIGHT", "YES",
        "NOTHING", "LEFT", "PRESS", "OKAY", "NO" });
    wordList.put("BLANK", new String[] { "WAIT", "RIGHT", "OKAY", "MIDDLE", "BLANK" });
    wordList.put("NOTHING", new String[] { "UHHH", "RIGHT", "OKAY", "MIDDLE", "YES", "BLANK", "NO", "PRESS",
        "LEFT", "WHAT", "WAIT", "FIRST", "NOTHING" });
    wordList.put("YES",
        new String[] { "OKAY", "RIGHT", "UHHH", "MIDDLE", "FIRST", "WHAT", "PRESS", "READY", "NOTHING", "YES" });
    wordList.put("WHAT", new String[] { "UHHH", "WHAT" });
    wordList.put("UHHH", new String[] { "READY", "NOTHING", "LEFT", "WHAT", "OKAY", "YES", "RIGHT", "NO", "PRESS",
        "BLANK", "UHHH" });
    wordList.put("LEFT", new String[] { "RIGHT", "LEFT" });
    wordList.put("RIGHT", new String[] { "YES", "NOTHING", "READY", "PRESS", "NO", "WAIT", "WHAT", "RIGHT" });
    wordList.put("MIDDLE",
        new String[] { "BLANK", "READY", "OKAY", "WHAT", "NOTHING", "PRESS", "NO", "WAIT", "LEFT", "MIDDLE" });
    wordList.put("OKAY", new String[] { "MIDDLE", "NO", "FIRST", "YES", "UHHH", "NOTHING", "WAIT", "OKAY" });
    wordList.put("WAIT",
        new String[] { "UHHH", "NO", "BLANK", "OKAY", "YES", "LEFT", "FIRST", "PRESS", "WHAT", "WAIT" });
    wordList.put("PRESS", new String[] { "RIGHT", "MIDDLE", "YES", "READY", "PRESS" });
    wordList.put("YOU",
        new String[] { "SURE", "YOU ARE", "YOUR", "YOU'RE", "NEXT", "UH HUH", "UR", "HOLD", "WHAT?", "YOU" });
    wordList.put("YOU ARE", new String[] { "YOUR", "NEXT", "LIKE", "UH HUH", "WHAT?", "DONE", "UH UH", "HOLD",
        "YOU", "U", "YOU'RE", "SURE", "UR", "YOU ARE" });
    wordList.put("YOUR", new String[] { "UH UH", "YOU ARE", "UH HUH", "YOUR" });
    wordList.put("YOU'RE", new String[] { "YOU", "YOU'RE" });
    wordList.put("UR", new String[] { "DONE", "U", "UR" });
    wordList.put("U", new String[] { "UH HUH", "SURE", "NEXT", "WHAT?", "YOU'RE", "UR", "UH UH", "DONE" });
    wordList.put("UH HUH", new String[] { "UH HUH" });
    wordList.put("UH UH", new String[] { "UR", "U", "YOU ARE", "YOU'RE", "NEXT", "UH UH" });
    wordList.put("WHAT?", new String[] { "YOU", "HOLD", "YOU'RE", "YOUR", "U", "DONE", "UH UH", "LIKE", "YOU ARE",
        "UH HUH", "UR", "NEXT", "WHAT?" });
    wordList.put("DONE", new String[] { "SURE", "UH HUH", "NEXT", "WHAT?", "YOUR", "UR", "YOU'RE", "HOLD", "LIKE",
        "YOU", "U", "YOU ARE", "UH UH", "DONE" });
    wordList.put("NEXT", new String[] { "WHAT?", "UH HUH", "UH UH", "YOUR", "HOLD", "SURE", "NEXT" });
    wordList.put("HOLD",
        new String[] { "YOU ARE", "U", "DONE", "UH UH", "YOU", "UR", "SURE", "WHAT?", "YOU'RE", "NEXT", "HOLD" });
    wordList.put("SURE",
        new String[] { "YOU ARE", "DONE", "LIKE", "YOU'RE", "YOU", "HOLD", "UH HUH", "UR", "SURE" });
    wordList.put("SURE",
        new String[] { "YOU'RE", "NEXT", "U", "UR", "HOLD", "DONE", "UH UH", "WHAT?", "UH HUH", "YOU", "LIKE" });
    displays = new String[3];
    buttons = new String[3][6];
    answers = new int[3];
    for (int i = 0; i < 3; i++) {
      displays[i] = displayWords[(int) (Math.random() * (displayWords.length - 1))];// picks a random word to display
      String[] tempButtWords = buttWords; // prepares a copy of button words
      for (int j = 0; j < 6; j++) {
        int rand = (int) (Math.random() * (buttWords.length - 1)); // selects a random button word
        if (!(tempButtWords[rand] == null)) { // makes sure the word hasn't already been used
          buttons[i][j] = tempButtWords[rand]; // sets a button to a random button word
          tempButtWords[rand] = null; // removes the word from the list
        } else {
          j--;
        }
      }
      boolean breakAll = false; // this section gets what the answer should be, in an admittedly convoluted way.
                                // I'm not gonna even try to explain it
      String[] list = wordList.get(buttons[i][displayStrings.get(displays[i]) - 1]);
      for (int j = 0; j < list.length; j++) {
        for (int k = 0; k < 6; k++) {
          if (buttons[i][k].equals(list[j])) {
            answers[i] = k;
            breakAll = true;
            break;
          }
        }
        if (breakAll) {
          break;
        }
      }
    }
    currStage = 0;

  }

  public void check() {
    while (currStage < 3) {
      System.out.println("The word on the display is " + displays[currStage] + "\nand the buttons are\n"
          + buttons[currStage][0] + "  " + buttons[currStage][1] + "\n"
          + buttons[currStage][2] + "  " + buttons[currStage][3] + "\n"
          + buttons[currStage][4] + "  " + buttons[currStage][5]
          + "\n\nWhat button will you push? Answer as a number, with 1 being the top left and 6 being the bottom right.");
      String userInput = input.nextLine();
      if (stop) {
        return;
      }
      try {
        if ((Integer.parseInt(userInput) - 1) == answers[currStage]) {
          currStage++; // progresses
        } else if ((Integer.parseInt(userInput) - 1) > 5 || (Integer.parseInt(userInput) - 1) < 0) {
          super.b.strike();
        }
      } catch (NumberFormatException e) {
        System.out.println("please input as an integer");
      }
    }
    super.clear();
  }

  public String toString() {
    return ("who's on first's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }
}
