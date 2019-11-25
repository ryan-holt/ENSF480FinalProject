package Domain.Controllers;

import Presentation.Views.PeriodicalReportView;

/**
 * This class is responsible for controlling the periodical report view
 * @author Harsohail Brar
 * @version 4.10.0
 * @since November 25, 2019
 */
public class PeriodicalReportController extends Controller {

    // Periodical Report View
    private PeriodicalReportView periodicalReportView;

    /**
     * Constructor to create the PeriodicalReportController object
     * @param clv PeriodicalReportView object
     * @param ccc ClientCommunicationController object
     */
    public PeriodicalReportController(PeriodicalReportView prv, ClientCommunicationController ccc){
        super(ccc);
        periodicalReportView = prv;

        periodicalReportView.addCloseReportButtonListener(e -> closeReportListen());
    }

    /**
     * Closes report and open main view when close report button is pressed
     */
    public void closeReportListen(){
        periodicalReportView.setVisible(false);
        clientCommunicationController.getMainController().displayView();
    }

    // Visibility functions
    @Override
    public void displayView() {
        periodicalReportView.display();
    }

    @Override
    public void hideView() {
        periodicalReportView.hide();
    }

    // Getters and Setters
    public PeriodicalReportView getPeriodicalReportView() {
        return periodicalReportView;
    }

    public void setPeriodicalReportView(PeriodicalReportView periodicalReportView) {
        this.periodicalReportView = periodicalReportView;
        periodicalReportView.addCloseReportButtonListener(e -> closeReportListen());
    }
}
