package Domain.Controllers;

import Presentation.Views.PeriodicalReportView;

public class PeriodicalReportController extends Controller {

    private PeriodicalReportView periodicalReportView;

    public PeriodicalReportController(PeriodicalReportView prv, ClientCommunicationController ccc){
        super(ccc);
        periodicalReportView = prv;
    }

    @Override
    public void displayView() {
        periodicalReportView.display();
    }

    @Override
    public void hideView() {
        periodicalReportView.hide();
    }
}
