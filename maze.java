/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the maze module
 * Sources: none to the best of my memory
 */

public class maze extends module {
  private boolean[][][] board;
  private int[][] circles;
  private int[] square;
  private int[] triangle;

  public maze(bomb bomb) {
    super.b = bomb;
    boolean[][][][] table = {
        { { { false, true, false, true }, { false, false, true, true }, { false, true, true, false },
            { false, true, false, true }, { false, false, true, true }, { false, false, true, false } },
            {
                { true, true, false, false }, { false, true, false, true }, { true, false, true, false },
                { true, false, false, true }, { false, false, true, true }, { false, true, true, false } },
            {
                { true, true, false, false }, { true, false, false, true }, { false, true, true, false },
                { false, true, false, true }, { false, false, true, true }, { true, true, true, false } },
            {
                { true, true, false, false }, { false, false, false, true }, { true, false, true, true },
                { true, false, true, false }, { false, false, false, true }, { true, true, true, false } },
            {
                { true, true, false, true }, { false, false, true, true }, { false, true, true, false },
                { false, true, false, true }, { false, false, true, false }, { true, true, false, false } },
            {
                { true, false, false, true }, { false, false, true, false }, { true, false, false, true },
                { true, false, true, false }, { false, false, false, true }, { true, false, true, false } } },

        { { { false, false, false, true }, { false, true, true, true }, { false, false, true, false },
            { false, true, false, true }, { false, true, true, true }, { false, false, true, false } },
            {
                { false, true, false, true }, { true, false, true, false }, { false, true, false, true },
                { true, false, true, false }, { true, false, false, true }, { false, true, true, false } },
            {
                { true, true, false, false }, { false, true, false, true }, { true, false, true, false },
                { false, true, false, true }, { false, false, true, true }, { true, true, true, false } },
            {
                { true, true, false, true }, { true, false, true, false }, { false, true, false, true },
                { true, false, true, false }, { false, true, false, false }, { true, true, false, false } },
            {
                { true, true, false, false }, { false, true, false, false }, { true, true, false, false },
                { false, true, false, true }, { true, false, true, false }, { true, true, false, false } },
            {
                { true, false, false, false }, { true, false, false, true }, { true, false, true, false },
                { true, false, false, true }, { false, false, true, true }, { true, false, true, false } } },

        { { { false, true, false, true }, { false, false, true, true }, { false, true, true, false },
            { false, true, false, false }, { false, true, false, true }, { false, true, true, false } },
            {
                { true, false, false, false }, { false, true, false, false }, { true, true, false, false },
                { true, false, false, true }, { true, false, true, false }, { true, true, false, false } },
            {
                { false, true, false, true }, { true, true, true, false }, { true, true, false, false },
                { false, true, false, true }, { false, true, true, false }, { true, true, false, false } },
            {
                { true, true, false, false }, { true, true, false, false }, { true, true, false, false },
                { true, true, false, false }, { true, true, false, false }, { true, true, false, false } },
            {
                { true, true, false, false }, { true, false, false, true }, { true, false, true, false },
                { true, true, false, false }, { true, true, false, false }, { true, true, false, false } },
            {
                { true, false, false, true }, { false, false, true, true }, { false, false, true, true },
                { true, false, true, false }, { true, false, false, true }, { true, false, true, false } } },

        { { { false, true, false, true }, { false, true, true, false }, { false, false, false, true },
            { false, false, true, true }, { false, false, true, true }, { false, true, true, false } },
            {
                { true, true, false, false }, { true, true, false, false }, { false, true, false, true },
                { false, false, true, true }, { false, false, true, true }, { true, true, true, false } },
            {
                { true, true, false, false }, { true, false, false, true }, { true, false, true, false },
                { false, true, false, true }, { false, false, true, false }, { true, true, false, false } },
            {
                { true, true, false, false }, { false, false, false, true }, { false, false, true, true },
                { true, false, true, true }, { false, false, true, true }, { true, true, true, false } },
            {
                { true, true, false, true }, { false, false, true, true }, { false, false, true, true },
                { false, false, true, true }, { false, true, true, false }, { true, true, false, false } },
            {
                { true, false, false, true }, { false, false, true, true }, { false, false, true, false },
                { false, false, false, true }, { true, false, true, false }, { true, false, false, false } } },

        { { { false, false, false, true }, { false, false, true, true }, { false, false, true, true },
            { false, false, true, true }, { false, true, true, true }, { false, true, true, false } },
            {
                { false, true, false, true }, { false, false, true, true }, { false, false, true, true },
                { false, true, true, true }, { true, false, true, false }, { true, false, false, false } },
            {
                { true, true, false, true }, { false, true, true, false }, { false, false, false, true },
                { true, false, true, false }, { false, true, false, true }, { false, true, true, false } },
            {
                { true, true, false, false }, { true, false, false, true }, { false, false, true, true },
                { false, true, true, false }, { true, false, false, false }, { true, true, false, false } },
            {
                { true, true, false, false }, { false, true, false, true }, { false, false, true, true },
                { true, false, true, true }, { false, false, true, false }, { true, true, false, false } },
            {
                { true, false, false, false }, { true, false, false, true }, { false, false, true, true },
                { false, false, true, true }, { false, false, true, true }, { true, false, true, false } } },

        { { { false, true, false, false }, { false, true, false, true }, { false, true, true, false },
            { false, false, false, true }, { false, true, true, true }, { false, true, true, false } },
            {
                { true, true, false, false }, { true, true, false, false }, { true, true, false, false },
                { false, true, false, true }, { true, false, true, false }, { true, true, false, false } },
            {
                { true, true, false, true }, { true, false, true, false }, { true, false, false, false },
                { true, true, false, false }, { false, true, false, true }, { true, false, true, false } },
            {
                { true, false, false, true }, { false, true, true, false }, { false, true, false, true },
                { true, true, true, false }, { true, true, false, false }, { false, true, false, false } },
            {
                { false, true, false, true }, { true, false, true, false }, { true, false, false, false },
                { true, true, false, false }, { true, false, false, true }, { true, true, true, false } },
            {
                { true, false, false, true }, { false, false, true, true }, { false, false, true, true },
                { true, false, true, true }, { false, false, false, true }, { true, false, true, false } } },

        { { { false, true, false, true }, { false, false, true, true }, { false, false, true, true },
            { false, true, true, false }, { false, true, false, true }, { false, true, true, false } },
            {
                { true, true, false, false }, { false, true, false, true }, { false, false, true, false },
                { true, false, false, true }, { true, false, true, false }, { true, true, false, false } },
            {
                { true, false, false, true }, { true, false, true, false }, { false, true, false, true },
                { false, false, true, false }, { false, true, false, true }, { true, false, true, false } },
            {
                { false, true, false, true }, { false, true, true, false }, { true, true, false, true },
                { false, false, true, true }, { true, false, true, false }, { false, true, false, false } },
            {
                { true, true, false, false }, { true, false, false, false }, { true, false, false, true },
                { false, false, true, true }, { false, true, true, false }, { true, true, false, false } },
            {
                { true, false, false, true }, { false, false, true, true }, { false, false, true, true },
                { false, false, true, true }, { true, false, true, true }, { true, false, true, false } } },

        { { { false, true, false, false }, { false, true, false, true }, { false, false, true, true },
            { false, true, true, false }, { false, true, false, true }, { false, true, true, false } },
            {
                { true, true, false, true }, { true, false, true, true }, { false, false, true, false },
                { true, false, false, true }, { true, false, true, false }, { true, true, false, false } },
            {
                { true, true, false, false }, { false, true, false, true }, { false, false, true, true },
                { false, false, true, true }, { false, true, true, false }, { true, true, false, false } },
            {
                { true, true, false, false }, { true, false, false, true }, { false, true, true, false },
                { false, false, false, true }, { true, false, true, true }, { true, false, true, false } },
            {
                { true, true, false, false }, { false, true, false, false }, { true, false, false, true },
                { false, false, true, true }, { false, false, true, true }, { false, false, true, false } },
            {
                { true, false, false, true }, { true, false, true, true }, { false, false, true, true },
                { false, false, true, true }, { false, false, true, true }, { false, false, true, false } } },

        { { { false, true, false, false }, { false, true, false, true }, { false, false, true, true },
            { false, false, true, true }, { false, true, true, true }, { false, true, true, false } },
            {
                { true, true, false, false }, { true, true, false, false }, { false, true, false, true },
                { false, false, true, false }, { true, true, false, false }, { true, true, false, false } },
            {
                { true, true, false, true }, { true, false, true, true }, { true, false, true, false },
                { false, true, false, true }, { true, false, true, false }, { true, true, false, false } },
            {
                { true, true, false, false }, { false, true, false, false }, { false, true, false, true },
                { true, false, true, false }, { false, false, false, true }, { true, true, true, false } },
            {
                { true, true, false, false }, { true, true, false, false }, { true, true, false, false },
                { false, true, false, true }, { false, true, true, false }, { true, false, false, false } },
            {
                { true, false, false, true }, { true, false, true, false }, { true, false, false, true },
                { true, false, true, false }, { true, false, false, true }, { false, false, true, false } } } };
    // this is every maze. I remember my grip on reality going away while writing it
    int selection = (int) (Math.random() * 8); // selects a random maze
    board = table[selection]; // board is the selected maze
    int[][][] circleTable = { { { 1, 5 }, { 6, 4 } }, { { 2, 3 }, { 5, 5 } }, { { 4, 3 }, { 6, 3 } },
        { { 1, 6 }, { 1, 3 } }, { { 4, 1 }, { 5, 4 } }, { { 3, 2 }, { 5, 6 } }, { { 2, 1 }, { 2, 6 } },
        { { 3, 3 }, { 4, 6 } }, { { 1, 2 }, { 3, 5 } } };
    circles = circleTable[selection]; // circles tell you which maze is correct
    square = new int[2];
    triangle = new int[2];
    while (true) {
      int[] s = { (int) (Math.random() * 5 + 1), (int) (Math.random() * 5 + 1) };
      square = s;
      int[] t = { (int) (Math.random() * 5 + 1), (int) (Math.random() * 5 + 1) };
      triangle = t;
      // picks random space for the square and traingle
      if (!(triangle[0] + 3 > square[0] && triangle[0] - 3 < square[0] &&
          triangle[1] + 3 > square[1] && triangle[1] - 3 < square[1])) {
        break; // ensures square and triangle are not too close together
      }
    }
  }

