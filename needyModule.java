/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: provides an advanced base for modules that are always running
 * Sources: none to the best of my memory
 */
public class needyModule extends module implements Runnable {
  protected long timer;
  protected boolean killed;
  protected boolean active;
  protected int nextWarn;
  protected int randomDelay;

  public needyModule(bomb bomb) {
    super.b = bomb;
    super.cleared = true; // cleared is true so that the game knows that when you complete everything
                          // else, this is automatically complete
    killed = false; // killed is only called when the bomb is cleared
    active = false; // active is when the module is warning you
    timer = System.currentTimeMillis();
    randomDelay = (int) (Math.random() * 20000); // +- 20 seconds
  }

  public void run() {
  }

  protected void strike() {
    super.b.strike();
  }

  public void kill() {
    super.kill();
    killed = true;
  }
}
