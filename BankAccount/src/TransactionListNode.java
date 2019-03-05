//Node for Transaction List
import java.io.Serializable;
public class TransactionListNode implements Serializable{
	public Transaction info;
	public TransactionListNode next;
	
	public TransactionListNode(Transaction t) {
		this(t,null);
	    }
		public TransactionListNode(Transaction t, TransactionListNode tln) {
		info = t; next = tln;
		}
}
