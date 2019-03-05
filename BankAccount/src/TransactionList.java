//Queue Class for Transactions of Client
import java.io.Serializable;
public class TransactionList implements Serializable {
	
	protected TransactionListNode head;
	
	protected TransactionListNode tail;
	
			int TransactionSize = 0;
			
			
		public TransactionList () {
			head = tail = null;
		}
		
		public boolean isEmpty() {
			if (head==null) {
				return true;
			} else 
				return false;
		}
		
		public int GetTransactionListSize() {
			return TransactionSize = 0;
		}
		
		public void enqueue (Transaction Trans) {
			if(head == null) {
				head = new TransactionListNode(Trans);
			} else {
				tail.next = new TransactionListNode(Trans);
				tail = tail.next;
			}
		}
		
		public int dequeue() {
			int ToBeDeleted = head.info.TransactionType;
			if (head == tail){
				head = tail = null;
			} else {
				head = head.next;
			}
			return ToBeDeleted;
		}
		
		public int ping() {
			return head.info.TransactionType;
		}
		
		public void PrintTransactions() {
			
		}
}
