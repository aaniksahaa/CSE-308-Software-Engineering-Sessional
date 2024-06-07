package employee;

public class Cashier extends Employee{
    public Cashier(String name){
        super(name);
    }
    @Override
    public void checkPendingLoans() {
        // Does not have permission to do so
        System.out.println("");
        return;
    }
    @Override
    public void approveLoan(){
        System.out.println("You don’t have permission for this operation");
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
