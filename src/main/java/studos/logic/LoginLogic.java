/*
 * Info about this package doing something for package-info.java file.
 */

package studos.logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Back end from Oleg.
 */
public class LoginLogic {

    /**
     * It's a boolean for all method.
     */
    private boolean checkIt;

    /**
     * This method return if the iput data is right.
     *true If it is right.
     *false if it is not.
     *@param username just username.
     *@param password just password.
     *@return boolean
     */
    public boolean ifInitialIsRight(final String username,
                                    final String password) {
        if (username.equals("admin") && password.equals("admin")) {
            checkIt = true;
        } else {
            checkIt = false;
        }
        return checkIt;
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
            final Stage primaryStage = new Stage();
            primaryStage.setTitle("Hello World!");
            final Button btn = new Button();
            btn.setText("Say 'Hello World'");
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
}
