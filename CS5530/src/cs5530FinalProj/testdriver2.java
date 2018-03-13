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

	public static void displayUserTypeMenu()
	{
		System.out.println("        Welcome to UUber System     ");
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
		System.out.println("3. Record a Ride:");
		System.out.println("4. Write Feedback for UberCar:");
		System.out.println("5. View Feedback for UberCar:");
		System.out.println("6. Rate Feedback:");
		System.out.println("7. Set User to Trusted:");
		System.out.println("8. Set User to Not Trusted:");
		System.out.println("9. Browse UC:");
		System.out.println("10. Two Degrees of Seperation:");
		System.out.println("11. Statistics:");
		System.out.println("12. User Awards (admin only):");
		System.out.println("13. Logout:");
		System.out.println("pleasse enter your choice:");
	}
	
	public static void displayDriverMenu()
	{
		System.out.println("        Welcome to UUber System     ");
		System.out.println("1. Add a UberCar:");
		System.out.println("2. Update a UberCar:");
		System.out.println("3. Set Hours of Op:");
		System.out.println("4. Logout:");
		System.out.println("pleasse enter your choice:");
	}
	//end of console write line menues
	
	
	//this is main menu option 2
	
	public static void startUser(BufferedReader in, Connector con, Database user) throws IOException
	{
		String choice, username, vin, feedback, fid, score, from, to, model;
		int time;
		boolean loggedIn = true;
		Feedback fb = new Feedback();
		int c=0;
		
		// Start loop
		while(loggedIn)
		{
			displayUserMenu();
			
			//Find which option to go to
			while ((choice = in.readLine()) == null && choice.length() == 0);
			try
			{
				c = Integer.parseInt(choice);
			}
			catch (Exception e)
			{	 
				continue;
			}
			
			//Check if in bounds
			if (c<1 | c>13)
				continue;
			
			//Switch case for all the options
			switch (c)
			{
			case 1: //Reserve
				System.out.println("please enter car vin number:");
				while ((vin = in.readLine()) == null && vin.length() == 0);
				System.out.println("please enter a time to reserve a car:");
				while ((from = in.readLine()) == null && from.length() == 0);
				
				time = Integer.parseInt(from);
				
				user.reserveCar(user.login, vin, time, con.stmt);

				break;
				
			case 2: // Favorite
				System.out.println("please enter car vin number:");
				while ((vin = in.readLine()) == null && vin.length() == 0);
				
				user.favoriteCar(vin, user.login, con.stmt);
				
				break;
				
			case 3: // Record a Ride
				System.out.println("please enter car vin number:");
				while ((vin = in.readLine()) == null && vin.length() == 0);
				System.out.println("please enter your start time:");
				while ((from = in.readLine()) == null && from.length() == 0);
				System.out.println("please enter car vin number:");
				while ((to = in.readLine()) == null && to.length() == 0);
				
				//user.RecordRide(vin, from, to, con.stmt);
				
				break;
				
			case 4: // Write Feedback
				System.out.println("please enter car vin number:");
				while ((vin = in.readLine()) == null && vin.length() == 0);
				System.out.println("please enter your feedback:");
				while ((feedback = in.readLine()) == null && feedback.length() == 0);
				
				//Feedback creation here
				//fb.createFeedback(feedback, vin, user.login, con.stmt);
				break;
				
			case 5: // View Feedback
				System.out.println("please enter car vin number:");
				while ((vin = in.readLine()) == null && vin.length() == 0);
				
				fb.getFeedback(vin, con.stmt);
				break;
				
			case 6: // Rate Feedback
				System.out.println("please enter fid number to rate feedback:");
				while ((fid = in.readLine()) == null && fid.length() == 0);
				System.out.println("please enter 0 (useless), 1 (useful), or 2 (very useful):");
				while ((score = in.readLine()) == null && score.length() == 0);
				
				fb.rateFeedback(user.login, fid, score, con.stmt);
				break;
				
			case 7: //Trust a user
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
				
			case 8: //Do not trust a user
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
				
			case 9: //Browsing UC
				System.out.println("please enter car model:");
				while ((model = in.readLine()) == null && model.length() == 0);
				System.out.println("Sorting order:");
				System.out.println("(a) average numerical score of feedbacks:");
				System.out.println("(b) average numerical score of trusted users feedbacks:");
				System.out.println("please enter your choice:");
				while ((choice = in.readLine()) == null && choice.length() == 0);
				
				//user.BrowseUC(model, choice, con.stmt);
				
				break;
				
			case 10: //2DoS
				System.out.println("please enter other username:");
				while ((username = in.readLine()) == null && username.length() == 0);
				
				//user.TwoDegreesOfSeperation(user.login, username, con.stmt);
				break;
				
			case 11: //Stats
				System.out.println("Statistics:");
				System.out.println("(a) Most popular UC for each catagory:");
				System.out.println("(b) Most expensive UC for each catagory:");
				System.out.println("(c) Highest Rating UC for each catagory:");
				System.out.println("please enter your choice:");
				while ((choice = in.readLine()) == null && choice.length() == 0);
				
				//user.statShow(choice, con.stmt);
				
				break;
				
			case 12: //User Awards
				System.out.println("User Award:");
				System.out.println("(a) Most trusted user:");
				System.out.println("(b) Most useful user:");
				System.out.println("please enter your choice:");
				while ((choice = in.readLine()) == null && choice.length() == 0);
				
				//user.award(choice, con.stmt);
				
				break;
				
			case 13: //Logging out
				user.logout();
				loggedIn = false;
				System.out.println("Logging out.");
				break;
			}
		}
		
		//Switch to main menu
		mainMenu(in, con, user);
	}
	
	public static void startDriver(BufferedReader in, Connector con, Database user) throws IOException
	{
		String choice, vin, from, to, catagory, make, model, year;
		boolean loggedIn = true;
		Feedback fb = new Feedback();
		int c=0;
		
		//Start of loop
		while(loggedIn)
		{
			displayDriverMenu();
			
			//get choice
			while ((choice = in.readLine()) == null && choice.length() == 0);
			try
			{
				c = Integer.parseInt(choice);
			}
			catch (Exception e)
			{	 
				continue;
			}
			
			// is in bounds
			if (c<1 | c>8)
				continue;
			
			//Switch case for all the options
			switch (c)
			{
			case 1: //Add a car
				System.out.println("please enter car vin number:");
				while ((vin = in.readLine()) == null && vin.length() == 0);
				System.out.println("please enter car catagory:");
				while ((catagory = in.readLine()) == null && catagory.length() == 0);
				System.out.println("please enter car make:");
				while ((make = in.readLine()) == null && make.length() == 0);
				System.out.println("please enter car model:");
				while ((model = in.readLine()) == null && model.length() == 0);
				//System.out.println("please enter car year:");
				//while ((year = in.readLine()) == null && year.length() == 0);
				
				//TODO Own car from this information2
				user.addCar(user.login, vin, catagory, make, model, con.stmt);
				break;
				
			case 2: //Update a car
				System.out.println("please enter car vin number:");
				while ((vin = in.readLine()) == null && vin.length() == 0);
				System.out.println("please enter car catagory:");
				while ((catagory = in.readLine()) == null && catagory.length() == 0);
				System.out.println("please enter car make:");
				while ((make = in.readLine()) == null && make.length() == 0);
				System.out.println("please enter car model:");
				while ((model = in.readLine()) == null && model.length() == 0);
				//System.out.println("please enter car year:");
				//while ((year = in.readLine()) == null && year.length() == 0);
				
				//TODO Own car from this information2
				//user.updateCar(user.login, vin, catagory, make, model, year, con.stmt);
				break;
				
			case 3: //Set hours of operation
				System.out.println("please enter your starting time:");
				while ((from = in.readLine()) == null && from.length() == 0);
				System.out.println("please enter your ending time:");
				while ((to = in.readLine()) == null && to.length() == 0);
				
				//Set the hours of operation for the ud from this information
				
				break;
				
			case 4:
				user.logout();
				loggedIn = false;
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
        boolean active = true;
        
		while(active)
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
			switch(c)
			{
			case 1:
				displayUserTypeMenu();
		    	
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
				if (c > 3 || c < 1)
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
				break;
				
			case 2:
				displayUserTypeMenu();
		    	
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
				if (c > 3 || c < 1)
					continue;
				
				System.out.println("Login:");
				while ((login = in.readLine()) == null && login.length() == 0);
				System.out.println("Password:");
				while ((password = in.readLine()) == null && password.length() == 0);
				
				if (c == 1)
					user.verifyLogin(login, password, "user", con.stmt);
				else
					user.verifyLogin(login, password, "driver", con.stmt);
				
				if (user.loggedIn == true)
				{
					if (c == 1)
						startUser(in, con, user);
					else
						startDriver(in, con, user);
					break;
				}
				else
				{
					System.out.println("Login Failed.");
					break;
				}
				
			case 3:
				active = false;
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
