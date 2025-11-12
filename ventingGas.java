/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the venting gas module
 * Sources: none to the best of my memory
 */
public class ventingGas extends needyModule {
  private boolean ans;

  public ventingGas(bomb b) {
    super(b);
    Thread t = new Thread();
    t.start();
  }

  public void check() {
    if (ans) {
      System.out.println("vent gas?(Y/N)");
    } else {
      System.out.println("detonate bomb?(Y/N)");
    }
    String userAnswer = input.nextLine();
    if (stop) {
      return;
    }
    if (!(ans == (userAnswer.toLowerCase().charAt(0) == 'y'))) {
      super.strike();
    }
    active = false;
    timer = System.currentTimeMillis();
  }

  public String toString() {
    return ("venting gas is" + ((active) ? ("") : ("n't")) + " displaying "
        + ((active) ? ("something") : ("anything")));
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
      } else if (active && timer + nextWarn * 10000 < System.currentTimeMillis()) {
        System.out
            .println(getModuleName() + " warning " + (30 - nextWarn * 10)
                + " seconds left");
        nextWarn++;
        if (nextWarn == 4) {
          // if you didn't pick an answer...
          System.out.println(getModuleName() + " striked you");
          strike();
          active = false;
          timer = System.currentTimeMillis();
          randomDelay = (int) (Math.random() * 20000);
        }
      }
    }

  }

  private void pickAnswer() {
    ans = Main.tf();
  }

  public String getModuleName() {
    return "venting gas";
  }
}
