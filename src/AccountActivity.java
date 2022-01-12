import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.FileWriter;

public class AccountActivity {

	public Long SIN;
	public String name;
	public double withdrawl;
	public Date date;
	public long acountNumber;
	public double deposit;
	public boolean open;
	public double transfer;
	public double balance;

	
	public AccountActivity(Long SIN, String name, int withdrawl, int deposit, Date date, long accountNumber, 
			boolean open, int transfer, int balance){
		this.SIN = SIN;
		this.name = name;
		this.withdrawl = withdrawl;
		this.date = date;
		this.acountNumber = accountNumber;
		this.deposit = deposit;
		this.open = open;
		this.transfer = transfer;
		this.balance = balance;
		
	
	}	
	

    
    @Override
    public String toString() { 
        return ("Sin:       " + SIN + "\n" + "Name:      " + name + "\n" + 
    "Deposit:   " + withdrawl + "\nWthdrwl:   " + deposit + "\ndate:      " + 
        		date + "\nOpen:      " + open + "\nBalance:   " + balance + "\n" + "\n");
    } 
    
	
}
