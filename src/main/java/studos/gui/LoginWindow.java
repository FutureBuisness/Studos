/**
 * Info about this package doing something for package-info.java file.
 */

package studos.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import studos.logic.LoginLogic;


/**
 * Main front-end class.
 */

public class LoginWindow extends Application {
    /**
     * Simple main.
     * @param args args for main.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * Dunno what to do.
     * @throws IOException IOexception
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        /*
         * Setting window properties.
         * Title, setting resizable to false
         * and removing context menu from window.
        */
        primaryStage.setTitle("Studos - Okno logowania aplikacji");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons()
            .add(new Image(getClass()
            .getResourceAsStream("/loginWindow/logoIcon.png")));

        /*
         * loading controlls from .fxml file and setting size of window.
        */
        final FXMLLoader loader =
             new FXMLLoader(GuiStarter.class
             .getResource("view/loginWindow.fxml"));
        final Parent root = loader.load();

        final int windowHeight = 494;
        final int windowWidth = 1110;

        /*
         * Creating variables from content in front-end controlls.
        */
        final Button loginButton =
                 (Button) loader.getNamespace().get("loginButton");
        final Button minimalizeButton =
                 (Button) loader.getNamespace().get("minimalizeButton");
        final Button closeButton =
                 (Button) loader.getNamespace().get("closeButton");
        final TextField usernameText =
                  (TextField) loader.getNamespace()
                  .get("usernameTextField");
        final PasswordField passwordText =
                  (PasswordField) loader.getNamespace()
                  .get("passwordTextField");

        /*
         * Window close button.
         * This action will close app window.
        */
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                primaryStage.close();
            }
        });

        /*
         * Window minimalize button.
         * This action will minimalize app window.
        */
        minimalizeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                primaryStage.setIconified(true);
            }
        });

        /*
         * Button login execute.
         * This action will run back-end login method.
        */
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                final String username = usernameText.getText();
                final String password = passwordText.getText();

                /*
                 * Function below is checking user credentials.
                 * (login function)
                 */
                LoginLogic loginLogic = new LoginLogic();
                if (loginLogic.ifPlaceIsEmpty(username, password)) {
                    System.out.println("Login: Username or password is empty.");
                } else {
                    if (loginLogic.ifInitialIsRight(username, password)) {
                        loginLogic.secondWindow(username, password);
                    } else {
                        System.out.println("Login: Credential are incorrect.");
                    }
                }
            }
        });

        /*
         * Setting scene with settings declared above
         * and loading .css loginWindow file.
        */
        Scene scene = new Scene(root, windowWidth, windowHeight);
        scene.getStylesheets()
                 .add(getClass()
                 .getResource("view/loginWindow.css").toExternalForm());

        /*
         * Showing scene.
        */
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
