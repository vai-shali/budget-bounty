package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

/**
* Entity class representing a Scheduler for handling scheduled payments and bills.
* @Author Vaishali
* @Since 2nd September, 2024
*/
@Entity
@Table(name = "scheduler")
public class Scheduler {

  /**
   * The unique identifier for the scheduler.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scheduler_id")
  private Integer schedulerId;

  /**
   * The user associated with this scheduler.
   */
  @ManyToOne
  @JoinColumn(name = "user_id")  // Foreign key column in the scheduler table
  private User user;

  /**
   * The type of bill for the scheduled payment.
   */
  @Column(name = "bill_type")
  private String billType;

  /**
   * The customer ID associated with the scheduled payment.
   */
  @Column(name = "customer_id")
  private Integer customerId;

  /**
   * The name of the bill for the scheduled payment.
   */
  @Column(name = "bill_name")
  private String billName;

  /**
   * The account number of the payee for the scheduled payment.
   */
  @Column(name = "payee_acc")
  private String payeeAcc;

  /**
   * The amount for the scheduled payment.
   */
  @Column(name = "amount")
  private double amount;

  /**
   * The due date for the scheduled payment.
   */
  @Column(name = "due_date")
  private Date dueDate;

  /**
   * The date when the payment is scheduled.
   */
  @Column(name = "scheduled_date")
  private Date scheduledDate;

  /**
   * Indicates if the scheduled payment is recurring.
   */
  @Column(name = "is_recurring")
  private Integer isRecurring; // Consider changing this to a boolean if appropriate

  /**
   * The frequency of the recurring payment (e.g., monthly, weekly).
   */
  @Column(name = "frequency")
  private String frequency;

  /**
   * The number of occurrences after which the payment schedule ends.
   */
  @Column(name = "end_after")
  private Integer endAfter;

  /**
   * The date by which the payment schedule ends.
   */
  @Column(name = "end_by")
  private Date endBy;

  /**
   * Indicates if the scheduled payment has been paid.
   */
  @Column(name = "is_paid")
  private Integer isPaid; // Consider changing this to a boolean if appropriate

  /**
   * Default constructor for Scheduler.
   */
  public Scheduler() {}

