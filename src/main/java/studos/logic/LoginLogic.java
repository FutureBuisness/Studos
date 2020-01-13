/*
 * Info about this package doing something for package-info.java file.
 */

package studos.logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studos.gui.MainWindow;

/**
 * Back end from Oleg.
 */
public class LoginLogic {

    /**
     * It's a boolean for all method.
     */
    private boolean checkIt;

    /**
     * Variable that store 'X' from login window position.
     */
    private static double xOffset = 0;
    /**
     * Variable that store 'Y' from login window position.
     */
    private static double yOffset = 0;

    /**
     * This method return if the iput data is right.
     * @param username just username.
     * @param password just password.
     * @return True If it is right, false if it's not.
     */
    public boolean ifInitialIsRight(
        final String username, final String password) {
        if (username.equals("admin") && password.equals("admin")) {
            checkIt = true;
        } else {
            checkIt = false;
        }
        return checkIt;
    }

    /**
     * This is temporary method to handle back-end from controlls.
     * @param loader variable that store FXML file.
     * @param root variable that store window root.
     * @param primaryStage variable that store window stage.
     */
    public static void controlls(
        final FXMLLoader loader, final Parent root, final Stage primaryStage) {

        // Creating variables from content in front-end controlls.
        final Button loginButton =
             (Button) loader.getNamespace().get("loginButton");
        final Button minimalizeButton =
             (Button) loader.getNamespace().get("minimalizeButton");
        final Button closeButton =
             (Button) loader.getNamespace().get("closeButton");
        final ToggleButton polishLanguageButton =
             (ToggleButton) loader.getNamespace()
            .get("polishLanguageButton");
        final ToggleButton englishLanguageButton =
             (ToggleButton) loader.getNamespace()
             .get("englishLanguageButton");
        final TextField usernameText =
             (TextField) loader.getNamespace()
             .get("usernameTextField");
        final PasswordField passwordText =
             (PasswordField) loader.getNamespace()
             .get("passwordTextField");
        final Text loginMessageText =
             (Text) loader.getNamespace().get("loginMessageText");
        final CheckBox rememberCheckBox =
             (CheckBox) loader.getNamespace()
             .get("rememberMeCheckbox");

        // Todo - to backend file
        if (AppLanguage.getLanguage().equals("pl")) {
            polishLanguageButton.setSelected(true);
        } else {
            if (AppLanguage.getLanguage().equals("en")) {
                englishLanguageButton.setSelected(true);
            }
        }

        if (UserConfigReader.ifDataIsReady()) {
            try {
                rememberCheckBox.setSelected(true);
                usernameText.setText(UserConfigReader.getUsername());
                passwordText.setText(UserConfigReader.getPassword());
            } catch (final Exception e) {
            }
        }

        // Only event handlers above
        /*
         * Window draggable method. This action will allow user to drag window.
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
         * Language changing button.
         * This action will change application language to polish.
        */
        polishLanguageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                AppLanguage.setLanguage("pl");
                polishLanguageButton.setSelected(true);
            }
        });

        /*
         * Language changing button.
         * This action will change application language to english.
        */
        englishLanguageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                root.requestFocus();
                AppLanguage.setLanguage("en");
                englishLanguageButton.setSelected(true);
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
                        .setText("Nieprawidłowe dane logowania.");
                    }
                }
                loginButton.requestFocus();
            }
        });
    }

    /**
     * If placeholder is empty will show false if
     * there is something then its true.
     * It must help us to create usual urgen that placeholder is empty.
     *
     *@param username just username.
     *@param password just password.
     *@return boolean
     */
    public boolean ifPlaceIsEmpty(final String username,
                                  final String password) {
        if (username.isEmpty() || password.isEmpty()) {
            checkIt = true;
        } else {
            checkIt = false;
        }
        return checkIt;
    }

    /**
     * Here is should be a new window.
     * @param username just username
     * @param password just password
     *
     */

    public void secondWindow(final String username, final String password) {
        if (ifInitialIsRight(username, password)) {
            MainWindow newWindow = new MainWindow();
            newWindow.mainWindow();
        }
    }
}
