/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the keypad module
 * Sources: none to the best of my memory
 */
public class keypad extends module {
  private String[] display;
  private int[] answer;

  public keypad(bomb bomb) {
    super.b = bomb;
    final String[][] table = {
        { "balloon", "at", "upsidedowny", "squigglyn", "squidknife", "hookn",
            "leftc" },
        { "euro", "head", "leftc", "cursive", "hollowstar", "hookn",
            "question" },
        { "copyright", "pumpkin", "cursive", "doublek", "meltedthree",
            "upsidedowny", "hollowstar" },
        { "six", "paragraph", "bt", "squidknife", "doublek",
            "questionmark", "smileyface" },
        { "pitchfork", "smileyface", "bt",
            "rightc", "paragraph", "dragon", "filledstar" },
        { "six", "euro", "tracks", "ae", "pitchfork", "nwithhat", "omega" } };
    int selection = (int) (Math.random() * 5); // selects random column
    display = new String[4];
    int[] rowStorage = new int[4];
    for (int i = 0; i < 4; i++) {
      int selection2 = (int) (Math.random() * 6); // selects random item from selection column
      if (!(table[selection][selection2].equals(""))) { // checks if item has already been picked
        display[i] = table[selection][selection2]; // puts item on display
        rowStorage[i] = selection2; // stores the row number of each selection
        table[selection][selection2] = ""; // marks item as picked
      } else {
        i--; // makes selection2 pick a new item
      }
    }
    answer = new int[4];
    int counter = 0;
    for (int i = 0; i < 7; i++) { // creates an int array of the order of numbers
      int temp = Main.findIndex(rowStorage, i);
      if (!(temp == -1)) { // findIndex returns -1 when it's not found
        answer[counter] = temp;
        counter++;
      }
    }
    // changes display from word to Ascii
    final String[][] asciiTable = { { "copyright",
        "                    \n                    \n      ■■■■■■■■■     \n    ■■■■■■■■■ ■■    \n    ■ ■■■   ■  ■■   \n    ■■■■■   ■  ■■   \n    ■■■■■■■■■ ■■    \n      ■■■■■■■■■     \n          ■         \n                    " },
        { "filledstar",
            "                    \n                    \n         ■■         \n         ■■■        \n    ■■■■■■■■■■■■    \n      ■■■■■■■■      \n       ■■■■■■■      \n      ■■    ■■      \n                    \n                    " },
        { "hollowstar",
            "                    \n                    \n         ■■         \n         ■■■        \n    ■■■■■■ ■■■■■    \n      ■■    ■■      \n       ■■■■■■■      \n      ■■    ■■      \n                    \n                    " },
        { "smileyface",
            "                    \n                    \n                    \n        ■■■■   ■    \n   ■■■        ■■■   \n    ■■■■■■■■■■■■    \n      ■■■■■■■       \n        ■■■         \n                    \n                    " },
        { "doublek",
            "                    \n                    \n   ■■■■ ■■■■■ ■■■   \n    ■ ■■ ■■■ ■■ ■   \n       ■■■■■■■      \n      ■■■■■■■■■     \n     ■■■ ■■■ ■■■    \n   ■■■■ ■■■■■ ■■■■  \n                ■■  \n                ■   " },
        { "omega",
            "                    \n                    \n      ■■■■■■■■      \n    ■■■     ■■■     \n    ■■       ■■     \n    ■■■     ■■■     \n    ■ ■■■ ■■■■■     \n    ■■■■■ ■■■■■     \n                    \n                    " },
        { "squidknife",
            "                    \n   ■■  ■■■■■■■■■    \n   ■■  ■■■■■■■■■■   \n   ■■   ■■■ ■■■     \n   ■■■■■■■■■■■      \n   ■■  ■■■■■■■■■    \n   ■■ ■■■  ■■ ■■■   \n   ■■ ■■   ■■  ■■   \n                    \n                    " },
        { "pumpkin",
            "                    \n       ■■■■■■       \n       ■ ■■■■       \n   ■■■   ■■    ■■   \n  ■■■           ■■  \n  ■■            ■■■ \n  ■■    ■■■■     ■■ \n  ■■■■■■■■■■■■■■■■■ \n   ■■■■■     ■■■■■  \n                    " },
        { "hookn",
            "                    \n                    \n      ■■■■  ■■■     \n        ■■■■■■      \n       ■■■■■        \n      ■■■ ■■■■      \n      ■■   ■■■      \n         ■■■■■      \n                    \n                    " },
        { "six",
            "                    \n          ■■■■■     \n      ■■■■■■■■■     \n      ■■■■■■■■      \n      ■■■   ■■■     \n      ■■     ■■     \n      ■■■  ■■■■     \n       ■■■■■■■      \n                    \n                    " },
        { "squigglyn",
            "                    \n      ■             \n      ■■■■          \n       ■■■  ■■■     \n       ■■■■■■■      \n      ■■■■ ■■       \n           ■■■      \n           ■■■      \n                    \n                    " },
        { "at",
            "                    \n                    \n        ■■■■        \n       ■■■■■■       \n      ■■■  ■■■      \n     ■■■■■■■■■■     \n    ■■■  ■■  ■■■    \n   ■■■   ■■   ■■■   \n                    \n                    " },
        { "ae",
            "                    \n                    \n       ■■  ■■       \n     ■■■■■■■■■■     \n      ■■■■■■■■■     \n     ■■  ■■         \n     ■■■■■■■■■■     \n                    \n                    \n                    " },
        { "meltedthree",
            "                    \n      ■■■■■■        \n     ■■■  ■■■       \n         ■■■■       \n        ■■■■■       \n           ■■       \n           ■■■■     \n             ■■     \n             ■■     \n                    " },
        { "euro",
            "        ■■ ■■       \n        ■■ ■■       \n      ■■■■■■■       \n      ■■    ■■■     \n         ■■■■■■     \n         ■■■■■■     \n      ■■    ■■■     \n      ■■■■■■■       \n                    \n                    " },
        { "nwithhat",
            "         ■  ■       \n        ■■■■■       \n     ■■■■■■■■■■■    \n      ■■■   ■■■     \n      ■■■ ■■■■■     \n      ■■■■■■ ■■     \n      ■■■■   ■■     \n     ■■■■■ ■■■■■    \n             ■■■    \n             ■■     " },
        { "dragon",
            "                    \n        ■  ■■       \n        ■■■■        \n       ■■■■■■■      \n         ■■■■■      \n         ■■■■■      \n        ■■■■■■      \n       ■■■■■■       \n       ■■■■■■       \n                    " },
        { "questionmark",
            "                    \n                    \n          ■■■       \n           ■        \n         ■■■        \n        ■■          \n        ■■  ■■      \n        ■■■■■■      \n                    \n                    " },
        { "paragraph",
            "                    \n                    \n       ■■■■■■■      \n       ■■■■■■■      \n       ■■■■■■■      \n          ■■■■      \n            ■■      \n            ■■      \n                    \n                    " },
        { "rightc",
            "                    \n         ■■■■       \n      ■■■■■■■■■     \n     ■■■            \n     ■■   ■■        \n     ■■   ■■        \n     ■■■■   ■■■     \n       ■■■■■■■■     \n                    \n                    " },
        { "leftc",
            "                    \n       ■■■          \n     ■■■■■■■■■      \n            ■■■     \n       ■■■  ■■■     \n       ■■■  ■■■     \n     ■■   ■■■■      \n     ■■■■■■■        \n                    \n                    " },
        { "pitchfork",
            "                    \n                    \n    ■■  ■■■■   ■    \n     ■■  ■■   ■■    \n     ■■  ■■   ■■    \n     ■■■ ■■ ■■■     \n       ■■■■■■       \n        ■■■■        \n                    \n                    " },
        { "cursive",
            "                    \n                    \n       ■■■  ■       \n     ■■■ ■■■■■■■    \n    ■■■  ■■■  ■■    \n    ■■■  ■■  ■■■    \n     ■■■ ■■■■■■■    \n      ■■■■■■■■■■■   \n                    \n                    " },
        { "tracks",
            "                    \n                    \n        ■  ■■■      \n       ■■■■■■■      \n     ■■ ■■■■■■■     \n     ■■■■■■■■■■     \n      ■■■■■■■       \n      ■■■■■■■       \n       ■            \n                    " },
        { "balloon",
            "                    \n       ■■■■■■       \n     ■■■■■■■■■■     \n     ■■      ■■■    \n     ■■       ■■    \n     ■■■     ■■■    \n     ■■■■■■■■■■     \n        ■■■■■       \n         ■■■        \n                    " },
        { "upsidedowny",
            "                    \n                    \n      ■■■■■■        \n      ■■■■■         \n       ■■■■■        \n        ■■■■        \n       ■■  ■■ ■■    \n      ■■    ■■■■    \n                    \n                    " },
        { "bt",
            "                    \n                    \n      ■■■■■■        \n    ■■■■■■■■■       \n    ■■ ■■■ ■■       \n       ■■■■■■■■■    \n       ■■■   ■■■    \n      ■■■■■■■■■■    \n      ■■■■■■■■      \n                    " } };
    // map, first index is word, second is ascii representation
    // I can't use files here so I just did this manually
    for (int i = 0; i < display.length; i++) {
      display[i] = getFromMapString(display[i], asciiTable);
      display[i].replace("■ ", "  ");
    }
  }

  public void check() {
    for (int i = 0; i < 4; i++) {
      System.out.println(display[i]);
    }
    System.out.println("Please enter your answer as numbers with an enter/return between each.");
    for (int i = 0; i < 4; i++) {
      try {
        String userInput = input.nextLine();
        if (stop) {
          return;
        }

        if (!(Integer.parseInt(userInput) - 1 == answer[i])) {
          super.b.strike();
          i--;
        }
      } catch (Exception e) {
        throw e;
        // System.out.println("ERROR: please input an integer");
        // i--;
      }
    }
    super.clear();
  }

  public String toString() {
    return ("keypad's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }

  private String getFromMapString(String word, String[][] map) {
    for (int i = 0; i < map.length; i++) {
      if (map[i][0].equals(word)) {
        return map[i][1];
      }
    }
    return "";
  }
}