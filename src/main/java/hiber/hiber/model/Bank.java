package hiber.hiber.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * @author Vishal
 * @Since 2nd September, 2024
 */
@Entity
@Table(name="bank")
public class Bank {
	@Id
	@Column(name="bank_id")
    private int bankId;
    @Column(name="bank_name")
	private String bankName;
    private String ifsc;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "upi_id")
    private String upiId;
    @Column(name = "account_type")
    private String accountType;
    private double balance;
//    @Column(name = "user_id")
//    private int userId;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_id")
    private User user;

    public Bank() {
		super();
	}

	// Constructor
    public Bank(int bankId, String bankName, String ifsc, String accountNumber, String upiId, String accountType, double balance, User userObj) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.accountNumber = accountNumber;
        this.upiId = upiId;
        this.accountType = accountType;
        this.balance = balance;
        this.user=userObj;
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

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
    
	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", bankName=" + bankName + ", ifsc=" + ifsc + ", accountNumber="
				+ accountNumber + ", upiId=" + upiId + ", accountType=" + accountType + ", balance=" + balance
				;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
