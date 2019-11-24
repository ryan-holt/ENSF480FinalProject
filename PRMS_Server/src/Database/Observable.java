package Database;

import Utils.Listing;
import Utils.User;

public interface Observable {

    public void addObserver(User user);
    public void removeObserver(User u);
    public void notifyAllObservers(Listing newListing);

}
