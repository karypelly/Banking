import java.util.Comparator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;
public class test {

	 //setting todays date for testing
	
		
	
	
	public static void main(String[] args) throws Exception {
		LocalDate d = LocalDate.now();
		BankAccount ba1 = new CheckingAccount("Kary Shane", (long) 123456, 1, 0001);
		BankAccount ba2 = new CheckingAccount("Sandi Pelly", (long) 987654, 2, 0002);
		BankAccount ba3 = new CheckingAccount("Kayla Faye", (long) 987654, 3, 0003);
		
		//test for option 1 overdraft
		ba1.withdrawlAmount(25);
		ba1.withdrawlAmount(200);
		ba1.withdrawlAmount(20);
		
		//repay overdraft for option 1
		ba1.depositAmount(1000);
		
		//test for option 2 overdraft
		ba2.withdrawlAmount(25);
		ba2.withdrawlAmount(200);
		ba2.withdrawlAmount(20);
		
		//repay overdraft for option 2
		ba2.depositAmount(1000);
		
		CheckingAccount.saveAccountLog();
		CheckingAccount.retrieveAccountLog();
		
	}
	
 
}
