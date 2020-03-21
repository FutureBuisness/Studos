package studos.logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;



/**
 * Represents a human object in the studos ORM.
 */
@Entity
@Table(name = "HUMANS")
public class Human {
    /**
     * unique student id.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int humanId;
    /**
     * first name of the human.
     */
    private String firstName;
    /**
     * last name of the human.
     */
    private String lastName;
    /**
     * address of the human.
     */
    private String address;

    /**
     * Default constructor.
     */
    public Human() {
    }

    /**
     * Creates a new instance of Human.
     * @param firstName1 first name.
     * @param lastName1 last name.
     * @param address1 address.
     */
    public Human(final String firstName1, final String lastName1,
    final String address1) {
        this.firstName = firstName1;
        this.lastName = lastName1;
        this.address = address1;
    }

    /**
     * Gets the humans id for this human.
     *
     * @return human id.
     */
    public int getHumanId() {
        return humanId;
    }

    /**
     * Sets the humans id for this human.
     *
     * @param humanId1 id of human.
     */
    public void setHumanId(final int humanId1) {
        this.humanId = humanId1;
    }

    /**
     * Gets the first name for this human.
     *
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name for this human.
     *
     * @param firstName1 name.
     */
    public void setFirstName(final String firstName1) {
        this.firstName = firstName1;
    }

    /**
     * Gets the last name for this human.
     *
     * @return last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name for this human.
     *
     * @param lastName1 name.
     */
    public void setLastName(final String lastName1) {
        this.lastName = lastName1;
    }

    /**
     * Gets the address for this human.
     *
     * @return address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address for this human.
     *
     * @param address1 adress.
     */
    public void setAddress(final String address1) {
        this.address = address1;
    }

    /**
     * Method used by the UI to clear information on the screen.
     * @return String used in the navigation rules.
     */
    public String clear() {
        firstName = "";
        lastName = "";
        address = "";
        return "clear";
    }

}
