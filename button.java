/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the button module
 * Sources: none to the best of my memory
 */
public class button extends module {
  private String color, label, strip;
  private boolean tap;

  public button(bomb bomb) {
    super.b = bomb;
    final String[] colorAr = { "red", "blue", "white", "yellow" };
    final String[] labelAr = { "Abort", "Detonate", "Hold", "Press" };
    color = colorAr[(int) (Math.random() * (colorAr.length - 1))];
    strip = colorAr[(int) (Math.random() * (colorAr.length - 1))];
    label = labelAr[(int) (Math.random() * (labelAr.length - 1))];
    tap = false; // stores if the button needs to be tapped
    boolean frk = false; // stores if a lit FRK indicator is present
    boolean car = false; // stores if a lit CAR indicator is present
    for (int i = 0; i < bomb.getIndicators().length; i++) {
      if (bomb.getIndicators()[i].equals("FRK")) {
        frk = true;
      } else if (bomb.getIndicators()[i].equals("CAR")) {
        car = true;
      }
    }
    // these are put in this order, as that's the order that appears on the manual
    if (color.equals("blue") && label.equals("Abort")) {
    } else if (label.equals("Detonate") && bomb.getBatteries() > 1) {
      tap = true;
    } else if (color.equals("white") && car) {
    } else if (bomb.getBatteries() > 2 && frk) {
      tap = true;
    } else if (color.equals("yellow")) {
    } else if (color.equals("red") && label.equals("hold")) {
      tap = true;
    }
  }

  public void check() {
    while (true) {
      System.out.println(color + " button saying " + label + "\ndo you tap (Y/N)?");
      String userInput = input.nextLine();
      if (stop) {
        return;
      }
      if ((userInput.toLowerCase().charAt(0) == 'y') == tap) {
        if (tap) {
          break;
        }
      }
      // only here if not tapped
      if (stop) {
        return;
      }
      System.out.println(strip + " colored strip. Press return to release the button");
      countdown go = new countdown(super.b); // displays timer
      super.b.disallowBack();// stop being able to exit module
      input.nextLine(); // waits until next enter
      if (stop) {
        return;
      }
      go.stop();
      float endTime = super.b.timeLeft();
      char[] cAr = (endTime + "").toCharArray();
      boolean[] ffo = { false, false, false }; // first index stores if there's a 5, second stores a 4, and last stores
                                               // a 1
      for (int i = 0; i < cAr.length; i++) { // tests all digits on timer
        switch ((int) (cAr[i] - 48)) {
          case 5:
            ffo[0] = true;
            break;
          case 4:
            ffo[1] = true;
            break;
          case 1:
            ffo[2] = true;
            break;
        }
      }
      if (stop) {
        return;
      }
      if (strip.equals("yellow")) {
        if (ffo[0]) {
          break;
        } else {
          super.b.strike();
        }
      } else if (strip.equals("blue")) {
        if (ffo[1]) {
          break;
        } else {
          super.b.strike();
        }
      } else {
        if (ffo[2]) {
          break;
        } else {
          super.b.strike();
        }
      }
    }
    super.clear();
  }

  public String toString() {
    return ("button's been cleared: " + (super.isClear() ? "Yes" : "No"));
  }

  public class countdown implements Runnable {

    // to stop the thread
    private boolean exit;
    Thread t;
    bomb b;

    countdown(bomb bomb) {
      b = bomb;
      t = new Thread(this);
      exit = false;
      t.start(); // Starting the thread
    }

    // execution of thread starts from run() method
    public void run() {
      while (!exit) {
        b.printTime();
        System.out.println();
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          System.out.println("Caught:" + e);
        }
      }
    }

    // for stopping the thread
    public void stop() {
      exit = true;
    }
  }
}
