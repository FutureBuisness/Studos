/**
 * Info about this package doing something for package-info.java file.
 */

package studos.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
     * @throws IOException
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        primaryStage.setTitle("Studos - Okno logowania aplikacji");
        final FXMLLoader loader =
         new FXMLLoader(GuiStarter.class.getResource("view/loginWindow.fxml"));
        final Parent root = loader.load();

        final int windowHeight = 400;
        final int windowWidth = 642;

        final Button loginButton =
        (Button) loader.getNamespace().get("loginButton");
        final TextField usernameText =
        (TextField) loader.getNamespace().get("username");
        final PasswordField passwordText =
        (PasswordField) loader.getNamespace().get("password");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                final String username = usernameText.getText();
                final String password = passwordText.getText();

                // if (!username.isEmpty() && !password.isEmpty()) {
                //     /**
                //     * Waiting for error showing text.
                //     */
                // } else {
                //     /**
                //     * Waiting for back end method.
                //     */
                // }
            }
        });
        Scene scene = new Scene(root, windowWidth, windowHeight);
        scene.getStylesheets().add("view/loginWindow.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
