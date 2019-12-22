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
import javafx.stage.StageStyle;

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
        /**
         * Setting window properties.
         * Title, setting resizable to false
         * and removing context menu from window.
        */
        primaryStage.setTitle("Studos - Okno logowania aplikacji");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        /**
         * loading controlls from .fxml file and setting size of window.
        */
        final FXMLLoader loader =
         new FXMLLoader(GuiStarter.class.getResource("view/loginWindow.fxml"));
        final Parent root = loader.load();

        final int windowHeight = 494;
        final int windowWidth = 1110;

        /**
         * Creating variables from content in front-end controlls.
        */
        final Button loginButton =
        (Button) loader.getNamespace().get("loginButton");
        final TextField usernameText =
        (TextField) loader.getNamespace().get("usernameTextField");
        final PasswordField passwordText =
        (PasswordField) loader.getNamespace().get("passwordTextField");

        /**
         * Button login execute.
         * This action will run back-end login method.
        */
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

        /**
         * Setting scene with settings declared above
         * and loading .css loginWindow file.
        */
        Scene scene = new Scene(root, windowWidth, windowHeight);
        scene.getStylesheets()
        .add(getClass().getResource("view/loginWindow.css").toExternalForm());

        /**
         * Showing scene.
        */
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
