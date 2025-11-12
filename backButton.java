/*
 * Name: Mitchell Donais
 * Date: 5/12/2024
 * Description: creates a back button
 * Sources: none to the best of my memory
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class backButton extends Application {
  static Button back;

  public static void begin() {
    launch();
  }

  public static void set(EventHandler<ActionEvent> event) {
    back.setOnAction(event);
  }

  public void start(Stage primaryStage) {
    back = new Button("Back");
    Scene scene = new Scene(back);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Back Button");
    primaryStage.show();
  }

}
