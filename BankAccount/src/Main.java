/**@author Sean Jeffrey B. Fung, Jose Paolo P. Paredes, Kenneth Allan O. Cuarteros
 **Section: A
 **Date: 02-25-2019
 **Description: This Program is A Banking System with features for the Client and Admin with support of File Reading and Writing
 */
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
public class Main {
	
	/**
	 * method WriteToFile - method for Saving List of Accounts
	 * @param Accountlist list - The list of accounts with a linked list structure.
	 * @return void
	 */
	private static void WriteToFile(AccountList list){
		  try {
		    FileOutputStream fos = new FileOutputStream ("list.txt");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(list);
		    fos.close();
		  } 
		  catch (Exception e) {
		    System.out.println(e);   
		  }
		}
	
	/**
	 * method ReadFromFile - reads or loads the linked list with the accounts saved by the system
	 * @return o_userdata - LinkedList objects containing the Accounts and its data
	 */
	private static AccountList ReadFromFile(){
		  AccountList o_userdata = new AccountList();
		  try {
		    FileInputStream fis = new  FileInputStream("list.txt");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    Object obj = ois.readObject();
		    o_userdata = (AccountList) obj;
		  } 
		  catch (Exception e) {
			System.out.println();
			System.out.println("ERROR! list.txt file not found.");
		    System.out.println("New file will be generated after save. . .");
		    System.out.println();
		    pause();
		  } 
		  return o_userdata;
		}
	
	// Main method of the project
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("CCA BANKING SYSTEM");
		DecimalFormat Billion = new DecimalFormat("##0.####"); // Allow Digits more than 100 million
		int search;
		
		AccountList list;
		//Loads the List File if it exists
		list = ReadFromFile();
		
