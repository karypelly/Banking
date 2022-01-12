import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Date;

public class DemandLoanAccount {
	private double debt;
	public boolean Suspended = true;
	//private String matchTo = "Balance:   [0-9]*.[0-9]{1,2}";
	private AccountActivity DemLoanAct;
	private Date date = new Date();
	
	protected ArrayList<AccountActivity> accountLog = new ArrayList<>();
	
	public DemandLoanAccount(CheckingAccount a)
	{ 
		this.accountLog = CheckingAccount.accountLog;
		
	}
	public DemandLoanAccount(CreditAccount b) {
		
		this.accountLog = CreditAccount.accountLog;
	}
	
	
	public void depositAmount(int deposit) //throws Exception 
	{
		readAccountLog();
		this.debt += deposit;
		
		try {
			saveAccountLog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An Error Has Occured in saving.");
		}
		if (this.debt >=0)
		{
			Suspended = reactivate();
		}
		
		
	}
	public boolean reactivate()
	{
	 System.out.println("Account has been reactivated");
	 return false;
	}
	
	public void saveAccountLog() throws Exception {
		writeAccountLog();
		 FileWriter fw = new FileWriter("file.txt");
		 accountLog.add(DemLoanAct);
		
		 for (int i = 0; i < accountLog.size(); i++) {
		      fw.write(accountLog.get(i) + "");
		    }
		 fw.close();
	}
	
	public void writeAccountLog()
	{
		DemLoanAct.balance = this.debt;
		DemLoanAct.date = this.date;
	}
	
	
	public void readAccountLog() 
	{
		this.DemLoanAct = this.accountLog.get(accountLog.size());
		this.debt = DemLoanAct.balance;
	}
	
	
	
	
//	public void readAccountLog() throws IOException{
//		String fileName = "file.txt";
//		File file = new File(fileName);
//		FileReader fr = new FileReader(file);
//		BufferedReader br = new BufferedReader(fr);
//		
//		String line;
//		
//		while((line = br.readLine()) != null)
//		{
//			if (Pattern.matches(matchTo, line) == true) {
//				String input[] = line.split(":   ");
//				//System.out.println(Double.parseDouble(input[1]));			
//				this.debt = Double.parseDouble(input[1]);
//			}
//		}
//		br.close();
//		System.out.println(this.debt);
//	}
	

}


