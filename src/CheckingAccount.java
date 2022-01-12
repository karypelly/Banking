import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class CheckingAccount extends BankAccount {

	private String customer;
	private boolean open;
	private boolean suspended;
	public Long SIN;
	int choice;
	protected int balance;
	private int overdraftLimit; 
	protected static ArrayList<AccountActivity> accountLog = new ArrayList<>();
	public Date date; 
	public double limit;
	public long accountNumber;
	public int transfer;
	public Calendar c;
	public int overdrawnTimes;
	
	public Date debtSince;
	private DemandLoanAccount newDebt;
	
	public CheckingAccount(String customer, Long SIN, int choice, long accountNumber) {
		this.customer = customer;  
		this.open = true;
		this.suspended = false;
		this.balance = 0;
		this.SIN = SIN;
		this.choice = choice;
		this.overdraftLimit = 0;
		this.date = new Date();
		
		accountLog.add(new AccountActivity(SIN, customer, 0, 0, this.date, accountNumber, true, this.transfer, this.balance));
		
	}

	public void fees() {
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		String day = String.valueOf(dayOfMonth);
		if (day.equals("1")) {
			if (choice == 3) {
				this.balance -= 4;
				accountLog.add(new AccountActivity(SIN, customer, 0, 0, this.date, accountNumber, true, this.transfer, this.balance));
			}
		}
	}
		
	
	@Override
	public void withdrawlAmount(int amount) {
		
		if(this.debtSince != null) {
			if(( this.date.getTime() - this.debtSince.getTime() / (1000 * 60 * 60 * 24) ) == 90) 
			{
				suspendAccount();
			}
		}
		
		if (this.balance - amount < 0) {
			if (choice == 1) {
				balance -= 45;
				
			}else if (choice == 2) {
				this.balance -= (amount + 5);
			}else if (choice == 3) {
				this.balance -= amount;
			}
			
		}else if (this.balance >= amount){
			this.balance -= amount;
		}
		accountLog.add(new AccountActivity(this.SIN, this.customer, 0, amount, this.date, accountNumber, true, this.transfer, this.balance));
		
	}
	
	@Override
	public void depositAmount(int amount) {
		
		if (amount <= 0) {
			System.out.println("Enter a positive amount greater than 0!");
	    }else if (this.open == true && this.suspended == false) {
			this.balance += amount;
			accountLog.add(new AccountActivity(this.SIN, this.customer, amount, 0, this.date, this.accountNumber, this.open, this.transfer, this.balance));
		}else if (this.suspended == true){
			System.out.println("Account is suspended! transferring to DemandLoanAccount.");
			newDebt = new DemandLoanAccount(this);
			newDebt.depositAmount(amount);
			if (newDebt.Suspended == false) 
			{
				reactivateAccount();
				this.debtSince = null;
			}
		}else if (this.open == false) {
			System.out.println("Account is closed!");
		
		}
	}

	@Override
	public void createAccount(int choice) {
		CheckingAccount ca = new CheckingAccount(customer, SIN, choice, 0);
		ca.choice = choice;
	}

	@Override
	public void cancelAccount() {
		this.open = false;
		accountLog.add(new AccountActivity(this.SIN, this.customer, 0, 0, this.date, this.accountNumber, false, this.transfer, this.balance));
	}

	@Override
	public void suspendAccount() {
		this.suspended = true;
		
	}

	@Override
	public void reactivateAccount() {
		this.suspended = false;
		
	}

	@Override
	public int getBalance() {
		accountLog.add(new AccountActivity(this.SIN, this.customer, 0, 0, this.date, this.accountNumber, this.open, this.transfer, this.balance));
		return this.balance;
		
	}

	@Override
	public void terminateAccount() {
		this.open = false;
		
	}

	@Override
	public void setOverdraftOption(int option) {
		if (option != 1 || option != 2 || option != 3) {
			System.out.println("Enter choice 1, 2 or 3!");
		}
		this.choice = option;
		
	}

	@Override
	public void setLimit(int amount) {
		this.overdraftLimit = amount;
		
	}

	@Override
	public void transferAmount(BankAccount from, BankAccount to, int amount) {
		from.withdrawlAmount(amount);
		to.depositAmount(amount);
		
	}


	
	public static void insertionSort(ArrayList<AccountActivity> Array) {
	    int i, j, k, l, m, n;
	    for (k = 0; k < Array.size(); k ++) {
	    		for (l = k + 1; l < Array.size(); l++) {
	    			if (Array.get(k).SIN.equals(Array.get(l).SIN)) {
	    				for (m = 1; m < Array.size(); m++) {
	    		    			AccountActivity key = new AccountActivity((long) 0, "", 0, 0, new Date(), 0, true, 0, 0);
	    		    			key.SIN = Array.get(m).SIN;
	    		    			key.name = Array.get(m).name;
	    		    			key.withdrawl = Array.get(m).withdrawl;
	    		    			key.date = Array.get(m).date;
	    		    			key.acountNumber = Array.get(m).acountNumber;
	    		    			key.deposit = Array.get(m).deposit;
	    		    			key.transfer = Array.get(m).transfer;
	    		    	        key.balance = Array.get(m).balance;
	    		    	        key.open = Array.get(m).open;

	    		    			n = m;
	    		    			while((m > 0) && (Array.get(m - 1).date.after(key.date))) {
	    		    				Array.set(m,Array.get(m - 1));
	    		    				m--;
	    		    			}
	    		        Array.set(m,key);
	    				}
	    			}
	    		}
	    }
	    for (i = 1; i < Array.size(); i++) {
	    		AccountActivity key = new AccountActivity((long) 0, "", 0, 0, new Date(), 0, true, 0, 0);
	        key.SIN = Array.get(i).SIN;
	        key.name = Array.get(i).name;
	        key.withdrawl = Array.get(i).withdrawl;
	        key.date = Array.get(i).date;
	        key.acountNumber = Array.get(i).acountNumber;
	        key.deposit = Array.get(i).deposit;
	        key.transfer = Array.get(i).transfer;
	        key.balance = Array.get(i).balance;
	        key.open = Array.get(i).open;
	        j = i;
	        while((j > 0) && (Array.get(j - 1).SIN > key.SIN)) {
	            Array.set(j,Array.get(j - 1));
	            j--;
	        }
	        Array.set(j,key);
	    }
	}
	public static void saveAccountLog() throws Exception {
		 FileWriter fw = new FileWriter("file.txt");
		 for (int i = 0; i < accountLog.size(); i++) {
		      fw.write(accountLog.get(i) + "");
		    }
		 fw.close();
	}
	public static void retrieveAccountLog() throws IOException{
		String fileName = "file.txt";
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		br.close();
	}

	
	


}
