package studos.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    @Transient
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
    @Column(name = "LOGIN", unique = true, nullable = false,
    length = lenght)
    private String login;
    /**
     * unique password of the account.
     */
    @Column(name = "PASSWORD", unique = false, nullable = false,
    length = lenght)
    private String password;

    /**
     * unique e-mail adress of the account.
     */
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    /**
     * Deflaut constructor.
     */

    public LoginClass() {
    }

    /**
     * Creates a new instance of Account.
     * @param login1 login.
     * @param password1 password.
     * @param email1 email.
     */
    public LoginClass(final String login1,
    final String password1,
    final String email1) {
        this.login = login1;
        this.password = password1;
        this.email = email1;
    }

    /**
     * Gets the E-mail of this account.
     *
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the E-mail of this account.
     * @param email1 e-mail of the account.
     */
    public void setEmail(final String email1) {
        this.email = email1;
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
