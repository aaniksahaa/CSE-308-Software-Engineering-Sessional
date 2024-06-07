package finance;

import bank.Bank;
import employee.Employee;

public class Loan {
    private double amount;
    private int requestingYear;
    private int approvalYear;
    private int repayYear;
    private String approvedByEmployeeName;
    // Because while initiating a loan, we only know the amount and requestingYear
    public Loan(double amount, int requestingYear) {
        this.amount = amount;
        this.requestingYear = requestingYear;
        this.approvalYear = Constants.NOT_APPLICABLE; // denoting not approved
        this.repayYear = Constants.NOT_APPLICABLE;    // denoting not paid back
        this.approvedByEmployeeName = null;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getRequestingYear() {
        return requestingYear;
    }

    public void setRequestingYear(int requestingYear) {
        this.requestingYear = requestingYear;
    }

    public int getApprovalYear() {
        return approvalYear;
    }

    public void setApprovalYear(int approvalYear) {
        this.approvalYear = approvalYear;
    }

    public int getRepayYear() {
        return repayYear;
    }

    public void setRepayYear(int repayYear) {
        this.repayYear = repayYear;
    }

    public String getApprovedByEmployeeName() {
        return approvedByEmployeeName;
    }

    public void setApprovedByEmployeeName(String approvedByEmployeeName) {
        this.approvedByEmployeeName = approvedByEmployeeName;
    }
}
