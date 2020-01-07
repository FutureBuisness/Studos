/**
 * Info about this package doing something for package-info.java file.
 */

package studos.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main front-end class.
 */

public class AppFx extends Application {
    /**
     * Simple main.
     * @param args args for main.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * Dunno what to do.
     */
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        final int windowHeight = 300;
        final int windowWidth = 250;

        primaryStage.setScene(new Scene(root, windowHeight, windowWidth));
        primaryStage.show();
    }
}
