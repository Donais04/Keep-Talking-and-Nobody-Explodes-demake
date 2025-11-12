/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: emulates the capacitor discharge module
 * Sources: none to the best of my memory
 */
public class capacitorDischarge extends needyModule {

  public capacitorDischarge(bomb b) {
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
      if (timer + 30000 < System.currentTimeMillis() && !active) { // always 30 seconds. This is here bc in game it only
                                                                   // makes noises starting at halfway full
        System.out.println(getModuleName() + " is halfway full");
        nextWarn = 1;
        active = true;
      } else if (active && timer + 30000 + nextWarn * 10000 < System.currentTimeMillis()) {
        System.out
            .println(getModuleName() + " warning " + (30 - nextWarn * 10)
                + " seconds left, check the module to discharge");
        nextWarn++;
        if (nextWarn == 4) {
          // if you let it fill up
          System.out.println(getModuleName() + " striked you");
          strike();
          active = false;
          timer = System.currentTimeMillis();
        }
      }
    }

  }

  public void check() {
    System.out.println("while you are on this screen, the capacitor will discharge");
    waitForEnter waiter = new waitForEnter();
    while (waiter.isAlive()) {
      if (timer < System.currentTimeMillis()) {
        timer += 1500;
        // adds 1.5 seconds every 0.5 seconds, for a total of 1 second gained per second
        if (timer > System.currentTimeMillis()) {
          timer = System.currentTimeMillis();
        }
      }
      if (timer + 30000 > System.currentTimeMillis()) {
        active = false;
      }
      System.out
          .println("there are " + (Math.round((60.0 - (System.currentTimeMillis() - timer) / 1000.0) * 100.0)) / 100.0
              + " seconds left on the timer. Press enter when you'd like to leave");
      try {
        Thread.sleep(500);
      } catch (Exception e) {
      }
    }
  }

  public String toString() {
    return ("capacitor discharge is at "
        + (60 - (System.currentTimeMillis() - timer) / 1000)
        + " seconds");
  }

  public String getModuleName() {
    return ("capacitor");
  }

  private class waitForEnter implements Runnable {

    // to stop the thread
    private Thread t;

    waitForEnter() {
      t = new Thread(this);
      t.start(); // Starting the thread
    }

    // execution of thread starts from run() method
    public void run() {
      input.nextLine();
    }

    public boolean isAlive() {
      return t.isAlive();
    }
  }
}
