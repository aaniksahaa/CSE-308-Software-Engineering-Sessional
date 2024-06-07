package bank;

import account.Account;
import account.FixedDepositAccount;
import account.SavingsAccount;
import account.StudentAccount;
import employee.Employee;
import finance.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BankManagementSystem {

    private Bank bank;
    private Account loggedInAccount = null;
    private Employee loggedInEmployee = null;

    private String[] accountCommands = {"Deposit", "Withdraw", "Query", "Request"};

    private String[] employeeCommands = {"Approve", "Change", "Lookup", "See"};

    public BankManagementSystem(Bank bank){
        this.bank = bank;
    }
    public void run(String filepath)  {
        try {
            File inputFile;
            inputFile = new File(filepath);
            Scanner scanner = new Scanner(inputFile);

            //File inputFile = new File("src/input2.txt");

            // May be replaced by System.in if required
            //Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                if (inputLine.isEmpty()) {
                    break;
                }
                // Splitting the input line into words
                String[] words = inputLine.split("\\s+");

                ArrayList<String> arguments = new ArrayList<>();

                String command = words[0];

                for (int i = 1; i < words.length; i++) {
                    arguments.add(words[i]);
                }

                if (command.equalsIgnoreCase("Create")) {
                    if (isLoggedIn()) {
                        handleLoggedIn();
                        continue;
                    }
                    createAccount(arguments);
                } else if (command.equalsIgnoreCase("Open")) {
                    if (isLoggedIn()) {
                        handleLoggedIn();
                        continue;
                    }
                    // Account or Employee Login
                    String username = arguments.get(0);
                    Employee requestedEmployee = bank.employeeMap.get(username);
                    if (requestedEmployee == null) { // Then it is account login
                        handleAccountLogin(username);
                    } else {
                        handleEmployeeLogin(username);
                    }
                } else if (command.equalsIgnoreCase("Close")) {
                    if (isLoggedIn()) {
                        handleClose();
                    } else {
                        System.out.println("No Account or Employee is currently logged in.");
                    }
                }
                // only Bank Management can do it
                else if (command.equalsIgnoreCase("INC")) {
                    if (isLoggedIn()) {
                        handleLoggedIn();
                    }
                    bank.incrementOneYear();
                } else if (checkAccountCommand(command)) {  // Handling Account Commands
                    if (loggedInAccount == null) {
                        System.out.println("Sorry. No account is currently logged in.");
                        continue;
                    } else {
                        handleAccountCommand(command, arguments);
                    }
                } else if (checkEmployeeCommand(command)) {  // Handling Employee Commands
                    if (loggedInEmployee == null) {
                        System.out.println("Sorry. No employee is currently logged in.");
                        continue;
                    } else {
                        handleEmployeeCommand(command, arguments);
                    }
                }
            }
            // Close the scanner
            scanner.close();
        }catch(FileNotFoundException e) {
            System.out.println("\n\nSorry, the requested input file does not exist.");
            //e.printStackTrace();
        }
    }
    private Boolean isLoggedIn(){
        return (loggedInAccount != null || loggedInEmployee != null);
    }
    private void handleAccountLogin(String username){
        Account requestedAccount = bank.accountMap.get(username);
        if(requestedAccount == null){
            System.out.println("Provided name does not match any Account or Employee");
        } else{
            loggedInAccount = requestedAccount;
            System.out.println("Welcome back, " + loggedInAccount.getUsername());
        }
    }
    private void handleEmployeeLogin(String username){
        Employee requestedEmployee= bank.employeeMap.get(username);
        loggedInEmployee = requestedEmployee;
        System.out.print(loggedInEmployee.getName() + " active. ");
        loggedInEmployee.checkPendingLoans();
    }
    private void handleClose(){
        if(loggedInAccount != null){
            System.out.println("Transactions Closed for " + loggedInAccount.getUsername());
            loggedInAccount = null;
        } else if (loggedInEmployee != null) {
            System.out.println("Operations for " + loggedInEmployee.getName() + " closed");
            loggedInEmployee = null;
        }
    }
    private void handleLoggedIn(){
        String currentName;
        if(loggedInAccount != null) { currentName = loggedInAccount.getUsername(); }
        else { currentName = loggedInEmployee.getName(); }
        System.out.println("Please close your current login as " + currentName + " first");
    }
    private double parseAmount(String str){
        return Double.parseDouble(str.replace(",",""));
    }
    private Boolean checkAccountCommand(String command){
        for(String c: accountCommands){
            if(command.equalsIgnoreCase(c)){
                return true;
            }
        }
        return false;
    }

    private void handleAccountCommand(String command, ArrayList<String>arguments){
        if (command.equalsIgnoreCase("Deposit")) {
            double amount = parseAmount(arguments.get(0));
            loggedInAccount.deposit(amount);
        }
        else if (command.equalsIgnoreCase("Withdraw")) {
            double amount = parseAmount(arguments.get(0));
            loggedInAccount.withdraw(amount);
        }
        else if (command.equalsIgnoreCase("Query")) {
            loggedInAccount.query();
        }
        else if (command.equalsIgnoreCase("Request")) {
            double amount = parseAmount(arguments.get(0));
            loggedInAccount.requestLoan(amount);
        }else{
            System.out.println("Invalid Command for Account");
        }
    }
    private Boolean isUniqueUsername(String username){
        return bank.accountMap.get(username) == null;
    }
    private void createAccount(ArrayList<String>arguments){
        String username = arguments.get(0);
        if(isUniqueUsername(username) == false){
            System.out.println("Sorry an account with username " + username + " already exists");
            return;
        }
        double initialDeposit = Double.parseDouble(arguments.get(2));
        Account newAccount = null;
        String account_type = arguments.get(1);
        if(account_type.equalsIgnoreCase(Constants.SAVINGS)){
            newAccount = new SavingsAccount(username,initialDeposit);
        } else if (account_type.equalsIgnoreCase(Constants.STUDENT)) {
            newAccount = new StudentAccount(username,initialDeposit);
        } else if (account_type.equalsIgnoreCase(Constants.FIXED_DEPOSIT)){
            if(initialDeposit < 100000){
                System.out.println("Sorry. Initial deposit must be at least 100,000$ for a Fixed Deposit account.");
                return;
            }
            else{
                newAccount = new FixedDepositAccount(username,initialDeposit);
            }
        }
        bank.addAccount(newAccount);
        loggedInAccount = newAccount;
    }
    private Boolean checkEmployeeCommand(String command){
        for(String c: employeeCommands){
            if(command.equalsIgnoreCase(c)){
                return true;
            }
        }
        return false;
    }
    private void handleEmployeeCommand(String command, ArrayList<String>arguments){
        if (command.equalsIgnoreCase("Approve") && arguments.get(0).equalsIgnoreCase("Loan")) {
            loggedInEmployee.approveLoan();
        }
        else if (command.equalsIgnoreCase("Change")) {
            String type = arguments.get(0);
            double rate = parseAmount(arguments.get(1));
            loggedInEmployee.changeInterestRate(type,rate);
        }
        else if (command.equalsIgnoreCase("Lookup")) {
            String username = arguments.get(0);
            loggedInEmployee.lookupAccount(username);
        }
        else if (command.equalsIgnoreCase("See")) {
            loggedInEmployee.seeInternalFund();
        }
        else{
            System.out.println("Invalid Command for Employee");
        }
    }
}

/*

Create Alice Student 1000
Deposit 20000
Withdraw 12,000
Query
Request 500
Close
Open O1
Approve Loan
Change Student 7.50
Lookup Alice
See
Close
Open Alice
Query
Close
INC
Open Alice
Query
Close
Create Bob Fixed-Deposit 1000
Create Bob Fixed-Deposit 200000
Deposit 20000
Deposit 70000
Withdraw 10000
Query
Open Alice
INC
Close
Create Alice Fixed-Deposit 1000000
INC
INC
INC
Open Alice
Query

 */
