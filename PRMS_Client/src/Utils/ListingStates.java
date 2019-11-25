package Utils;

/**
 * Just an interface that has the different states
 * a listing can be in
 * @author  Gary Wu
 * @since November 25, 2019
 */
public interface ListingStates {

    static final String NOT_ACTIVE = "Not Active";
    static final String ACTIVE = "Active";
    static final String RENTED = "Rented";
    static final String CANCELLED = "Cancelled";
    static final String SUSPENDED = "Suspended";
    static final String NO_INPUT = "-";

}
