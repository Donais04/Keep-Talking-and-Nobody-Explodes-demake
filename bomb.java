
/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: provides the structure of the bomb, and allows for interactions with it
 * Sources: none to the best of my memory
 */
import javafx.event.EventHandler;

import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

class bomb extends Thread {
  private long time;
  private long startTime;
  private String[] ports;
  private indicator[] indicators;
  private int batteries = -1;
  private int strikes;
  private String serial;
  private module[] modules;

  public bomb(String code) {
    // code contains a name, a dot, an int for time, possibly a p. and the ports as
    // ints, possibly a b. and the batteries as an int, possibly an s. and the
    // serial number, possibly an i. and the indicators as three characters and a 0
    // or 1, then an m. and the modules, as pentadecimal, with random ones
    // represented with brakets around the options
    startTime = System.currentTimeMillis() / 10;
    String[] codeComponets = code.split("\\.");
    time = Integer.parseInt(codeComponets[1]);
    for (String i : Arrays.copyOfRange(codeComponets, 1, codeComponets.length - 1)) {
      switch (i.charAt(0)) {
        case 'p':
          String[] portAr = { "DVI-D", "Parallel", "PS/2", "RJ-45", "Serial", "Stereo RCA" };
          ports = new String[i.length() - 1];
          for (int j = 1; j < i.length(); j++) {
            ports[j - 1] = portAr[i.toCharArray()[j] - '0'];
          }
          break;
        case 'b':
          batteries = Integer.parseInt(i.substring(1));
          break;
        case 's':
          serial = i.substring(1);
          break;
        case 'i':
          indicators = new indicator[(i.length() - 1) / 4];
          for (int j = 0; j < indicators.length; j++) {
            indicators[j] = new indicator(i.substring(j * 4 + 1, (j + 1) * 4),
                (i.substring((j + 1) * 4, (j + 1) * 4 + 1) == "1"));
            // ^ first argument is the label, second is whether it's lit or not
          }
          break;
        case 'S':
          strikes = Integer.parseInt(i.substring(1));
          break;
      }
    }
    if (ports == null) {
      String[] portAr = { "DVI-D", "Parallel", "PS/2", "RJ-45", "Serial", "Stereo RCA" };
      ports = new String[(int) (Math.random() * 2) + 1];
      for (int i = 0; i < ports.length; i++) {
        ports[i] = portAr[(int) (Math.random() * (portAr.length - 1) + 1)];
      }
    }
    if (indicators == null) {
      indicators = new indicator[(int) (Math.random() * 5) + 2];
      for (int i = 0; i < indicators.length; i++) {
        indicators[i] = new indicator();
      }

    }
    if (serial == null) {
      char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't',
          'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
      // no o, as it can be confused with a 0
      serial = "";
      for (int i = 0; i < 8; i++) {
        serial = serial + ("" + chars[(int) (Math.random() * chars.length)]);
      }
    }
    if (batteries == -1) {
      batteries = (int) (Math.random() * 4);
    }
    strikes = 3;
    ArrayList<module> mods = new ArrayList<module>();
    String modString = code.split("\\.")[code.split("\\.").length - 1];
    for (int j = 1; j < modString.length(); j++) {
      if (modString.charAt(j) == '[') {
        ArrayList<module> randModHolder = new ArrayList<module>();
        while (!(modString.charAt(j + 1) == ']')) {
          j++;
          randModHolder.add(pentadecimalToModule(this, "" + modString.charAt(j)));
        }
        j++;
        mods.add(randModHolder.get((int) Math.floor(Math.random() * randModHolder.size())));
      } else {
        mods.add(pentadecimalToModule(this, "" + modString.charAt(j)));
      }
    }
    module[] classReferrence = {};
    modules = mods.toArray(classReferrence);
    new Thread(new Runnable() {
      public void run() {
        while (true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          if (timeLeft() < 0) {
            System.out.println("TIME UP \nBOOM!!");
            close();
            System.exit(0);
          }
        }
      }
    }).start();
    this.start(); // starts thread to watch backButton

  }

  public void run() {
    backButton.begin();
  }

  public void check(int num) {
    backButton.set(new killer());
    modules[num].checkRequest();
    for (module i : modules) {
      i.stop = false;
    }
  }

  public String toString() {
    String modString = "";
    for (int i = 0; i < modules.length; i++) {
      modString += modules[i] + "\n";
    }
    String portString = "";
    for (int i = 0; i < ports.length; i++) {
      portString += ports[i] + "\n";
    }
    String indString = "";
    for (int i = 0; i < indicators.length; i++) {
      indString += indicators[i] + "\n";
    }
    return (batteries + " batter" + (batteries == 1 ? "y" : "ies") + ",\nThe ports are:\n" + portString
        + "The indicators are:\n" + indString + "and the serial number is " + serial + "\n" + modString
        + "You have " + timeLeft() + " seconds left\nand " + strikes + " strikes left.");
  }

  public int strike() {
    strikes--;
    if (!Main.practiceMode) {
      int s = strikes;
      if (s > 0) {
        System.out.println(s + " strikes left");
      } else {
        System.out.println("BOOM!");
        close();
        System.exit(0);
      }
    } else {
      System.out.println("you got something wrong");
    }
    return strikes;

  }

  public float timeLeft() {
    return ((float) ((startTime - (System.currentTimeMillis() / 10)) + (long) time * 100) / 100);
  }

  public void printTime() {
    System.out.print(timeLeft());
  }

  public int getStrikes() {
    return strikes;
  }

  public void close() {
    for (module i : modules) {
      i.kill();
    }
  }

  public String[] getPorts() {
    return ports;
  }

  public indicator[] getIndicators() {
    return indicators;
  }

  public int getBatteries() {
    return batteries;
  }

  public String getSerial() {
    return serial;
  }

  public module[] getModules() {
    return modules;
  }

  private class killer implements EventHandler<ActionEvent> {
    public void handle(ActionEvent event) {
      for (module i : modules) {
        i.stop();
      }
      System.out.println("please press return to complete the action of going back");
    }
  }

  private class noKill implements EventHandler<ActionEvent> {
    public void handle(ActionEvent event) {
      System.out.println("you cannot go back at this moment");
    }
  }

  public static String encode() {
    return "";
  }

  private static module pentadecimalToModule(bomb b, String c) {

    switch (c.toUpperCase().charAt(0)) {
      case '0':
        return new wires(b);
      case '1':
        return new button(b);
      case '2':
        return new keypad(b);
      case '3':
        return new simonSays(b);
      case '4':
        return new whosOnFirst(b);
      case '5':
        return new memory(b);
      case '6':
        return new morseCode(b);
      case '7':
        return new complexWires(b);
      case '8':
        return new wireSequence(b);
      case '9':
        return new maze(b);
      case 'A':
        return new password(b);
      case 'B':
        return new ventingGas(b);
      case 'C':
        return new capacitorDischarge(b);
      case 'D':
        return new knob(b);
      default:
        return null;
    }
  }

  protected void disallowBack() {
    backButton.set(new noKill());
  }
}
