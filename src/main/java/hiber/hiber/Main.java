package hiber.hiber;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import hiber.hiber.model.Bank;
import hiber.hiber.model.User;
import hiber.hiber.service.BankService;
import hiber.hiber.service.UserService;
import hiber.hiber.util.HibernateUtil;

public class Main {
	public static void bankCheck() {
		BankService bs=new BankService();
		UserService us=new UserService();
		User uobj=us.getUserById(1);
		Bank obj=new Bank(6,"Standard","IFSC0006","1234321","eve@okemp.com","Savings",5000.0,uobj);
		List<Bank> list=bs.getAllBanks();
		System.out.println("Before Insert");
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		bs.addBank(obj);

		System.out.println("After Insert");
		list=bs.getAllBanks();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		obj.setBalance(4500.0);
		bs.updateBank(obj);

		System.out.println("After Update");
		list=bs.getAllBanks();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		bs.deleteBank(6);

		System.out.println("After Delete");
		list=bs.getAllBanks();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
	}
	public static void userCheck() {
		UserService us=new UserService();
		User uobj=new User(6,"name","username","password","9876543210","email",0);
		List<User> list=us.getAllUsers();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		us.saveUser(uobj);
		User dobj=us.getUserById(6);
		System.out.println(dobj.toString());
		uobj.setRole(1);
		us.updateUser(uobj);
		list=us.getAllUsers();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
		System.out.println(us.existsByEmail("email"));
		dobj=us.findByEmailAndPassword("email", "password");
		System.out.println(dobj.toString());
		us.deleteUser(6);
		list=us.getAllUsers();
		list.forEach((objli)->{
			System.out.println(objli.toString());
		});
	}
	public static void main(String[] args) {
		bankCheck();
		userCheck();
	}
}
