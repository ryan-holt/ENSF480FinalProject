
public class GUITester {

    public static void main(String[] args) {
        GUITester guiTester = new GUITester();
        guiTester.run();
    }

    public void run(){
        //testLoginView();
        //testMainView(new ManagerMainView());
        //testSearchListingView();
        //testCreateListingView();
        testListingView();
        //PeriodicalReportView();
    }

    public void PeriodicalReportView() {
        PeriodicalReportView prv = new PeriodicalReportView();
        prv.display();
    }

    public void testLoginView(){
        LoginView loginView = new LoginView();
        loginView.display();
    }

    public void testCreateListingView() {
        CreateListingView clv = new CreateListingView();
        clv.display();
    }

    public void testListingView(){
        ListingView listView = new ListingView();
        listView.display();
    }

    public void testMainView(MainView mainView){
        mainView.display();
    }

    public void testSearchListingView(){
        SearchListingView searchListingView = new SearchListingView();
        searchListingView.display();
    }

}
