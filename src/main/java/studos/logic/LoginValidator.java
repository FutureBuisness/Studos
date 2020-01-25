package studos.logic;

import org.hibernate.Session;

public class LoginValidator {
    LoginClass loggg = new LoginClass();
    public LoginValidator(){

    };

    final DbConnect dbc = new DbConnect();
    final Session session = (Session) dbc.getSessionFactory().openSession();
    final String result = (String) session.
    createNativeQuery("select version()").
    getSingleResult();


    public void metoda(){
        session.getTransaction().begin();
        LoginClass log = (LoginClass) session.find(LoginClass.class, "admin");
        String login = log.getLogin();
        loggg.setLogin(login);
        String password = log.getPassword();
        loggg.setPassword(password);
        System.out.println(login);
        System.out.println(password);
        if (login.equals("admin") && password.equals("admin"))
        {
            System.out.println("Jest w bazie");
        } else {
            System.out.println("Nie ma w bazie");
        }
        System.out.println(loggg.getLogin());
        System.out.println(loggg.getPassword());


        session.getTransaction().commit();
        //session.close();
    }





}