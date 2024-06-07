package bank;

import account.Account;
import account.FixedDepositAccount;
import account.SavingsAccount;
import account.StudentAccount;
import employee.Cashier;
import employee.Employee;
import employee.ManagingDirector;
import employee.Officer;
import finance.Specification;

import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
    private static Bank bank;
    private Specification specification;

    // Since it is called internalFund
    // It is regarded as a standalone entity
    // which is independent of all accounts.
    // However, when an account pays a charge
    // it is added to Bank's internal fund.
    private double internalFund;
    private int currentYear; // with respect to instantiation
    private ArrayList<Account>accountList = new ArrayList<>();
    private ArrayList<Employee>employeeList = new ArrayList<>();
    public HashMap<String, Account> accountMap = new HashMap<>();
    public HashMap<String, Employee> employeeMap = new HashMap<>();

    private Bank()
    {
        // Setting default Specifications
        specification = new Specification();

        currentYear = 0;
        internalFund = specification.getInitialFund();

        ArrayList<String> initial_employee_names = new ArrayList<>();
        this.addEmployee(new ManagingDirector("MD"));
        initial_employee_names.add("MD");
        for(int i=1; i<=specification.getInitialOfficerCount(); i++){
            String name = "O"+i;
            this.addEmployee(new Officer(name));
            initial_employee_names.add(name);
        }
        for(int i=1; i<=specification.getInitialCashierCount(); i++){
            String name = "C"+i;
            this.addEmployee(new Cashier(name));
            initial_employee_names.add(name);
        }
        // Printing Logs
        System.out.print("Bank Created; ");
        int total_initial_employees = initial_employee_names.size();
        for(int i=0; i<total_initial_employees-1; i++){
            System.out.print(initial_employee_names.get(i) + ", ");
        }
        System.out.println(initial_employee_names.get(total_initial_employees-1) + " created");
    }
    // To ensure thread safety of Singleton Class
    public static synchronized Bank getBank()
    {
        if(bank == null)
        {
            bank = new Bank();
        }
        return bank;
    }
    public Specification getSpecification() {
        return specification;
    }
    public int getCurrentYear() {
        return currentYear;
    }

    public double getInternalFund() {
        return internalFund;
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }
    public void addEmployee(Employee e){
        this.employeeList.add(e);
        this.employeeMap.put(e.getName(),e);
    }
    public void addAccount(Account a){
        this.accountList.add(a);
        this.accountMap.put(a.getUsername(),a);
    }
    public void incrementOneYear(){
        for(Account a: accountList){
            a.incrementOneYear();
        }
        currentYear++;
        System.out.println("1 year passed");
    }
    public void addToFund(double amount){
        internalFund += amount;
    }
    public void removeFromFund(double amount){
        internalFund -= amount;
    }
}
