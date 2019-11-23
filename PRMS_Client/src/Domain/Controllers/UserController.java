package Domain.Controllers;

import Presentation.Views.UserView;

public class UserController extends Controller implements Messages {

    private UserView userView;

    public UserController(UserView uv, ClientCommunicationController ccc){
        super(ccc);
        userView = uv;

        userView.addBackToMenuListener(e -> backToMenuListen());
    }

    public void backToMenuListen(){
        userView.setVisible(false);
        clientCommunicationController.getMainController().displayView();
    }

    @Override
    public void displayView() {
        userView.display();
    }

    @Override
    public void hideView() {
        userView.hide();
    }

    public UserView getUserView() {
        return userView;
    }
}
