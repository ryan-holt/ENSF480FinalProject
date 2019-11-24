package Domain.Controllers;

import Presentation.Views.PeriodicalReportView;

public class PeriodicalReportController extends Controller {

    private PeriodicalReportView periodicalReportView;

    public PeriodicalReportController(PeriodicalReportView prv, ClientCommunicationController ccc){
        super(ccc);
        periodicalReportView = prv;

        periodicalReportView.addCloseReportButtonListener(e -> closeReportListen());
    }

    public void closeReportListen(){
        periodicalReportView.setVisible(false);
        clientCommunicationController.getMainController().displayView();
    }

    @Override
    public void displayView() {
        periodicalReportView.display();
    }

    @Override
    public void hideView() {
        periodicalReportView.hide();
    }

    public PeriodicalReportView getPeriodicalReportView() {
        return periodicalReportView;
    }

    public void setPeriodicalReportView(PeriodicalReportView periodicalReportView) {
        this.periodicalReportView = periodicalReportView;
        periodicalReportView.addCloseReportButtonListener(e -> closeReportListen());
    }
}
