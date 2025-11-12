/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the knob module
 * Sources: none to the best of my memory
 */
public class knob extends needyModule {
  private int ans;
  private boolean[] display;
  private int knob;

  public knob(bomb b) {
    super(b);
    Thread t = new Thread(this);
    t.start();
  }

  public void run() {
    while (!killed) {
      try {
        Thread.sleep(100); // just here so that it's not taking up processing power unneccesarily
      } catch (Exception e) {
      }
      if (timer + 20000 + randomDelay < System.currentTimeMillis() && !active) { // between 20 and 40 seconds
        System.out.println(getModuleName() + " is now active, you have 30 seconds");
        nextWarn = 1;
        active = true;
        timer = System.currentTimeMillis();
        this.pickAnswer();
      } else if (active && timer + randomDelay + nextWarn * 10000 < System.currentTimeMillis()) {
        System.out
            .println(getModuleName() + " warning " + (30 - nextWarn * 10)
                + " seconds left, make sure you've selected a direction");
        nextWarn++;
        if (nextWarn == 4) {
          // knob only checks if you've gotten it correct at the end
          if (!checkAnswer()) {
            System.out.println(getModuleName() + " striked you");
            strike();
          } else {
            System.out.println(getModuleName() + " is deactivated, congrats");
          }
          active = false;
          timer = System.currentTimeMillis();
          randomDelay = (int) (Math.random() * 20000);
        }
      }
    }

  }

  public void check() {
    for (int i = 0; i < 12; i++) {
      // displays lights
      System.out.print((display[i]) ? (" O ") : (" . "));
      if (i == 5) {
        System.out.println();
      }
    }
    if (Main.practiceMode) {
      System.out.println(ans);
    }
    System.out.println("\n what direction do you turn the knob? 1 is up, 2 is right, 3 is down, and 4 is left");
    String userInput = input.nextLine();
    if (stop) {
      return;
    }
    // try {
    knob = Integer.parseInt(userInput);
    // } (catch NumberFormatException e) {
    // System.out.println("please enter as an integer");}
  }

  public String toString() {
    return ("Knob is" + ((active) ? ("") : ("n't")) + " displaying " + ((active) ? ("something") : ("anything")));
  }

  public String getModuleName() {
    return ("knob");
  }

  private void pickAnswer() {
    final boolean[][] displayChoices = { { false, false, true, false, true, true, true, true, true, true, false, true },
        { true, false, true, false, true, false, false, true, true, false, true, true },
        { false, true, true, false, false, true, true, true, true, true, false, true },
        { true, false, true, false, true, false, false, true, false, false, false, true },
        { false, false, false, false, true, false, true, false, false, true, true, true },
        { false, false, false, false, true, false, false, false, false, true, true, false },
        { true, false, true, true, true, true, true, true, true, false, true, false },
        { true, false, true, true, false, false, true, true, true, false, true, false } };
    int choice = (int) (Math.random() * (displayChoices.length - 1));
    if (Main.practiceMode) {
      System.out.println(choice);
    }
    display = displayChoices[choice];
    ans = (int) Math.floor((double) (choice + 1) / 2.0);

  }

  private boolean checkAnswer() {
    return (ans == knob);
  }
}
