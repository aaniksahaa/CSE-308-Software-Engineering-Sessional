package finance;

import java.util.HashMap;

public class Specification {
    private int initialOfficerCount = 2;
    private int initialCashierCount = 5;
    private int fixedDepositMaturityPeriod = 1;
    private double initialFund = 1000000;
    private double yearlyServiceCharge = 500;
    private double loanInterestPercentage = 10;
    public HashMap<String,Double>interestRateMap = new HashMap<>();
    public HashMap<String,Double>maximumAllowableLoanMap = new HashMap<>();

    public Specification(){

        interestRateMap.put(Constants.SAVINGS,10.0);
        interestRateMap.put(Constants.STUDENT,5.0);
        interestRateMap.put(Constants.FIXED_DEPOSIT,15.0);

        maximumAllowableLoanMap.put(Constants.SAVINGS,10000.0);
        maximumAllowableLoanMap.put(Constants.STUDENT,1000.0);
        maximumAllowableLoanMap.put(Constants.FIXED_DEPOSIT,100000.0);
    }

    public int getInitialOfficerCount() {
        return initialOfficerCount;
    }

    public void setInitialOfficerCount(int initialOfficerCount) {
        this.initialOfficerCount = initialOfficerCount;
    }

    public int getInitialCashierCount() {
        return initialCashierCount;
    }

    public void setInitialCashierCount(int initialCashierCount) {
        this.initialCashierCount = initialCashierCount;
    }

    public int getFixedDepositMaturityPeriod() {
        return fixedDepositMaturityPeriod;
    }

    public void setFixedDepositMaturityPeriod(int fixedDepositMaturityPeriod) {
        this.fixedDepositMaturityPeriod = fixedDepositMaturityPeriod;
    }

    public double getInitialFund() {
        return initialFund;
    }

    public void setInitialFund(double initialFund) {
        this.initialFund = initialFund;
    }

    public double getYearlyServiceCharge() {
        return yearlyServiceCharge;
    }

    public void setYearlyServiceCharge(double yearlyServiceCharge) {
        this.yearlyServiceCharge = yearlyServiceCharge;
    }

    public double getLoanInterestPercentage() {
        return loanInterestPercentage;
    }

    public void setLoanInterestPercentage(double loanInterestPercentage) {
        this.loanInterestPercentage = loanInterestPercentage;
    }
}
