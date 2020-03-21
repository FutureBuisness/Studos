
package studos.gui;

import org.hibernate.Session;

import studos.logic.DbConnect;
import studos.logic.LoginLogic;

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
        //  LoginValidator log = new LoginValidator();
        // log.metoda();
        final DbConnect dbc = new DbConnect();
        final Session session = (Session) dbc.getSessionFactory().openSession();
        LoginLogic.setSession(session);
        LoginWindow.main(args);
    }

}
