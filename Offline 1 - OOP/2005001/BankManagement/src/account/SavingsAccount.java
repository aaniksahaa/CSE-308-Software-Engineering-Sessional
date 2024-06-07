package account;

import bank.Bank;
import finance.Constants;

public class SavingsAccount extends Account{
    public SavingsAccount(String username, double initialDeposit){
        super(username, initialDeposit);
        System.out.println("Savings account for " + username + " Created; initial balance "+ initialDeposit + "$");
    }
    @Override
    public void withdraw(double amount) {
        if(balance - amount < 1000){
            System.out.println("Invalid transaction; current balance " + balance + "$");
        }
        else{
            super.withdraw(amount);
        }
    }
    @Override
    public void requestLoan(double amount) {
        double maximumAllowableLoan = Bank.getBank().getSpecification().maximumAllowableLoanMap.get(Constants.SAVINGS);
        if(this.getTotalRequestedLoan() + amount > maximumAllowableLoan){
            System.out.println("Sorry. The maximum allowable loan for Savings account is "+ maximumAllowableLoan + "$");
        }
        else{
            super.requestLoan(amount);
        }
    }
    @Override
    public void incrementOneYear(){
        addInterest(Bank.getBank().getSpecification().interestRateMap.get(Constants.SAVINGS));
        super.deduceYearlyCharge();
        super.incrementOneYear();
    }
}
