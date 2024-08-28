package model1;

public class Bank {
    private int bankId;
    private String bankName;
    private String ifsc;
    private String accountNumber;
    private String upiId;
    private String accountType;
    private double balance;
    private int userId;

    // Constructor
    public Bank(int bankId, String bankName, String ifsc, String accountNumber, String upiId, String accountType, double balance, int userId) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.accountNumber = accountNumber;
        this.upiId = upiId;
        this.accountType = accountType;
        this.balance = balance;
        this.userId = userId;
    }

    // Getters and Setters
    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
