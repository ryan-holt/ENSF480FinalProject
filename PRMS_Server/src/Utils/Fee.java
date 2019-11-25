package Utils;

import java.io.Serializable;

/**
 * The fee class which has to implement Serilizable and essentially
 * keeps track of the fees for the different listings and
 * how much it is and how long before they must pay again
 * @author  Gary Wu
 * @since November 25, 2019
 */
public class Fee implements Serializable {

    //Member variables
    private static final long serialVersionUID = 7L;
    private double feeAmount; // $
    private int feePeriod;     // days

    /**
     * Creates object fee with the amount and period
     * @param amount the amount of the fee
     * @param period the period the fee will last
     */
    public Fee(double amount, int period){
        feeAmount = amount;
        feePeriod = period;
    }
    /**
     * Creates object fee with the amount and sets
     * a default period of 7 days
     * @param amount the amount of the fee
     */
    public Fee(double amount){
        feeAmount = amount;
        feePeriod = 7;
    }

    // Getters and setters
    public double getFeeAmount() {
        return feeAmount;
    }

    public int getFeePeriod() {
        return feePeriod;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public void setFeePeriod(int feePeriod) {
        this.feePeriod = feePeriod;
    }
}
