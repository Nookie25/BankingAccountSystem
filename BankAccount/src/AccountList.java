//Bank Account Linked List and Available Methods
import java.io.Serializable;
import java.text.DecimalFormat;
public class AccountList implements Serializable{

protected ListNode head;

protected ListNode tail;

		  
		  int ListSize = 0;
		  DecimalFormat Billion = new DecimalFormat("##0.####");

	/*
	 * Constructor
	 */
	public AccountList() {
		head = tail = null;
	}
	/**
	 * method GetListSize - this gets the number of accounts created
	 * @return ListSize - the number of accounts in the list
	 */
	public int GetListSize() {
		return ListSize;
	}
	/**
	 * method isEmpty -  This method checks if the list is Empty
	 * @return true if list isEmpty
	 * @return false if list is not empty
	 */
	public boolean isEmpty() {
		if(head==null) {
			return true;
		} else
			return false;
	}
	
	/**
	 * method Deposit - this method enables the user to add money to his/her account
	 * @param accnum - the account number input by the user
	 * @param value - the desired number of money to deposit by the user
	 * @return value - the amount deposited by the user
	 */
	public float Deposit (int accnum, float value) {
		Account fromACC = null;
		ListNode temp;
		for(temp = head; temp.next != null && temp.info.AccNum != accnum; temp = temp.next);
			if(temp.info.AccNum == accnum) {
				fromACC = temp.info;
			}
			fromACC.Balance = fromACC.Balance + value;
			return value;
	}
	/**
	 * method Withdraw - this allows the user to get money from his/her account
	 * @param accnum - account number input by the user
	 * @param value - the desired amount to be withdrawn by the user
	 * @return value - the amount withdrawn  
	 */
	public float Withdraw (int accnum, float value) {
		Account fromACC = null;
		ListNode temp;
		for(temp = head; temp.next != null && temp.info.AccNum != accnum; temp = temp.next);
			if(temp.info.AccNum == accnum) {
				fromACC = temp.info;
			}
			fromACC.Balance = fromACC.Balance - value;
			return value;
	}
	/**
	 * method GetBalance - the method allows the user to input his/her account number then outputs the account's total balance
	 * @param accnum - account number input by the user
	 * @return temp2.info.Balance - the total balance of the user 
	 */
	public float GetBalance(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.Balance;
	}
	
	/**
	 * method CreateIntArray - creates an int array to store the ID contents of the Account linked list
	 * @return SortedArrayID - the sorted account numbers in ascending order
	 */
	public int [] CreateIntArray() {
		int [] array;
		array = new int[ListSize];
		int [] SortedArrayID = new int [ListSize];
		SortedArrayID = PrintSortInt(array);
		return SortedArrayID;
	}
	
	/**
	 * method PrintSortInt - transfers the linked list to an array and sorts it according to ID number directly using insertion sort
	 * @param array - array with the linked list data
	 * @return array - a list of account numbers in order
	 */
	public int [] PrintSortInt(int [] array) {
		int i = 0;
		for (ListNode temp = head  ; temp != null ; temp = temp.next) {
			array[i] = temp.info.AccNum;
			i++;
		}
		int n = array.length; 
        for (i=1; i<n; ++i) { 
            int key = array[i]; 
            int j = i-1; 
  

            while (j>=0 && array[j] > key) 
            { 
                array[j+1] = array[j]; 
                j = j-1; 
            } 
            array[j+1] = key; 
        } 
        return array;
    } 
	
