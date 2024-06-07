package employee;

import account.Account;
import bank.Bank;
import finance.Loan;

public abstract class Employee {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name){
        this.name = name;
    }
    public void lookupAccount(String username){
        Account requiredAccount = Bank.getBank().accountMap.get(username);
        if(requiredAccount != null){
            System.out.println(username + "’s current balance " + requiredAccount.getBalance() + "$");
        }
        else{
            System.out.println("Account with username " + username + " not found.");
        }
    }
    public void checkPendingLoans(){
        for(Account a: Bank.getBank().getAccountList()){
            if(a.getTotalRequestedLoan() > 0){
                System.out.println("There are loan approvals pending");
                return;
            }
        }
        System.out.println("No loan aprovals pending");
    }
    // Approve loans of all users irrespectively
    public void approveLoan(){
        int count = 0;
        for(Account a: Bank.getBank().getAccountList()){
            if(a.getTotalRequestedLoan() > 0){
                count++;
                for(Loan l: a.getRequestedLoans()){
                    fetchFromBankFund(l.getAmount());
                    l.setApprovedByEmployeeName(this.name);
                    l.setApprovalYear(Bank.getBank().getCurrentYear());
                }
                a.receiveRequestedLoans();
                System.out.println("Loan for " + a.getUsername() + " approved");
            }
        }
        if(count == 0){
            System.out.println("No pending Loans.");
        }
    }
    public void changeInterestRate(String type, double rate){
        Bank.getBank().getSpecification().interestRateMap.put(type,rate);
        System.out.println("Interest rate of " + type + " Account changed to " + rate);
    }
    private void transferToBankFund(double amount){
        Bank.getBank().addToFund(amount);
    }
    private void fetchFromBankFund(double amount){
        Bank.getBank().removeFromFund(amount);
    }

    public void seeInternalFund(){
        System.out.println("Internal Fund of Bank = " + Bank.getBank().getInternalFund());
    }
}
