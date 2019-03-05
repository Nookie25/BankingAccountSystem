import java.io.Serializable;
public class Transaction implements Serializable {

		int TransactionType;
		int Order = 0;
		
		public Transaction (int Action, int Ord) {
			
			TransactionType = Action;
			Order = Ord;
		}
}
