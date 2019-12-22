/*
 * Info about this package doing something for package-info.java file.
 */

package studos.logic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App {

    private boolean checkIt; 
    
    /** 
    *Ta metoda otpowiada za poprawność wrowadzonych danych.
    *true jeżeli dane są poprawne. 
    *false jeżli nie są.
    *@return boolean of right initialize
    */ 
 
    public boolean ifInitialIsRight(final String name, final String password) {
        if (name.equals("sql") && password.equals("sql")) {
            checkIt = true;
        } else if (!name.equals("sql") && (!password.equals("sql"))) {
            checkIt = false;
        }
        return checkIt;
    }

    /**
     * Ta otpowiada za placeholder jeżeli jest pusty to jest false jeżeli jest cpś wpisane true.
     * 
     * @return boolean True of not empty place.
     * 
     */

    public boolean ifPlaceIsEmpty(final String name, final String password) {
        if (name.isEmpty() || password.isEmpty()) {
            checkIt = false;
        } else {
            checkIt = true;
        }
        return checkIt;
    }

    /**
     * Here is should be a new window.
     * 
     * @param name just name
     * 
     * 
     */
    
    public void secondWindow(final String name, final String password) {
        if (ifInitialIsRight(name, password) == true) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("contactdialog.fxml"));
                Scene scene = new Scene((Parent) fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        }
        
    }
}
