
public class GUITester {

    public static void main(String[] args) {
        GUITester guiTester = new GUITester();
        guiTester.run();
    }

    public void run(){
        //testLoginView();
        //testMainView(new ManagerMainView());
        testSearchListingView();
    }

    public void testLoginView(){
        LoginView loginView = new LoginView();
        loginView.display();
    }

    public void testMainView(MainView mainView){
        mainView.display();
    }

    public void testSearchListingView(){
        SearchListingView searchListingView = new SearchListingView();
        searchListingView.display();
    }

}