  /**
   * Parameterized constructor for creating a Scheduler with all fields.
   *
   * @param user The user associated with the scheduler.
   * @param billType The type of bill.
   * @param customerId The customer ID.
   * @param billName The name of the bill.
   * @param payeeAcc The account number of the payee.
   * @param amount The amount of the scheduled payment.
   * @param dueDate The due date of the payment.
   * @param scheduledDate The date when the payment is scheduled.
   * @param isRecurring Indicates if the payment is recurring.
   * @param frequency The frequency of the payment.
   * @param endAfter The number of occurrences after which the payment ends.
   * @param endBy The date by which the payment schedule ends.
   * @param isPaid Indicates if the payment has been made.
   */
  public Scheduler(User user, String billType, Integer customerId, String billName,
                   String payeeAcc, double amount, Date dueDate, Date scheduledDate, Integer isRecurring,
                   String frequency, Integer endAfter, Date endBy, Integer isPaid) {
//      this.schedulerId = schedulerId;
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

  /**
   * Gets the unique identifier for the scheduler.
   *
   * @return the scheduler ID
   */
  public Integer getSchedulerId() {
      return schedulerId;
  }

  /**
   * Sets the unique identifier for the scheduler.
   *
   * @param schedulerId the scheduler ID to set
   */
  public void setSchedulerId(Integer schedulerId) {
      this.schedulerId = schedulerId;
  }

  /**
   * Gets the user associated with this scheduler.
   *
   * @return the user
   */
  public User getUser() {
      return user;
  }

  /**
   * Sets the user associated with this scheduler.
   *
   * @param user the user to set
   */
  public void setUser(User user) {
      this.user = user;
  }

  /**
   * Gets the type of bill for the scheduled payment.
   *
   * @return the bill type
   */
  public String getBillType() {
      return billType;
  }

  /**
   * Sets the type of bill for the scheduled payment.
   *
   * @param billType the bill type to set
   */
  public void setBillType(String billType) {
      this.billType = billType;
  }

  /**
   * Gets the customer ID associated with the scheduled payment.
   *
   * @return the customer ID
   */
  public Integer getCustomerId() {
      return customerId;
  }

  /**
   * Sets the customer ID associated with the scheduled payment.
   *
   * @param customerId the customer ID to set
   */
  public void setCustomerId(Integer customerId) {
      this.customerId = customerId;
  }

  /**
   * Gets the name of the bill for the scheduled payment.
   *
   * @return the bill name
   */
  public String getBillName() {
      return billName;
  }

  /**
   * Sets the name of the bill for the scheduled payment.
   *
   * @param billName the bill name to set
   */
  public void setBillName(String billName) {
      this.billName = billName;
  }

  /**
   * Gets the account number of the payee for the scheduled payment.
   *
   * @return the payee account number
   */
  public String getPayeeAcc() {
      return payeeAcc;
  }

  /**
   * Sets the account number of the payee for the scheduled payment.
   *
   * @param payeeAcc the payee account number to set
   */
  public void setPayeeAcc(String payeeAcc) {
      this.payeeAcc = payeeAcc;
  }

  /**
   * Gets the amount for the scheduled payment.
   *
   * @return the amount
   */
  public double getAmount() {
      return amount;
  }

  /**
   * Sets the amount for the scheduled payment.
   *
   * @param amount the amount to set
   */
  public void setAmount(double amount) {
      this.amount = amount;
  }

  /**
   * Gets the due date for the scheduled payment.
   *
   * @return the due date
   */
  public Date getDueDate() {
      return dueDate;
  }

  /**
   * Sets the due date for the scheduled payment.
   *
   * @param dueDate the due date to set
   */
  public void setDueDate(Date dueDate) {
      this.dueDate = dueDate;
  }

  /**
   * Gets the date when the payment is scheduled.
   *
   * @return the scheduled date
   */
  public Date getScheduledDate() {
      return scheduledDate;
  }

  /**
   * Sets the date when the payment is scheduled.
   *
   * @param scheduledDate the scheduled date to set
   */
  public void setScheduledDate(Date scheduledDate) {
      this.scheduledDate = scheduledDate;
  }

  /**
   * Checks if the scheduled payment is recurring.
   *
   * @return the isRecurring flag
   */
  public Integer isRecurring() {
      return isRecurring;
  }

  /**
   * Sets whether the scheduled payment is recurring.
   *
   * @param isRecurring the isRecurring flag to set
   */
  public void setRecurring(Integer isRecurring) {
      this.isRecurring = isRecurring;
  }

  /**
   * Gets the frequency of the recurring payment.
   *
   * @return the frequency
   */
  public String getFrequency() {
      return frequency;
  }

  /**
   * Sets the frequency of the recurring payment.
   *
   * @param frequency the frequency to set
   */
  public void setFrequency(String frequency) {
      this.frequency = frequency;
  }

  /**
   * Gets the number of occurrences after which the payment schedule ends.
   *
   * @return the endAfter value
   */
  public Integer getEndAfter() {
      return endAfter;
  }

  /**
   * Sets the number of occurrences after which the payment schedule ends.
   *
   * @param endAfter the endAfter value to set
   */
  public void setEndAfter(Integer endAfter) {
      this.endAfter = endAfter;
  }

  /**
   * Gets the date by which the payment schedule ends.
   *
   * @return the endBy date
   */
  public Date getEndBy() {
      return endBy;
  }

  /**
   * Sets the date by which the payment schedule ends.
   *
   * @param endBy the endBy date to set
   */
  public void setEndBy(Date endBy) {
      this.endBy = endBy;
  }

  /**
   * Checks if the scheduled payment has been paid.
   *
   * @return the isPaid flag
   */
  public Integer isPaid() {
      return isPaid;
  }

  /**
   * Sets whether the scheduled payment has been paid.
   *
   * @param isPaid the isPaid flag to set
   */
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

