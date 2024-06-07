package employee;

import bank.Bank;

public class Officer extends Employee{
    public Officer(String name){
        super(name);
    }
    @Override
    public void changeInterestRate(String type, double rate) {
        System.out.println("You don’t have permission for this operation");
    }
    @Override
    public void seeInternalFund(){
        System.out.println("You don’t have permission for this operation");
    }
}