		boolean loop = false;
		do{
			CallMenu.Login();													//Menu to display log in choice 
	  
			int choice = input.nextInt();
			
			
				switch (choice){
				case 0:
					loop = true;
					break;
				case 1: 
					boolean infFindClient = true;
					boolean foundclient;
					if(list.isEmpty()) {
					System.out.println("No Accounts in System");
					System.out.println();
					}else
					do {

							System.out.println("Please Enter ID of Account ");
							search = input.nextInt();
							foundclient = list.InList(search);
							if(foundclient != true) {
								System.out.println("Entered Account Does Not Exist");
								System.out.println();
							}else {
								System.out.println("Entered Account Found");
								System.out.printf("%-50s %-50s %-50s\n", "Name", "Account Number", "Balance");
								list.PrintInList(search);
								System.out.println();
								pause();
								System.out.println();
								System.out.println("Hello Valued Client " + list.GetName(search));
								boolean clientloop2 = true;
								
								do {
						
									CallMenu.ClientActions();					//Menu to choose a client action
								
									int clientchoice = input.nextInt();
									
									
									//Performs action based on user choice
									switch (clientchoice) {
									case 0:										//Deposit
										float value;
										float deposited;
										boolean infDeposit = true;
										do {
											System.out.println("Please Enter Amount to Deposit ");
											value = input.nextFloat();
												deposited = list.Deposit(search, value);
												System.out.println("Successfully Deposited Amount: " );
												System.out.println(Billion.format(deposited));
												System.out.println();
												infDeposit = false;
										}while(infDeposit);
										WriteToFile(list);
										pause();
										break;
										
									case 1:										//Withdraw
										float fromBal1;
										float value1;
										float withdrawed;
										boolean infWithdraw = true;
										do {
											fromBal1 = list.GetBalance(search);
											System.out.println("Please Enter Amount to Withdraw ");
											value1 = input.nextFloat();
											if(fromBal1 > 0 && value1 <= fromBal1) {
												withdrawed = list.Withdraw(search, value1);
												System.out.println("Successfully Withdrawed Amount: " );
												System.out.println(Billion.format(withdrawed));
												System.out.println();
												infWithdraw = false;
											}else
												System.out.println("Insufficient Balance in Source Account! ");
												System.out.println();
												infWithdraw = false;
										}while(infWithdraw);
										WriteToFile(list);
										pause();
										break;
										
									case 2:										//Fund Transfer
										int ClientFund = search;
										boolean infTransferFunds = true;
										boolean to   = false;
										int toID     = 0;
										float amount;
										float fromBal2;
										do {
												System.out.println("Please Enter ID of Fund Destination Account ");
												search = input.nextInt();
												to = list.InList(search);
												if(to != true) {
													System.out.println("Entered Account Does Not Exist");
													System.out.println();
												}else {
													toID = search;
													fromBal2 = list.GetBalance(ClientFund);
													System.out.println("Please Enter Amount to Transfer ");
													System.out.println();
													amount = input.nextInt();
													if(fromBal2 > 0 && amount <= fromBal2) {
														list.Transfer(ClientFund, toID, amount);
														System.out.println("Fund Transfer Successful! ");
														System.out.println();
													}else
														System.out.println("Insufficient Balance in Source Account! ");
														System.out.println();
												}
										infTransferFunds = false;
										}while (infTransferFunds);
										WriteToFile(list);
										search = ClientFund;
										pause();
										break;
										
									case 3:									//Inquire Balance
										boolean infGetClientBalance = true;
										float ShowBal = 0;
										do {
												ShowBal = list.GetBalance(search);
												System.out.println("Balance of Your Account is "+ Billion.format(ShowBal));
												System.out.println();
												infGetClientBalance = false;
											}while(infGetClientBalance);
										pause();
										break;
									
									case 4:									//Go Back
										clientloop2 = false;
										break;
									default:
										System.out.println("Enter a valid choice.");	
										pause();
									}
								}while(clientloop2);
								 break;
								}
							infFindClient = false;	
						}while(infFindClient);
					System.out.println();
					System.out.println("Thank you for using CCA BANKING SYSTEM");
					System.out.println();
						break;
					
	
				case 2: System.out.println("Hello Admin!");
				
					boolean loop2 = false;
					do{
						
						CallMenu.AdminActions();															//Menu to choose an admin action
						
						int adminchoice = input.nextInt();
						
						
						//Performs action based on user choice
						switch (adminchoice) {
							case 0: 										//Open Account
								System.out.println("Please Fill Up Account information. . .");
								System.out.println("Enter First Name - Middle Initial. - Last Name:");
								String addname;
								input.nextLine();
								addname = input.nextLine();
									System.out.println("Account Number:");
									int addaccnum = input.nextInt();
									if(list.InList(addaccnum)) {
										System.out.println("Chosen ID is Already in List");
										System.out.println();
										pause();
										break;
									}
									System.out.println("Balance:");
									float addbal = input.nextFloat();
									if(addbal < 0) {
										System.out.println("Cannot Add Negative Balance!");
										System.out.println();
										pause();
										break;
									}
								Account newacc = new Account(addname, addaccnum, addbal);
								list.AddAccount(newacc);
								System.out.println();
								System.out.println("Account Opened Successfully!");
								WriteToFile(list);
								pause();
								break;
								
							case 1: 										//Close Account
								boolean infDeleteAccount = true;
								boolean DelExist = false;
								Account result;
								if(list.isEmpty()) {
									System.out.println("List Is Empty!");
									System.out.println();
								}else
								do {
									System.out.println("Please Enter ID of Account to Delete/Close");
									System.out.println();
									search = input.nextInt();
									DelExist = list.InList(search);
									if(!DelExist) {
										System.out.println("Entered Account Does Not Exist");
										System.out.println();
									}else {
										result = list.DeleteAccount(search);
										System.out.println("Account of "+ result.Name + " has been closed" );
										System.out.println();
									}
									infDeleteAccount = false;
								}while(infDeleteAccount);
								WriteToFile(list);
								pause();
								break;
								
							case 2: 										//Transfer Funds from one account to another
								boolean infTransferFunds = true;
								boolean from = false;
								int fromID   = 0;
								boolean to   = false;
								int toID     = 0;
								float amount;
								float fromBal;
								do {
									System.out.println("Please Enter ID of Fund Source Account ");
									search = input.nextInt();
									from = list.InList(search);
									if(from != true) {
										System.out.println("Entered Account Does Not Exist");
										System.out.println();
									}else {
										fromID = search;
										System.out.println("Please Enter ID of Fund Destination Account ");
										search = input.nextInt();
										to = list.InList(search);
										if(to != true) {
											System.out.println("Entered Account Does Not Exist");
											System.out.println();
										}else {
											toID = search;
											fromBal = list.GetBalance(fromID);
											System.out.println("Please Enter Amount to Transfer ");
											System.out.println();
											amount = input.nextInt();
											if(fromBal > 0 && amount <= fromBal) {
												list.Transfer(fromID, toID, amount);
												System.out.println("Fund Transfer Successful! ");
												System.out.println();
											}else
												System.out.println("Insufficient Balance in Source Account! ");
												System.out.println();
										}
									}
								infTransferFunds = false;
								}while (infTransferFunds);
								WriteToFile(list);
								pause();
								break;
							case 3:										//Open Sort and Print List Options
								boolean loop3 = false;
								do{
								if(list.isEmpty()) {
									System.out.println("There are Currently No Accounts in the System");
									pause();
									break;
								}else
									
																								//Menu to display choices the desired action for sorting/
																								//displaying list of accounts currently in the system
									CallMenu.SortMenu();
									int sortchoice = input.nextInt();
									switch (sortchoice) {
										case 0:										//Show all accounts
											System.out.println("Accounts in System:");
											System.out.println();
											list.PrintAll();
											System.out.println();
											pause();
											break;
											
										case 1:										//Bubble Sort By Name
											Account [] SearchAccountArray0 = new Account [list.GetListSize()];
											System.out.println("Accounts Ordered By Name:");
											System.out.println();
											SearchAccountArray0	= list.CreateNameSortArray();
											System.out.printf("%-50s %-50s %-50s\n", "Name", "Account Number", "Balance");
											for(int i = 0 ; i != list.GetListSize(); i++) {
												list.PrintInList(SearchAccountArray0[i].AccNum);
											}
											System.out.println();
											pause();
											break;
											
										case 2:										//Insertion Sort by Balance (Low to High)
											Account [] SearchAccountArray = new Account [list.GetListSize()];
											System.out.println("Accounts Ordered By Balance (Low to High):");
											System.out.println();
											SearchAccountArray	= list.CreateBalanceSortArray();
											System.out.printf("%-50s %-50s %-50s\n", "Name", "Account Number", "Balance");
											for(int i = 0 ; i != list.GetListSize(); i++) {
												list.PrintInList(SearchAccountArray[i].AccNum);
											}
											System.out.println();
											pause();
											break;
											
										case 3:										//Reverse of Low to High Insertion Sort
											Account [] SearchAccountArray2 = new Account [list.GetListSize()];
											System.out.println("Accounts Ordered By Balance (High to Low):");
											System.out.println();
											SearchAccountArray2	= list.CreateBalanceSortArray();
											System.out.printf("%-50s %-50s %-50s\n", "Name", "Account Number", "Balance");
											for(int i = ((list.GetListSize())-1) ; i != -1 ; i--) {
												list.PrintInList(SearchAccountArray2[i].AccNum);
											}
											System.out.println();
											pause();
											break;
											
										case 4:										//Insertion Sort by ID
											int [] SearchIDArray = new int [list.GetListSize()];
											System.out.println("Accounts Ordered By ID:");
											System.out.println();
											SearchIDArray	= list.CreateIntArray();
											System.out.printf("%-50s %-50s %-50s\n", "Name", "Account Number", "Balance");
											for(int i = 0 ; i != list.GetListSize(); i++) {
												list.PrintInList(SearchIDArray[i]);
											}
											System.out.println();
											pause();
											break;
											
										case 5:										//Get Size of List
											System.out.println();
											System.out.println("The number of registered accounts is: ");
											System.out.println(list.GetListSize());
											System.out.println();
											pause();
											break;
											
										case 6:										//Go Back
											loop3 = true;
											break;
											
										default:
											System.out.println("Enter a valid choice.");	
											pause(); 
											
									}
								}while(!loop3);
								break;
								
							case 4: 										//Search for Account using Linear Search
								boolean infFindAccount = true;
								boolean find;
								do {
									System.out.println("Please Enter ID of Account ");
									System.out.println();
									search = input.nextInt();
									find = list.InList(search);
									if(find != true) {
										System.out.println("Entered Account Does Not Exist");
										System.out.println();
									}else {
										System.out.println("Entered Account Found");
										list.PrintInList(search);
										System.out.println();
									}
								infFindAccount = false;	
								}while(infFindAccount);
									pause();
									break;
									
							case 5:										//Get Balance of Account
								boolean infGetBalance = true;
								boolean searchacc = false;
								float ShowBal = 0;
								do {
									System.out.println("Please Enter ID of Account to Display Balance");
									search = input.nextInt();
									searchacc = list.InList(search);
									if(!searchacc) {
										System.out.println("Account Does Not Exist!");
										System.out.println();
									} else 
										ShowBal = list.GetBalance(search);
										System.out.println("Balance of Account "+ search + " is "+ ShowBal);
										System.out.println();
										infGetBalance = false;
									}while(infGetBalance);
								pause();
								break;
								
							case 6:										//Linear Search for Account
								boolean infGetName = true;
								boolean searchname = false;
								do {
									System.out.println("Please Enter ID of Account to Find Name");
									search = input.nextInt();
									searchname = list.InList(search);
									if(searchname) {
										System.out.println("Name of Account Number "+ search + " is " + list.GetName(search));
										System.out.println();
									}else
										System.out.println("Account Does Not Exist!");
										System.out.println();
										infGetName = false;
								}while (infGetName);
								pause();
								break;
							case 7:										//Save (Write to Data File)
							    WriteToFile(list);
							    System.out.println("Data Has Been Saved Manually!");
							    System.out.println();
								break;
								
							case 8:										//Go Back
								loop2 = true;
								break;
								
							default:
								System.out.println("Enter a valid choice.");	
								pause();
						}
					}while (loop2 != true);
					break;
					
				default:
					System.out.println("Enter a valid choice.");
					pause();
				}
	
		} while (loop !=true);
			
System.out.println("Thank you for using CCA BANKING SYSTEM");
	
	}

	/**
	 * method pause - this enables the program to stop and wait for the user's cue to continue the program
	 */
	public static void pause(){
		Scanner pause = new Scanner(System.in);
		System.out.print("Press enter to continue . . .");
		String c = pause.nextLine();
	}

}	