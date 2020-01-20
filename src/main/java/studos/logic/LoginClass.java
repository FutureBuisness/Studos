package studos.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Class used to login.
 */
@Entity
@Table(name = "ACCOUNTS")
public class LoginClass {
    /**
     * lenght for chars in the table for login and password.
     */
    private final int lenght = 20;
    /**
     * unique login id.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int loginId;
    /**
     * unique login of the account.
     */
    @SuppressWarnings("magicnumber")
    @Column(name = "LOGIN", unique = true, nullable = false,
    length = lenght)
    private String login;
    /**
     * unique password of the account.
     */
    @SuppressWarnings("checkstyle:magicnumber")
    @Column(name = "PASSWORD", unique = false, nullable = false,
    length = lenght)
    private String password;

    /**
     * Deflaut constructor.
     */

    public LoginClass() {
    }

    /**
     * Creates a new instance of Account.
     * @param login1 login.
     * @param password1 password.
     */
    public LoginClass(final String login1, final String password1) {
        this.login = login1;
        this.password = password1;
    }

    /**
     * Gets the Accounts id for this account.
     *
     * @return loginId
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * Sets the Accounts id for this account.
     * @param loginId1 id fort account.
     */
    public void setLoginId(final int loginId1) {
        this.loginId = loginId1;
    }

    /**
     * Gets the login for this account.
     *
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login for this account.
     *
     * @param login1 name.
     */
    public void setLogin(final String login1) {
        this.login = login1;
    }

    /**
     * Gets the password for this account.
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for this account.
     *
     * @param password1 string.
     */
    public void setPassword(final String password1) {
        this.password = password1;
    }

}
