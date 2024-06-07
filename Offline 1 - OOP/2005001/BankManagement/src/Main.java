import bank.Bank;
import bank.BankManagementSystem;

public class Main{
    public static void main(String[] args) {

        // initializing and obtaining the bank for the first time
        Bank bank = Bank.getBank();
        BankManagementSystem bankManagementSystem = new BankManagementSystem(bank);

        String filepath = "src/input.txt";
        bankManagementSystem.run(filepath);

    }
}