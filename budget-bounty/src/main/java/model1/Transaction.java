package model1;

import java.util.Date;

public class Transaction {
    private Integer transactionId;
    private Integer userId;
    private Date transactionDate;
    private String transactionName;
    private String fromAccountNumber;
    private String toAccountNumber;
    private String transactionType;
    private Double amount;
    private String referenceNumber;

    public Transaction(Integer transactionId, Integer userId, Date transactionDate, String transactionName,
                       String fromAccountNumber, String toAccountNumber, String transactionType,
                       Double amount, String referenceNumber) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.transactionName = transactionName;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.referenceNumber = referenceNumber;
    }

    // Getters and setters for all fields

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    
    @Override
    public String toString() {
        return "transactionId=" + transactionId +
                ", userId=" + userId +
                ", transactionDate=" + transactionDate +
                ", transactionName='" + transactionName + '\'' +
                ", fromAccountNumber='" + fromAccountNumber + '\'' +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", referenceNumber='" + referenceNumber + '\'';
    }
}
