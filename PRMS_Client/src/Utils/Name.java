package Utils;

import java.io.Serializable;

public class Name implements Serializable {

    private static final long serialVersionUID = 5L;
    private String firstName;
    private String lastName;

    public Name(String f, String l){
        firstName = f;
        lastName = l;
    }

}
