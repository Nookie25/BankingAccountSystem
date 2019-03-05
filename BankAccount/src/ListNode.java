//Node for Account Linked List
import java.io.Serializable;
public class ListNode implements Serializable{
	public Account info;
	public ListNode next;
	
	public ListNode(Account i) {
		 this(i,null);
		 }
		 public ListNode(Account i, ListNode n){
		 info = i; next = n;
		 }
		 
}

