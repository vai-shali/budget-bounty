package hiber.hiber.model;

//import java.sql.Date;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "scheduler")
public class Scheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduler_id")
    private Integer schedulerId;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign key column in the scheduler table
    private User user;

    @Column(name = "bill_type")
    private String billType;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "bill_name")
    private String billName;

    @Column(name = "payee_acc")
    private String payeeAcc;

    @Column(name = "amount")
    private double amount;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "scheduled_date")
    private Date scheduledDate;

    @Column(name = "is_recurring")
    private Integer isRecurring; // Consider changing this to a boolean if appropriate

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "end_after")
    private Integer endAfter;

    @Column(name = "end_by")
    private Date endBy;

    @Column(name = "is_paid")
    private Integer isPaid; // Consider changing this to a boolean if appropriate

    // Constructor without parameters
    public Scheduler() {}

    // Constructor with parameters
    public Scheduler(User user, String billType, Integer customerId, String billName, 
                     String payeeAcc, double amount, Date dueDate, Date scheduledDate, Integer isRecurring, 
                     String frequency, Integer endAfter, Date endBy, Integer isPaid) {
//        this.schedulerId = schedulerId;
        this.user = user;
        this.billType = billType;
        this.customerId = customerId;
        this.billName = billName;
        this.payeeAcc = payeeAcc;
        this.amount = amount;
        this.dueDate = dueDate;
        this.scheduledDate = scheduledDate;
        this.isRecurring = isRecurring;
        this.frequency = frequency;
        this.endAfter = endAfter;
        this.endBy = endBy;
        this.isPaid = isPaid;
    }

    // Getters and Setters
    public Integer getSchedulerId() {
        return schedulerId;
    }

    public void setSchedulerId(Integer schedulerId) {
        this.schedulerId = schedulerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getPayeeAcc() {
        return payeeAcc;
    }

    public void setPayeeAcc(String payeeAcc) {
        this.payeeAcc = payeeAcc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Integer isRecurring() {
        return isRecurring;
    }

    public void setRecurring(Integer isRecurring) {
        this.isRecurring = isRecurring;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getEndAfter() {
        return endAfter;
    }

    public void setEndAfter(Integer endAfter) {
        this.endAfter = endAfter;
    }

    public Date getEndBy() {
        return endBy;
    }

    public void setEndBy(Date endBy) {
        this.endBy = endBy;
    }

    public Integer isPaid() {
        return isPaid;
    }

    public void setPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Scheduler [schedulerId=" + schedulerId + ", billType=" + billType
                + ", customerId=" + customerId + ", billName=" + billName + ", payeeAcc=" + payeeAcc + ", amount="
                + amount + ", dueDate=" + dueDate + ", scheduledDate=" + scheduledDate + ", isRecurring=" + isRecurring
                + ", frequency=" + frequency + ", endAfter=" + endAfter + ", endBy=" + endBy + ", isPaid=" + isPaid + "]";
    }
}
