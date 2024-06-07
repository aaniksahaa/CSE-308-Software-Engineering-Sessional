package account;

import bank.Bank;
import finance.Constants;

public class StudentAccount extends Account{
    public StudentAccount(String username, double initialDeposit){
        super(username, initialDeposit);
        System.out.println("Student account for " + username + " Created; initial balance "+ initialDeposit + "$");
    }
    @Override
    public void withdraw(double amount) {
        if(amount > 10000){
            System.out.println("Invalid transaction; current balance " + balance + "$");
        }
        else{
            super.withdraw(amount);
        }
    }
    @Override
    public void requestLoan(double amount) {
        double maximumAllowableLoan = Bank.getBank().getSpecification().maximumAllowableLoanMap.get(Constants.STUDENT);
        if(this.getTotalRequestedLoan() + amount > maximumAllowableLoan){
            System.out.println("Sorry. The maximum allowable loan for Student account is " + maximumAllowableLoan + "$");
        }
        else{
            super.requestLoan(amount);
        }
    }
    @Override
    public void incrementOneYear(){
        addInterest(Bank.getBank().getSpecification().interestRateMap.get(Constants.STUDENT));
        super.incrementOneYear();
    }
}
