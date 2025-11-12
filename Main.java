
/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: provides an interface to create and interact with bombs
 * Sources: none to the best of my memory
 */
import java.lang.Math;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class Main {
  public static boolean practiceMode = false; // used for practicing bombs or debugging
  private static Scanner input = new Scanner(System.in); // I'm not proud of using this...

  public static boolean tf() {
    // flips a coin (tf stands for true or false)
    return (Math.random() * 2 > 1);
  }

  public static int findIndex(int arr[], int t) {
    // simple search array
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == t) {
        return i;
      }
    }
    return -1;
  }

  public static void screenClear() {
    // gets rid of on screen text
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }

  public static void main(String[] args) {
    screenClear();
    System.out.println(
        "Hello! Would you like to play a created bomb, create a bomb and play it, or create a bomb and save it?"
            + "\nType 1, 2, or 3 respectively.");
    int userInput = 0;
    while (true) { // ensure the user types a 1, 2, or 3
      String getInput = input.nextLine();
      try {
        userInput = Integer.parseInt(getInput);
      } catch (NumberFormatException e) {
      }
      if (userInput > 0 && userInput < 4) {
        break;
      }
      System.out.println("Please input 1, 2, or 3");
    }
    switch (userInput) {
      case 1: // plays a created bomb
        String[] bombCodes = getContentsOfFile(new File("bombSeeds.txt")).split("\n"); // splits code by bomb
        System.out.println("Which of the following would you like to play? Answer as an integer");
        for (int i = 0; i < bombCodes.length; i++) { // displays bomb options
          System.out.println((i + 1) + ": " + bombCodes[i].split("\\.")[0]);
        }
        bomb b;
        while (true) { // gets user input
          String getInput = input.nextLine();
          try {
            b = new bomb(bombCodes[Integer.parseInt(getInput) - 1]);
            break;
          } catch (NumberFormatException e) {
            System.out.println("Please answer as an integer");
          } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input the number displayed to the left of the bomb you want to play");
          }
        }
        play(b);
        break;
      case 2: // create a bomb, then play it
        play(new bomb(bombCreator()));
        break;
      case 3: // create a bomb, then save it
        String fileSave = getContentsOfFile(new File("bombSeeds.txt")); // saves file so it can be written back later
        fileSave += bombCreator(); // adds new bomb to fileSave string
        try {
          FileWriter bombCodesWriter = new FileWriter("bombSeeds.txt");
          bombCodesWriter.write(fileSave); // writes entire file
          bombCodesWriter.close();
          System.out.println("The bomb has been saved :D");
        } catch (IOException e) {
          System.out.println(
              "You have deleted the bombSeeds.txt file in the time since I created it. You utter IDIOT! How could you");
        }

        break;
    }
  }

  private static void play(bomb b) {
    screenClear();
    System.out.println(b + "\n");
    while (true) {
      // checks if bomb is done
      boolean cleared = true;
      for (int i = 0; i < b.getModules().length; i++) {
        if (!b.getModules()[i].isClear()) {
          cleared = false;
        }
      }
      if (cleared) {
        System.out.println("Congrats! You had " + b.timeLeft() + " seconds left.");
        b.close();
        System.exit(0);
      }
      // allows user input
      System.out.println(
          "What do you what to look at? Type a number to check a module" +
              " (1 being the first module), a letter to check bomb, " +
              "and exit to close the program");
      int requestedModNumber = -1;
      String userInput = input.nextLine();
      try {
        requestedModNumber = Integer.parseInt(userInput) - 1;
        screenClear();
      } catch (NumberFormatException e) {
        if (userInput.toLowerCase().equals("exit")) {
          b.close();
          input.close();
          System.exit(0);
        }
        System.out.println("\n\n" + b); // prints bomb
      }
      try {
        b.check(requestedModNumber);
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("\n\n that module doesn't exist");
      }

    }
  }

  public static String bombCreator() {
    /*
     * code contains a name, a dot, an int for time, then an m. and the modules, as
     * pentadecimal, with random ones
     * represented with brakets around the options
     */

    String code = "";
    System.out.println("Welcome to the bomb creator!" +
        "\nEnter first how much time you'd like in seconds (no decimals)");
    while (code.equals("")) {
      try {
        String userInput = input.nextLine();
        code += "" + Integer.parseInt(userInput);
      } catch (NumberFormatException e) {
        System.out.println("Please input as an integer");
      }
    }
    System.out.println("Would you like to define the amount of strikes? (Y/N)");
    while (true) {
      try {
        if (input.nextLine().toLowerCase().charAt(0) == 'y') {
          code += ".S";
          System.out.println("Please input how many strikes you would like");
          Boolean breaker = false;
          while (!breaker) {
            try {
              code += input.nextInt();
              breaker = true;
            } catch (InputMismatchException e) {
              System.out.println("I'm sorry, I didn't quite understand that one");
            }
          }
        }
        break;
      } catch (Exception e) {
      }
    }
    code += ".m";
    System.out.println("Next, enter in the modules you'd like. You may use either the" +
        " names of the modules (the first 2 letters of each are enough for " +
        "the program to understand) or you may use numbers. 0 is wires, " +
        "1 is button, 2 is keypads, 3 is simon says, 4 is who's on first, " +
        "5 is memory, 6 is morse code, 7 is complicated wires, 8 is " +
        "sequenced wires, 9 is mazes, 10 is passwords, 11 is venting gas, 12 is " +
        "capacitor discharge, and 13 is knob. " +
        "Enter things one at a time with an enter/return key between " +
        "them. When you would like to be done, enter the word done. " +
        "If you would like a random module between multiple, enter in " +
        "R and the number of modules you'd like to randomize between, then " +
        "type in which modules you'd like to be in that pool (one at a time) " +
        "(for example, you may input R2, then a 0, then a 1 to get a random " +
        "module between wires and button). If you would like " +
        "to have a random module of a given type, enter in the type (between beginner, " +
        "intermediate, expert, needy, non-needy, all)");
    while (true) {
      String userInput = input.nextLine();
      if (userInput.length() > 1 && userInput.toLowerCase().substring(0, 2).equals("do")) {
        break; // if done
      } else if (userInput.length() > 1 && userInput.toLowerCase().charAt(0) == 'r'
          && Character.isDigit(userInput.charAt(1))) {
        // if random
        code += "[";
        for (int i = 0; i < Integer.parseInt(userInput.substring(1)); i++) {
          try {
            code += assignCharToModuleInput(input.nextLine());
          } catch (InputMismatchException e) {
            System.out.println("I'm sorry, I didn't quite understand");
          }
        }
        code += "]";
      } else {
        try {
          String holder = assignCharToModuleInput(userInput);
          if (holder.length() > 1) {
            holder = "[" + holder + "]";
          }
          code = code + holder;
        } catch (Exception e) {
          System.out.println("I'm sorry, I didn't quite understand");
        }
      }
      System.out.println("What is next?");
    }
    System.out.println("Finally, enter a name for your bomb!");
    String userInput = "";
    while (true) {
      userInput = input.nextLine();
      if (!userInput.contains(".")) {
        break;
      } else {
        System.out.println("Please do not use a period in your bomb's name");
      }
    }
    code = userInput + "." + code;
    return code;
  }

  private static String assignCharToModuleInput(String userInput) throws InputMismatchException {
    // could be a char but I'm lazy
    // takes in a user's input and assigns it a pentadecimal for it
    try { // if it's a string, it just takes the first two characters
      Integer.parseInt(userInput);
    } catch (NumberFormatException e) {
      userInput = userInput.substring(0, 2);
    }
    switch (userInput) {
      case "wi":
      case "0":
        return "0";
      case "bu":
      case "1":
        return "1";
      case "ke":
      case "2":
        return "2";
      case "si":
      case "3":
        return "3";
      case "wh":
      case "4":
        return "4";
      case "me":
      case "5":
        return "5";
      case "mo":
      case "6":
        return "6";
      case "co":
      case "7":
        return "7";
      case "se":
      case "8":
        return "8";
      case "ma":
      case "9":
        return "9";
      case "pa":
      case "10":
        return "A";
      case "ve":
      case "11":
        return "B";
      case "ca":
      case "12":
        return "C";
      case "kn":
      case "13":
        return "D";
      case "be":
        return "012";
      case "in":
        return "3459";
      case "ex":
        return "678A";
      case "ne":
        return "BCD";
      case "no":
        return "0123456789A";
      case "al":
        return "01234567890ABC";
      default:
        throw new InputMismatchException();

    }
  }

  public static String getContentsOfFile(File f) {
    String returner = "";

    try {
      f.createNewFile();
      Scanner reader = new Scanner(f);
      while (reader.hasNextLine()) {
        returner += reader.nextLine() + "\n";
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("You do not appear to have the file");
      System.exit(0);
    } catch (IOException e) {
      System.out.println("uhhh");
    }
    return returner;
  }
}