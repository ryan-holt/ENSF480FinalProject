package Utils;

import java.io.Serializable;

public class Fee implements Serializable {

    private static final long serialVersionUID = 7L;
    private double feeAmount; // $
    private int feePeriod;     // days

    public Fee(double amount, int period){
        feeAmount = amount;
        feePeriod = period;
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
