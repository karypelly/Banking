
public abstract class BankAccount {
	
	
	public abstract void withdrawlAmount(int amount);
	public abstract void depositAmount(int amount);
	public abstract void createAccount(int choice); 
	public abstract void cancelAccount();
	public abstract void suspendAccount();
	public abstract void reactivateAccount();
	public abstract int getBalance();
	public abstract void terminateAccount();
	public abstract void setOverdraftOption(int option);
    public abstract void setLimit(int amount);
    public abstract void transferAmount(BankAccount from, BankAccount to, int amount);
	public abstract void fees(); 
	
}