  public void check() {
    System.out.println(
        "The circles are marked with O, the triangle is marked with T, and the square is maked with S. (If a circle is missing, it is most likely behind either the square or the triangle). \nUse wasd to move.");
    while (true) {
      for (int i = 0; i < 36; i++) { // prints maze
        if (i % 6 == 0) {
          System.out.println(); // seperates rows
        }
        if (i == (square[0] - 1 + (6 - square[1]) * 6)) {
          System.out.print("S");
        } else if (i == (triangle[0] - 1 + (6 - triangle[1]) * 6)) {
          System.out.print("T");
        } else if (i == (circles[0][0] - 1 + (6 - circles[0][1]) * 6)) {
          System.out.print("O");
        } else if (i == (circles[1][0] - 1 + (6 - circles[1][1]) * 6)) {
          System.out.print("O");
        } else {
          System.out.print(".");
        }
      }
      System.out.println();
      String userInput = input.nextLine();
      if (stop) {
        return;
      }
      switch (userInput.toLowerCase().charAt(0)) { // checks the move
        case 'w':
          if (board[6 - square[1]][square[0] - 1][0]) {
            square[1]++;
          } else {
            super.b.strike();
          }
          break;
        case 's':
          if (board[6 - square[1]][square[0] - 1][1]) {
            square[1]--;
          } else {
            super.b.strike();
          }
          break;
        case 'a':
          if (board[6 - square[1]][square[0] - 1][2]) {
            square[0]--;
          } else {
            super.b.strike();
          }
          break;
        case 'd':
          if (board[6 - square[1]][square[0] - 1][3]) {
            square[0]++;
          } else {
            super.b.strike();
          }
          break;
        default:
          System.out.println("please input a w, a, s, or d");
          break;
      }
      if (square[0] == triangle[0] && square[1] == triangle[1]) { // checks if won
        break;
      }
    }
    super.clear();
  }

  public String toString() {
    return ("maze's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }
}
