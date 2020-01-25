
package studos.gui;

import studos.logic.LoginValidator;

/**
 * Just a simple class to start our app.
 */
public final class GuiStarter {

    /**
     * Simple constructor for linter to shut up.
     */
    private GuiStarter() {
    }

    /**
    *Simple main class to start our GUI.
    *
    *@param args nothing.
    */

    public static void main(final String[] args) {
        LoginValidator log = new LoginValidator();
        log.metoda();


        LoginWindow.main(args);
    }

}
