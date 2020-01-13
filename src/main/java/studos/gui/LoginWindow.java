/**
 * Info about this package doing something for package-info.java file.
 */

package studos.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        AppLanguage.languageInitialization();
        /*
         * Setting window properties. Title, setting resizable to false
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
             .getResource("view/loginWindow.fxml"));
        final Parent root = loader.load();
        final int windowHeight = 494;
        final int windowWidth = 1110;

        // Temporary method that stores controlls
        // and handle controlls actions
        LoginLogic.controlls(loader, root, primaryStage);

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