    /**
     * method CreateNameSortArray - bubble sort for sorting Account by Name (A - Z)
     * @return SortedArrayAccount - a list of names in order
     */
	public Account[] CreateNameSortArray() {
		Account [] array = new Account [ListSize];
		Account [] SortedArrayAccount = new Account [ListSize];
		SortedArrayAccount = ListToArray(array);
		for(int i = 1;  i < SortedArrayAccount.length; i++) {
			Account temp;
			for (int j = i; j > 0; j--) {
				if (( SortedArrayAccount[j].Name).compareTo((SortedArrayAccount[j-1].Name)) < 0)
	            {
	                temp = SortedArrayAccount[j];
	                SortedArrayAccount[j] = SortedArrayAccount[j - 1];
	                SortedArrayAccount[j - 1] = temp;
	            }
	        }
		}
		return SortedArrayAccount;
	}
    /**
     * method CreateBalanceSortArray - insertion sort for sorting Account by Balance (Low to High)
     * @return SortedArrayAccount - the accounts in ascending order according to balance
     */
	public Account [] CreateBalanceSortArray() {
		Account [] array = new Account[ListSize];
		Account [] SortedArrayAccount = new Account [ListSize];
		SortedArrayAccount = ListToArray(array);
		int n = array.length;
		 for (int i=1; i<n; ++i) { 
	            Account key = SortedArrayAccount[i]; 
	            int j = i-1; 
	            while (j>=0 && SortedArrayAccount[j].Balance > key.Balance) 
	            { 
	                SortedArrayAccount[j+1] = SortedArrayAccount[j]; 
	                j = j-1; 
	            } 
	            SortedArrayAccount[j+1] = key; 
	        } 
	        return SortedArrayAccount;
	    } 
		
	
	/**
	 * method ListToArray - transfer the linked list to an array
	 * @param array - the array that will receive the information of the linked list
	 * @return array - the array that is filled with the data of the linked list
	 */
	public Account[] ListToArray(Account [] array) {
		int i = 0;
		for (ListNode temp = head  ; temp != null ; temp = temp.next) {
			array[i] = temp.info;
			i++;
		}
		return array;
	}
	
	
   /**
    * method GetName - Gets the Name of the entered account number
    * @param accnum - account number input by the user
    * @return temp2.info.Name - name of the account number 
    */
	public String GetName(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.Name;
	}
	
	/**
	 * method AddAccount - enables the user to add an account
	 * @param add - the account to be added
	 */
	public void AddAccount(Account add) {
		if (!isEmpty()){
			tail.next = new ListNode(add);
			tail = tail.next;
		} else head = tail = new ListNode(add);
		ListSize++;
	}
	
	/**
	 * method DeleteAccount - enables the user to remove a created account
	 * @param accnum - account number input by the user
	 * @return del - returns the deleted account
	 */
	public Account DeleteAccount(int accnum) {
		Account del = null;
		ListNode temp;
		for(temp = head; temp.info.AccNum != accnum; temp = temp.next);
			if(temp.info.AccNum == accnum) {
				del = temp.info;
				if(head == tail && del.AccNum == head.info.AccNum){
					head=tail=null;
		
				}else if(del.AccNum == head.info.AccNum) {
					head = head.next;
					}else {
						ListNode pred, temp2;
						for(pred = head, temp2 = head.next; temp2 != null && temp2.info.AccNum != del.AccNum; pred = pred.next, temp2 = temp2.next);
						if(temp2 != null) {
							pred.next = temp2.next;
							if (temp2 == tail)
								tail = pred;
						}
					}
				
				}
			ListSize--;
		return del;
	}
	/**
	 * method InList - helps the user find an account number in the list
	 * @param accnum - account number input by the user
	 * @return temp2 - if the input account number by the user has a match in the list, temp2 returns true otherwise, false.
	 */
	public boolean InList(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2 != null;
	}
	/**
	 * method account PrintInLst - this method inputs a number and outputs the information of the object Account
	 * @param accnum - is the account number sent by the user
	 * @return temp2.info - prints the information of the specified account number
	 */
	public Account PrintInList(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		System.out.printf("%-55s %-45s %-45s\n", temp2.info.Name  , temp2.info.AccNum , Billion.format(temp2.info.Balance));
		return temp2.info;
	}
	
	/**
	 * method Transfer - this method enables the user to transfer transactions in between two accounts
	 * @param from - account number of the source account
	 * @param to - account number of the destination account 
	 * @param amount - amount of money to transfer
	 */
	public void Transfer(int from, int to, float amount) {
		Account fromACC = null;
		Account toACC = null;
		//pointer to source account
		ListNode temp;
		for(temp = head; temp.next != null && temp.info.AccNum != from; temp = temp.next);
			if(temp.info.AccNum == from) {
				fromACC = temp.info;
			}
		//pointer to destination account
		ListNode temp2;
		for(temp2 = head; temp2.next != null && temp2.info.AccNum != to; temp2 = temp2.next);
			if(temp2.info.AccNum == to) {
				toACC = temp2.info;
			}
			fromACC.Balance = fromACC.Balance - amount;
			toACC.Balance = toACC.Balance + amount;
	}
	/**
	 *method PrintAll - this method prints all accounts according to chronological order of creation
	 */
	public void PrintAll() {
		System.out.printf("%-50s %-50s %-50s\n", "Name", "Account Number", "Balance");
		for (ListNode temp = head; temp != null; temp = temp.next) {
			System.out.printf("%-55s %-45s %-45s\n", temp.info.Name  , temp.info.AccNum , Billion.format(temp.info.Balance));
		
		}
			
		
	}
	



}

