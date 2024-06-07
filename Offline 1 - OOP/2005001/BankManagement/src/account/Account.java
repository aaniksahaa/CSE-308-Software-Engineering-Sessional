package account;

import bank.Bank;
import finance.Constants;
import finance.Loan;

import java.util.ArrayList;

public abstract class Account {
    private String username;

    protected double balance;
    protected ArrayList<Loan>requestedLoans = new ArrayList<>();
    protected ArrayList<Loan>approvedLoans = new ArrayList<>();
    protected int openingYear;

    public Account(String username, double initialDeposit) {
        this.username = username;
        this.balance = initialDeposit;
        this.openingYear = Bank.getBank().getCurrentYear();
    }
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + "$ deposited; current balance " + this.balance +"$");
    }
    public void withdraw(double amount) {
        if(amount > balance) {
            System.out.println("Invalid transaction; current balance " + balance + "$");
        } else {
            balance -= amount;
        }
    }
    public void requestLoan(double amount) {
        //requestedLoan += amount;
        requestedLoans.add(new Loan(amount,Bank.getBank().getCurrentYear()));
        System.out.println("Loan request successful, sent for approval");
    }
    public void query() {
        System.out.print("Current balance " + balance + "$");
        double currentLoan = getTotalCurrentLoan();
        if(currentLoan > 0){
            System.out.print(", loan " + currentLoan + "$");
        }
        System.out.println("");
    }
    public void addInterest(double percentage){
        double amount = balance*(percentage/100.0);
        fetchFromBankFund(amount);
        balance += amount;
    }
    public void deduceLoanInterest(double amount){
        balance -= amount;
        transferToBankFund(amount);
    }
    public void deduceYearlyCharge(){
        double amount = Bank.getBank().getSpecification().getYearlyServiceCharge();
        balance -= amount;
        transferToBankFund(amount);
    }
    public void incrementOneYear(){
        double loanInterestPercentage = Bank.getBank().getSpecification().getLoanInterestPercentage();
        deduceLoanInterest((getTotalCurrentLoan() * loanInterestPercentage)/100);
    }

    public String getUsername() {
        return username;
    }

    public double getBalance(){
        return balance;
    }

    private void transferToBankFund(double amount){
        Bank.getBank().addToFund(amount);
    }
    private void fetchFromBankFund(double amount){
        Bank.getBank().removeFromFund(amount);
    }
    public double getTotalRequestedLoan(){
        double total = 0.0;
        for(Loan l: requestedLoans){
            total += l.getAmount();
        }
        return total;
    }
    protected double getTotalCurrentLoan(){
        double total = 0.0;
        for(Loan l: approvedLoans){
            // Check if it has been repaid
            if(l.getRepayYear() == Constants.NOT_APPLICABLE){
                total += l.getAmount();
            }
        }
        return total;
    }
    public ArrayList<Loan> getRequestedLoans() {
        return requestedLoans;
    }
    public ArrayList<Loan> getApprovedLoans() {
        return approvedLoans;
    }

    public void receiveRequestedLoans(){
        for(Loan l: requestedLoans){
            approvedLoans.add(l);
            balance += l.getAmount();
        }
        requestedLoans.clear();
    }
}
