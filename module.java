
/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: provides a base for all modules
 * Sources: none to the best of my memory
 */
import java.util.Scanner;

class module {
  protected Scanner input = new Scanner(System.in);
  protected boolean cleared;
  protected bomb b;
  protected boolean stop;

  public module() {
    cleared = false;
  }

  public void checkRequest() {
    if (!cleared || new needyModule(b).getClass().isAssignableFrom(this.getClass())) {
      check();
    } else {
      System.out
          .println("You have already cleared this");
    }
  }

  public void check() {

  }

  protected void clear() {
    cleared = true;
  }

  public boolean isClear() {
    return cleared;
  }

  public String toString() {
    return null;
  }

  public void kill() {
    input.close();
  }

  public void stop() {
    stop = true;
  }

  public String getModuleName() {
    return "debug message";
  }

}