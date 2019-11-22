package Domain.Controllers;

import Presentation.Views.ManagerMainView;

public class ManagerMainController extends Controller implements Messages{

   private ManagerMainView managerMainView;

   public ManagerMainController(ManagerMainView mmv, ClientCommunicationController ccc){
       super(ccc);
       managerMainView = mmv;

       managerMainView.addChangeFeeListener(e -> changeFeeListen());
       managerMainView.addEditListingStateListener(e -> editListingStateListen());
       managerMainView.addGetEntityInfoListener(e -> getEntityInfoListen());
       managerMainView.addGetReportListener(e -> getReportListen());
   }

   public void changeFeeListen(){
       // TODO change fee
   }

   public void editListingStateListen(){
       // TODO edit listing state
   }

   public void getEntityInfoListen(){
       // TODO getEntity info listen
   }

   public void getReportListen(){
       // TODO get report listen
   }

    @Override
    public void displayView() {
        managerMainView.display();
    }

    @Override
    public void hideView() {
        managerMainView.hide();
    }
}
