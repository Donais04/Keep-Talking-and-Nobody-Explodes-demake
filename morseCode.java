/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the maze module
 * Sources: https://www.codebug.org.uk/learn/step/541/morse-code-timing-rules/
 */
public class morseCode extends module {

  private double ans;
  private int[] display;
  private int totalTime;

  public morseCode(bomb bomb) {
    super.b = bomb;
    final String[] words = { "beats", "bistro", "bombs", "boxes", "break", "brick", "flick", "halls", "leaks", "shell",
        "slick", "steak", "sting", "strobe", "trick", "vector" };
    final double[] freqs = { 3.6, 3.552, 3.565, 3.535, 3.572, 3.575, 3.555, 3.515, 3.542, 3.505, 3.522, 3.582, 3.592,
        3.545, 3.532, 3.595 };
    int r = (int) (Math.random() * words.length); // picks a random word
    String randWord = words[r]; // picks a random word
    ans = freqs[r]; // sets answer to the frequency of the word
    boolean[][] constructor = new boolean[randWord.length()][]; // constructs the morse code for the word, with the
                                                                // first array being for the different letters, and the
                                                                // second being for the morse of each letter
    int count = 0;
    int dotLength = 25; // sets how many milliseconds a dot is considered
    for (int i = 0; i < randWord.length(); i++) {
      constructor[i] = toMorse(randWord.charAt(i)); // assigns each letter it's morse code
      count += 2 * constructor[i].length + 2; // keeps track of how long the display should be
    }
    display = new int[count + 10]; // display is what is actrually read when the morse code is being displayed,
                                   // with each int being how long it should wait in milliseconds
    display[0] = 14 * dotLength; // display[0] is a seperator for the end and the start of a word
    int k = 1; // k is where to write to
    for (int i = 0; i < randWord.length(); i++) {
      for (int j = 0; j < constructor[i].length; j++) { // these lengths are according
        if (constructor[i][j]) {
          display[k] = 3 * dotLength; // dash
        } else {
          display[k] = dotLength; // dot
        }
        k++;
        display[k] = 2 * dotLength; // space
        k++;
      }
      display[k] = 0;
      // band-aid fix for an issue that I can't quite put into words without typing a
      // paragraph on how this works
      k++;
      display[k] = 5 * dotLength; // seperates letters
      k++;
    }
    totalTime = 0; // keeps track of the time for the
    for (int i = 0; i < display.length; i++) {
      totalTime += display[i];
    }

  }

  public void check() {
    while (true) {
      System.out.println("The sequence will begin at a random spot. Press enter/return once you're ready.");
      input.nextLine();
      if (stop) {
        return;
      }
      final long startTimeCenti = (System.currentTimeMillis() / 10);
      long allTime = startTimeCenti;
      // allTime keeps track of how long has passes
      int i = (int) (Math.random() * (display.length - 1));
      // starts in a random spot
      while ((System.currentTimeMillis() / 10) < (startTimeCenti + totalTime)) {
        // keeps in loop until it's gone the length of totalTime
        if (System.currentTimeMillis() / 10 > (allTime + display[i])) {
          // if need to go to next index in display
          if (i == display.length - 1) {
            // if we need to loop back to the start of display
            final long temp2 = System.currentTimeMillis() / 10;
            allTime = temp2;
            i = 0;
          } else {
            // if you just need to increment display
            allTime += display[i];
            i++;
          }
        } else if (i % 2 == 1) { // alternates between printing out dots and nothing
          System.out.println("*");
        } else {
          System.out.println();
        }
      }
      System.out.println("Guess the frequency or type 0 to see the sequence again");
      double userAnswer;
      while (true) {

        String lineHolder = input.nextLine();
        if (stop) {
          return;
        }
        try {
          userAnswer = Double.parseDouble(lineHolder);
          break;
        } catch (NumberFormatException e) {
          System.out.println("Please input a number");
        }
      }
      if (userAnswer == 0) {
      } else if (userAnswer == ans) {
        break;
      } else {
        if (Main.practiceMode) {
          System.out.println(ans);
        }
        super.b.strike();
      }
    }
    super.clear();
  }

  public String toString() {
    return ("morse code's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }

  private boolean[] toMorse(char cha) {
    // converts character to string of morse
    String[] english = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
        "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ",", ".", "?" };
    String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
        "---", ".---.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---",
        "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "--..--", ".-.-.-", "..--.." };
    String str = cha + "";
    int index = -1;
    for (int i = 0; i < english.length; i++) {
      if (english[i].equals(str)) {
        index = i;
      }
    }
    // converts string of morse to boolean list
    boolean[] r = new boolean[morse[index].length()];
    for (int i = 0; i < r.length; i++) {
      if (morse[index].charAt(i) == '-') {
        r[i] = true;
      } else {
        r[i] = false;
      }
    }
    return r;
  }
}
