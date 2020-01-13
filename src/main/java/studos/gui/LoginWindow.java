/**
 * Info about this package doing something for package-info.java file.
 */

package studos.gui;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import studos.logic.AppLanguage;
import studos.logic.LoginLogic;
import studos.logic.UserConfigReader;


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
     * Variable that store 'X' from login window position.
     */

    private double xOffset = 0;
    /**
     * Variable that store 'Y' from login window position.
     */
    private double yOffset = 0;
    /**
     * Method that runs front-end of login window.
     * @throws IOException IOexception
     */

    @Override
    public void start(final Stage primaryStage) throws IOException {
        /*
         * Shearch for userData.config file and if it exists
         * and it's readable then reads data from last sucessfuly login.
        */
        UserConfigReader.readUserData();
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

        // Loading controlls from .fxml file and setting size of window.
        final FXMLLoader loader =
             new FXMLLoader(GuiStarter.class
               .getResource("view/loginWindow.fxml"), AppLanguage.loadLang());

        loader.setResources(AppLanguage.loadLang());

        final Parent root = loader.load();
        final int windowHeight = 494;
        final int windowWidth = 1110;

        // Creating variables from content in front-end controlls.
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
        final Text loginMessageText =
                  (Text) loader.getNamespace()
                  .get("loginMessageText");
        final CheckBox rememberCheckBox =
                    (CheckBox) loader.getNamespace().get("rememberMeCheckbox");
        //Todo - to backend file
        if (UserConfigReader.ifDataIsReady()) {
            try {
                rememberCheckBox.setSelected(true);
                usernameText.setText(UserConfigReader.getUsername());
                passwordText.setText(UserConfigReader.getPassword());
            } catch (final Exception e) {
              }
        }
        /*
         * Window draggable method.
         * This action will allow user to drag window.
        */
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
                root.requestFocus();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
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
                final LoginLogic loginLogic = new LoginLogic();
                if (loginLogic.ifPlaceIsEmpty(username, password)) {
                    loginMessageText
                    .setText("Dane logowania nie zostały uzupełnione.");
                } else {
                    if (loginLogic.ifInitialIsRight(username, password)) {
                        loginLogic.secondWindow(username, password);
                        if (rememberCheckBox.isSelected()) {
                            UserConfigReader.saveUserData(usernameText
                                 .getText(), passwordText.getText(), "");
                        } else {
                            UserConfigReader.deleteUserData();
                        }
                        primaryStage.close();
                    } else {
                        loginMessageText
                        .setText(AppLanguage.getStBundle("loginAttention"));
                    }
                }
                loginButton.requestFocus();
            }
        });
        /*
         * Setting scene with settings declared above
         * and loading .css loginWindow file.
        */
        final Scene scene = new Scene(root, windowWidth, windowHeight);
        scene.getStylesheets()
                 .add(getClass()
                 .getResource("view/loginWindow.css").toExternalForm());
        /*
         * Showing scene.
         * And controlled reset focus for window before start.
        */
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
