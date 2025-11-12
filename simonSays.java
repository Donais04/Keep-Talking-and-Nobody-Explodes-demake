/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the simon says module
 * Sources: none to the best of my memory
 */
public class simonSays extends module {
  private int[] flashes;
  private String[][] tableAnswers;
  private int currNumFlashes;

  public simonSays(bomb bomb) {
    super.b = bomb;
    String[][][] table = {
        { { "Blue", "Red", "Yellow", "Green" }, { "Yellow", "Green", "Blue", "Red" },
            { "Green", "Red", "Yellow", "Blue" } },
        { { "Blue", "Green", "Yellow", "Red" }, { "Red", "Blue", "Yellow", "Green" },
            { "Yellow", "Green", "Blue", "Red" } } }; // table of button mapping
    tableAnswers = table[1]; // checking if a vowel exists in the serial code
    for (int j = 0; j < super.b.getSerial().length(); j++) {
      char ch = super.b.getSerial().charAt(j);
      switch (ch) {
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
        case 'A':
        case 'E':
        case 'I':
        case 'O':
        case 'U':
          tableAnswers = table[0];
      }
    }
    flashes = new int[((int) (Math.random() * 3 + 3))]; // an arbitrary number, just based on my experiences
    for (int j = 0; j < flashes.length; j++) {
      flashes[j] = (int) (Math.random() * 3); // assigns a random color to each flash
    }
    currNumFlashes = 1;
  }

  public void check() {
    final String[] colors = { "red", "blue", "green", "yellow" };
    String[] solution = new String[flashes.length];
    while (currNumFlashes <= flashes.length) {
      for (int j = 0; j < flashes.length; j++) {
        solution[j] = tableAnswers[(3 - super.b.getStrikes())][flashes[j]]; // assigns colors to solution
      }
      for (int j = 0; j < currNumFlashes; j++) {
        System.out.println(colors[flashes[j]] + " flash");
      }
      System.out
          .println("Which colors will you press? Just answer the first letter (for example, type y for yellow)");
      boolean correct = false;
      for (int j = 0; j < currNumFlashes; j++) { // each flash you have to input the ones that came before it
        String ans = "";
        while (ans.equals("")) {
          String userInput = input.nextLine();
          if (stop) {
            return;
          }
          switch (userInput.toLowerCase().charAt(0)) {
            case 'r':
              ans = "Red";
              break;
            case 'b':
              ans = "Blue";
              break;
            case 'g':
              ans = "Green";
              break;
            case 'y':
              ans = "Yellow";
              break;
            default:
              System.out.println("I'm sorry, I didn't quite understand that");
              ans = "";
              break;
          }
        }
        if (!(ans.equals(solution[j]))) {
          if (Main.practiceMode) {
            System.out.println(solution[j]);
          }
          super.b.strike();
          for (int k = 0; k < flashes.length; k++) {
            solution[k] = tableAnswers[(3 - super.b.getStrikes())][flashes[k]]; // assigns colors to solution
          }
          break;
        } else if (currNumFlashes == (flashes.length + 1)) { // when you've gone through all flashes
          break;
        } else {
          correct = true;
        }
      }
      if (correct) {
        currNumFlashes++;
      }
    }
    super.clear();
  }

  public String toString() {
    return ("simon says's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }
}
