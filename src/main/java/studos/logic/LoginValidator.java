package studos.logic;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Class for validating credencials to login.
 */
public class LoginValidator {
/**
 * Session of hibernate.
 */
private final Session session;
/**
 * Constructor to transiction session.
 * @param session1 session for database.
*/
    public LoginValidator(final Session session1) {
        this.session = session1;
    };

/**
 * Checks if provided login and password are correct.
 * @param login checks if login exist in database.
 * @param password checks if password exist in database.
 * @return returns instance of client.
*/
    public LoginClass check(final String login, final String password) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LoginClass> cr = builder.createQuery(LoginClass.class);
        Root<LoginClass> mo = cr.from(LoginClass.class);
        Predicate likeRestriction = builder.and(
            builder.like(mo.get("login"), login),
            builder.like(mo.get("password"), password)
        );

        cr.select(mo).where(likeRestriction);

        Query<LoginClass> query = session.createQuery(cr);
        List<LoginClass> x = query.getResultList();
        if (x.isEmpty()) {
            return null;
        }
        return x.get(0);
    }
}
