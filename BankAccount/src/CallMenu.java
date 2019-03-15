
public class CallMenu {

	/*
	 *Method Login outputs the login options for the user
	 */
	public static void Login () {
		System.out.println();
		System.out.println("Welcome! Please choose an option. . .");
		System.out.println();
		System.out.println("0 - Close Program");
		System.out.println("1 - Log in as Client");
        System.out.println("2 - Log in as Admin");
	}
	
	/*
	 *Method ClientActions outputs the client's activity options
	 */
	public static void ClientActions() {
		
		System.out.println();
		System.out.println("0 - Deposit");
		System.out.println("1 - Withdraw");
		System.out.println("2 - Transfer Funds to Another Account");
		System.out.println("3 - Inquire Balance");
		System.out.println("4 - Back");
	}
	
	/*
	 *Method AdminActions outputs the choices for the admin
	 */
	public static void AdminActions() {
		System.out.println();
		System.out.println("0 - Open Account");
		System.out.println("1 - Close Account");
		System.out.println("2 - Transfer Funds");
		System.out.println("3 - Show List of Accounts");
		System.out.println("4 - Search for an Account");
		System.out.println("5 - Show Balance of Account");
		System.out.println("6 - Show Name of Account Owner");
		System.out.println("7 - Save Updates");
		System.out.println("8 - Back");
	}
	
	/*
	 *Method SortMenu outputs the different sorting options and activities of the List of accounts for the admin
	 */
	public static void SortMenu() {

		System.out.println("0 - Show List of Accounts");
		System.out.println("1 - Sort By Name (A-Z)");
		System.out.println("2 - Sort By Balance (Low to High)");
		System.out.println("3 - Sort By Balance (High to Low)");
		System.out.println("4 - Sort By ID");
		System.out.println("5 - Get the Current Size of the List");
		System.out.println("6 - Back");
	}
}
