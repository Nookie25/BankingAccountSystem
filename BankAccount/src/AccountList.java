//Bank Account Linked List and Available Methods
import java.io.Serializable;
import java.util.Collections;
public class AccountList implements Serializable{

protected ListNode head;

protected ListNode tail;

		  
		  int ListSize = 0;
	


	public AccountList() {
		head = tail = null;
	}

	public int GetListSize() {
		return ListSize;
	}
	
	public boolean isEmpty() {
		if(head==null) {
			return true;
		} else
			return false;
	}
	
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
	
	public float GetBalance(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.Balance;
	}
	
	//creates an int array to store the ID contents of the Account linked list
	public int [] CreateIntArray() {
		int [] array;
		array = new int[ListSize];
		int [] SortedArrayID = new int [ListSize];
		SortedArrayID = PrintSortInt(array);
		return SortedArrayID;
	}
	
	//transfers the linked list to an array and sorts it according to ID number directly
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
	
    //insertion sort for sorting Account by Name (A - Z)
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
    //insertion sort for sorting Account by Balance (Low to High)
	public Account [] CreateBalanceSortArray() {
		Account [] array = new Account[ListSize];
		Account [] SortedArrayAccount = new Account [ListSize];
		SortedArrayAccount = ListToArray(array);
		for(int i = 1;  i < SortedArrayAccount.length; i++) {
			Account temp;
			for (int j = i; j > 0; j--) {
				if ((SortedArrayAccount[j].Balance) < (SortedArrayAccount[j-1].Balance))
	            {
	                temp = SortedArrayAccount[j];
	                SortedArrayAccount[j] = SortedArrayAccount[j - 1];
	                SortedArrayAccount[j - 1] = temp;
	            }
	        }
		}
		
		return SortedArrayAccount;
	}
	
	//transfer the linked list to an array
	public Account[] ListToArray(Account [] array) {
		int i = 0;
		for (ListNode temp = head  ; temp != null ; temp = temp.next) {
			array[i] = temp.info;
			i++;
		}
		return array;
	}
	
	
   //Gets the Name of the entered account number
	public String GetName(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.Name;
	}
	
	public void AddAccount(Account add) {
		if (!isEmpty()){
			tail.next = new ListNode(add);
			tail = tail.next;
		} else head = tail = new ListNode(add);
		ListSize++;
	}
	
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
	
	public boolean InList(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2 != null;
	}
	
	public Account PrintInList(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		System.out.printf( "Name: " +"\t"+ temp2.info.Name  +"\t\t"+  "Account Number: " +"\t"+ temp2.info.AccNum +"\t"+ " Balance: " +"\t"+ temp2.info.Balance);
		System.out.println();
		return temp2.info;
	}
	
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
	
	public void PrintAll() {
		for (ListNode temp = head; temp != null; temp = temp.next) {
			System.out.printf( "Name: " +"\t"+ temp.info.Name  +"\t\t"+  "Account Number: " +"\t"+ temp.info.AccNum +"\t"+ " Balance: " +"\t"+ temp.info.Balance);
			System.out.println();
		}
			
		
	}
	



}
	

