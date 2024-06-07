package account;

import bank.Bank;
import finance.Constants;

public class FixedDepositAccount extends Account{
    private static int maturityPeriod = Bank.getBank().getSpecification().getFixedDepositMaturityPeriod();
    public FixedDepositAccount(String username, double initialDeposit){
        super(username, initialDeposit);
        System.out.println("Fixed Deposit account for " + username + " Created; initial balance "+ initialDeposit + "$");
    }
    @Override
    public void deposit(double amount) {
        if(amount < 50000){
            System.out.println("Sorry, The deposit amount must not be less than 50,000$ for Fixed Deposit Account");
        }
        else{
            super.deposit(amount);
        }
    }
    @Override
    public void withdraw(double amount) {
        if(Bank.getBank().getCurrentYear() - this.openingYear < maturityPeriod){
            System.out.println("Invalid transaction; current balance " + balance + "$");
        }
        else{
            super.withdraw(amount);
        }
    }
    @Override
    public void requestLoan(double amount) {
        double maximumAllowableLoan = Bank.getBank().getSpecification().maximumAllowableLoanMap.get(Constants.FIXED_DEPOSIT);
        if(this.getTotalRequestedLoan() + amount > maximumAllowableLoan){
            System.out.println("Sorry. The maximum allowable loan for Fixed Deposit account is " + maximumAllowableLoan + "$");
        }
        else{
            super.requestLoan(amount);
        }
    }
    @Override
    public void incrementOneYear(){
        addInterest(Bank.getBank().getSpecification().interestRateMap.get(Constants.FIXED_DEPOSIT));
        super.deduceYearlyCharge();
        super.incrementOneYear();
    }
}
