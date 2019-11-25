package Utils;

import java.io.Serializable;

/**
 * The name of the different users and this
 * implements serializable
 * @author  Gary Wu
 * @since November 25, 2019
 */
public class Name implements Serializable {

    //Member variables
    private static final long serialVersionUID = 5L;
    private String firstName;
    private String lastName;

    /**
     * Constructor for the name class
     * @param f first name
     * @param l last name
     */
    public Name(String f, String l){
        firstName = f;
        lastName = l;
    }

    //Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
