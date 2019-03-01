//Account class
import java.io.Serializable;
public class Account implements Serializable{

	String Name;
	int AccNum;
	float Balance;
	
	public Account (String n, int an, float b){
		
		Name = n;
		AccNum = an;
		Balance = b;
		
	}
}
