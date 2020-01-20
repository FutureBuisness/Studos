
package studos.gui;

import org.hibernate.Session;
import studos.logic.LoginClass;

import studos.logic.DbConnect;

/**
 * Just a simple class to start our app.
 */
public final class GuiStarter {

    /**
    *Simple constructor for linter to shut up.
    */
    private GuiStarter() {
    }

    /**
    *Simple main class to start our GUI.
    *
    *@param args nothing.
    */

    public static void main(final String[] args) {
        final DbConnect dbc = new DbConnect();
        final Session session = (Session) dbc.getSessionFactory().openSession();
        final String result = (String) session.
        createNativeQuery("select version()").
        getSingleResult();
        System.out.println(result);

        final LoginClass log = new LoginClass("admin", "admin");

        session.save(log);

        session.getTransaction().commit();
        session.close();



        LoginWindow.main(args);
    }

}
