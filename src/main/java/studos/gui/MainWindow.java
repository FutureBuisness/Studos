/**
 * Info about this package doing something for package-info.java file.
 */

package studos.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import studos.logic.I18N;

/**
 * Front end for main window.
 */

public class MainWindow {

    /**
     * It's fornt method for new window.
     *
     */

    public void mainWindow() {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle("Hello World!");
        final Button btn = new Button();
        btn.textProperty().bind(I18N.createStringBinding("button.name"));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        final int windowHeight = 300;
        final int windowWidth = 250;

        final StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, windowHeight, windowWidth));
        primaryStage.show();
    }
}

