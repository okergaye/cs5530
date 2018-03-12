package cs5530FinalProj;


import java.lang.*;
import java.sql.*;
import java.io.*;

public class testdriver2 {

	/**
	 * @param args
	 */
	//simple console write lines
	public static void displayMenu()
	{
		System.out.println("        Welcome to UUber System     ");
    	System.out.println("1. Registration:");
    	System.out.println("2. Login:");
    	System.out.println("3. exit:");
    	System.out.println("pleasse enter your choice:");
	}

	public static void displayRegistrationMenu()
	{
		System.out.println("        Registration     ");
		System.out.println("1. Standard User:");
		System.out.println("2. Driver User:");
		System.out.println("3. Go Back:");
    	System.out.println("pleasse enter your choice:");
	}
	
	public static void displayUserMenu()
	{
		System.out.println("        Welcome to UUber System     ");
		System.out.println("1. Reserve a UberCar:");
		System.out.println("2. Favorite UberCar:");
		System.out.println("3. Write Feedback for UberCar:");
		System.out.println("4. View Feedback for UberCar:");
		System.out.println("5. Rate Feedback:");
		System.out.println("6. Set User to Trusted:");
		System.out.println("7. Set User to Not Trusted:");
		System.out.println("8. Logout:");
	}
	
	public static void displayDriverMenu()
	{
		System.out.println("        Welcome to UUber System     ");
		System.out.println("1. Own a UberCar:");
		System.out.println("2. Set Hours of Op:");
		System.out.println("3. Logout:");
	}
	//end of console write line menues
	
	
	//this is main menu option 2
	
	public static void startUser(BufferedReader in, Connector con, Database user) throws IOException
	{
		String choice;
		String username;
		String vin;
		boolean loggedIn = true;
		int c=0;
		while(loggedIn)
		{
			displayUserMenu();
			while ((choice = in.readLine()) == null && choice.length() == 0);
			try
			{
				c = Integer.parseInt(choice);
			}
			catch (Exception e)
			{	 
				continue;
			}
			if (c<1 | c>8)
				continue;
			
			//Switch case for all the options
			switch (c)
			{
			case 1: //Reserve
				
				
				
				break;
				
			case 2: // Favorite
				System.out.println("please enter car vin number:");
				while ((vin = in.readLine()) == null && vin.length() == 0);
				
				user.favoriteCar(vin, user.login, con.stmt);
				
				break;
				
			case 3: // Write Feedback
				break;
				
			case 4: // View Feedback
				break;
				
			case 5: // Rate Feedback
				break;
				
			case 6: //Trust a user
				System.out.println("please enter other username:");
				while ((username = in.readLine()) == null && username.length() == 0);
				
				if (user.userExists(username, con.stmt) == 1)
				{
					user.trustUser(user.login, username, "trusted", con.stmt);
				}
				else
				{
					System.out.println("User does not exists.");
				}
				
				break;
				
			case 7: //Do not trust a user
				System.out.println("please enter other username:");
				while ((username = in.readLine()) == null && username.length() == 0);
				
				if (user.userExists(username, con.stmt) == 1)
				{
					user.trustUser(user.login, username, "not-trusted", con.stmt);
				}
				else
				{
					System.out.println("User does not exists.");
				}
				
				break;
				
			case 8: //Logging out
				user.logout();
				loggedIn = true;
				System.out.println("Logging out.");
				break;
			}
		}
		
		//Switch to main menu
		mainMenu(in, con, user);
	}
	
	// this is actually the reg menu!!! aka main menu option 1
	public static void mainMenu(BufferedReader in, Connector con, Database user) throws IOException
	{
		String choice;
        int c=0;
        String name;
        String address;
        String phone;
        String login;
        String password;
        
		while(true)
		{
			displayMenu();
			while ((choice = in.readLine()) == null && choice.length() == 0);
			try
			{
				c = Integer.parseInt(choice);
			}
			catch (Exception e)
			{	 
				continue;
			}
			if (c<1 | c>3)
				continue;
			if (c==1)
			{
				displayRegistrationMenu();
		    	
				// Check if user registration for standard or driver
				while ((choice = in.readLine()) == null && choice.length() == 0);
				try
				{
					c = Integer.parseInt(choice);
				}
				catch (Exception e)
				{	 
					continue;
				}
				
				// Return to menu
				if (c >= 3)
					continue;
				
				int loginCheck = 0;
				
				System.out.println("please enter your name:");
				while ((name = in.readLine()) == null && name.length() == 0);
				System.out.println("please enter your address:");
				while ((address = in.readLine()) == null && address.length() == 0);
				System.out.println("please enter your phone:");
				while ((phone = in.readLine()) == null && phone.length() == 0);
				System.out.println("Login:");
				while ((login = in.readLine()) == null && login.length() == 0);
				//Verify unique login here
				System.out.println("Password:");
				while ((password = in.readLine()) == null && password.length() == 0);
				
				int created;
				
				if (c == 1) //Register to standard user
				{
					user.createUberUser(login, password, name, address, phone, con.stmt);
				}
				else
				{
					user.createUberDriver(login, password, name, address, phone, con.stmt);
				}
			}
			else if (c==2)
			{
				System.out.println("Login:");
				while ((login = in.readLine()) == null && login.length() == 0);
				System.out.println("Password:");
				while ((password = in.readLine()) == null && password.length() == 0);
				
				user.verifyLogin(login, password, "user", con.stmt);
				
				if (user.loggedIn == true)
				{
					startUser(in, con, user);
					break;
				}
				else
				{
					System.out.println("Login Failed.");
				}
			}
			else
			{
				break;
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Example for cs5530");
		Connector con=null;
		Database data = new Database();
		String choice;
        int c=0;
        try
		{
			//remember to replace the password
			con= new Connector();
			System.out.println ("Database connection established");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			  
			
			
			//this matters
			mainMenu(in, con, data);
			
			System.out.println("EoM");
			con.stmt.close();
			
			
			
			
			
			
			
			//below this is irelivant
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println ("Either connection error or query execution error!");
		}
		finally
		{
			if (con != null)
			{
				try
				{
				con.closeConnection();
				System.out.println ("Database connection terminated");
				}
				catch (Exception e) { /* ignore close errors */ }
			}	 
		}
	}
}
