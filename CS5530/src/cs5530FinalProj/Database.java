package cs5530FinalProj;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;





public class Database 
{
	String login;
	String password;
	String name;
	String address;
	String phone;
	boolean driver = false;
	boolean loggedIn = false;
	
	public Database()
	{}
	
	// problem 9 begin
		public int userBrowseUC(String login, String catagory, String address, String model, String choice, Statement s) {

			//Make things and normalize
			StringBuilder switchString = new StringBuilder("");
			choice.toLowerCase();

			// if choice is not valid quit life
			if (!choice.equals("a") || !choice.equals("b")) {
				return 0;
			}
			String cat = "", add = "", mod ="";
			
			//discover which combination is selected
			if (catagory.length() != 0) {
	            switchString.append("c");
	            cat = "category = '" + catagory + "'";
			}
			if (address.length() != 0) {
				switchString.append("a");
				add = "address = '" + address + "'";

			}
			if (model.length() != 0) {
				switchString.append("m");
				mod = "model = '" + model + "'";

			}


			String sql = "Select * from UC,UD  WHERE UC.login = UD.login and " + cat + add + mod + "";

			// get maybe available pid, this will catch exceptions
			ResultSet rs = null;
			try {
				rs = s.executeQuery(sql);
				while (rs.next()) {
					String count = rs.getString("C");
				}
				rs.close();
			} catch (Exception e) {
				System.out.println("cannot execute the query");
			} finally {
				try {
					if (rs != null && !rs.isClosed())
						rs.close();
				} catch (Exception e) {
					System.out.println("cannot close resultset");
				}
			}
			
			
			
			
			//there might be a better way todo what is below by trying the above
			
			//Switch to decide what factors are to be considerd
			switch (switchString.toString()) {
			case "":

				break;
			case "c":
					if (choice.equals("a")) {
						
					}else {
						
						
					}

				break;
			case "a":
				if (choice.equals("a")) {
					
				}else {
					
					
				}
				break;
			case "m":
				if (choice.equals("a")) {
					
				}else {
					
					
				}
				break;
			case "ca":
				if (choice.equals("a")) {
					
				}else {
					
					
				}
				break;
			case "cm":
				if (choice.equals("a")) {
					
				}else {
					
					
				}
				break;
			case "am":
				if (choice.equals("a")) {
					
				}else {
					
					
				}
				break;
			case "cam":
				if (choice.equals("a")) {
					
				}else {
					
					
				}
				break;
			default:
				break;
			}

			return 1;
		}
		// problem 9 end

	
	

	
	
	
	
	
	
	//problem 4 area
	public String getRide(String login, String vin, String fromHour, String toHour, Statement s){
		Calendar cal = new GregorianCalendar();
    	Date date = new Date(cal.getTimeInMillis());
		int cost = Integer.parseInt(toHour) - Integer.parseInt(fromHour);
		String values = null;
		String count = null;
		//check if that string will work
		String sql = "Select count(*) as C from Period P WHERE P.pid = "
				+ "(SELECT A.pid from Available A where login = "
				+ "(SELECT UC.login from UC where UC.vin = '" + vin + "')) "
				+ "and P.fromHour < '" + fromHour + "' and P.toHour > '" + toHour + "'";

		// get maybe available pid, this will catch exceptions
		ResultSet rs=null;
		try
		{
			rs=s.executeQuery(sql);
			while (rs.next())
			{
				count = rs.getString("C");
			}
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		} 
		
		if(Integer.parseInt(count) != 0) {
			
			values = "VALUES ('" + 0 + "','" + cost + "', '" + date + "', '" + login + "',  '" + vin + "', '" + fromHour + "','" + toHour + "' ) ";
		}
		
		
		
		return values;
	}
	public int insertRide(String values, Statement s){

		String sql = "INSERT INTO Ride "
				+ values;
	
		//	INSERT INTO UC
	    //  VALUES ( 001, "sedan" , 'notReal')
		
		int output = -1;
		try
		{
			output = s.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
			System.out.println(e.getMessage());
		}

		if (output > 0)
		{
			System.out.println("Ride Added");
			return 1;
		}
		else
		{
			System.out.println("Something went wrong, could be anything?");
			return 0;
		} 
	}
	//problem 4 area end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//for 3
	public int addCar(String login, String vin, String cat, String make, String model, Statement s ) {

	
		//Get the user info and make sure there is only 1
		
		String sql = "INSERT INTO UC "
				+ "VALUES ('" + vin + "', '" + cat + "', '" + login + "',  '" + make + "', '" + model + "' ) ";
	
		//	INSERT INTO UC
	    //  VALUES ( 001, "sedan" , 'notReal')
		
		int output = -1;
		try
		{
			output = s.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
			System.out.println(e.getMessage());
		}

		if (output > 0)
		{
			System.out.println("Car Added");
			return 1;
		}
		else
		{
			System.out.println("Something went wrong, are you a legitimate User?");
			return 0;
		} 
		
		
		
	}
	
	//this should ask the user for a vin, and then return t
	public int modCar(String login, String vin, String cat, String make, String model, Statement s ) {
		
		String sql = "UPDATE UC "
				+ "SET category = '" + cat + "', make = '" + make + "', model = '" + model + "' "
						+ "WHERE vin = '" + vin + "' ";
	
	//	UPDATE Customers
	//	SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
	//	WHERE CustomerID = 1;
		
		int output = -1;
		try
		{
			output = s.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
			System.out.println(e.getMessage());
		}

		if (output > 0)
		{
			System.out.println("Car Updated");
			return 1;
		}
		else
		{
			System.out.println("Something went wrong, did you input the correct vin?");
			return 0;
		} 
		
	}
	//3 end
	
	
	
	
	////////taken from UBERCAR and UBER DRIVER
	public String getUberDriver(String login, String UUID, Statement stmt)
	{
		String sql="";
		String output="";
		ResultSet rs=null;
	 	System.out.println("executing "+sql);
	 	try
	 	{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 		}
	     
	 		rs.close();
	 	}
	 	catch(Exception e)
	 	{
	 		System.out.println("cannot execute the query");
	 	}
	 	finally
	 	{
	 		try
	 		{
		 		if (rs!=null && !rs.isClosed())
		 			rs.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("cannot close resultset");
	 		}
	 	}
	    return output;
	}

	public int createUberDriver(String login, String password, String name, String address, String phone, Statement stmt)
	{
		String sql = "insert into UD values ('" + login + "', '" + password + "', '" + name + "', '" + address + "', '" + phone + "')";
		int output = -1;
		try
		{
			output = stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
			System.out.println(e.getMessage());
		}

		if (output > 0)
		{
			System.out.println("UberDriver Creation Successful");
			return 1;
		}
		else
		{
			System.out.println("UberDriver Creation Failed");
			return 0;
		} 	
	}

	/////////
	
	
	
	
	//this is for problem 2
    public ArrayList<Triple> reserveCar(String login, int reserveHours, Statement stmt){
			
    		
    		ArrayList<Triple> list = new ArrayList<Triple>();
    	

			String vin, pid, cost;
			int resHour = reserveHours;
				
			String sql = "select vin, A.pid, toHour - fromHour as Cost from Period P,Available A,UC C where "
					+ "P.pid = A.pid and A.login = C.login and fromHour < '" + resHour + "' and toHour > '" + resHour + "'";

			// get maybe available pid, this will catch exceptions
			ResultSet rs=null;
			try
			{
				rs=stmt.executeQuery(sql);
				while (rs.next())
				{
					vin = rs.getString("vin");
					pid = rs.getString("pid");
					cost = rs.getString("cost");
					list.add(new Triple(vin, pid, cost, resHour));
				}

				rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot execute the query");
			}
			finally
			{
				try{
					if (rs!=null && !rs.isClosed())
						rs.close();
				}
				catch(Exception e)
				{
					System.out.println("cannot close resultset");
				}
			} 
			// now we should have an accepted pid
			return list;
			
		}
	
    public int reserveCarInsert(String login, String vin, String pid, String cost, int time,  Statement s) {
    	
    	
    	Calendar date = new GregorianCalendar();
    	Date test1 = new Date(date.getTimeInMillis() + time);
    	// reset hour, minutes, seconds and millis
    
    		System.out.println(date.get(0));
    		//System.out.println("here");
    		
    		String sql = "insert into Reserve values ('" + login + "', '" + vin + "', '" + pid + "',  '" + cost + "', '" + test1 + "')";
    	
    		//	INSERT INTO UC
    	    //  VALUES ( 001, "sedan" , 'notReal')
    		
    		int output = -1;
    		try
    		{
    			output = s.executeUpdate(sql);
    		}
    		catch(Exception e)
    		{
    			System.out.println("cannot execute the query");
    			System.out.println(e.getMessage());
    		}

    		if (output > 0)
    		{
    			System.out.println("Car Added");
    			return 1;
    		}
    		else
    		{
    			System.out.println("Something went wrong, are you a legitimate User?");
    			return 0;
    		} 
		
    	
		
		
		
    }
    
    
    
	public int createUberUser(String login, String password, String name, String address, String phone, Statement stmt)
	{
		String sql;
		sql = "insert into UU values ('" + login + "', '" + password + "', '" + name + "', '" + address + "', '" + phone + "')";
		
		int output = -1;
		try
		{
			output = stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
			System.out.println(e.getMessage());
		}

		if (output > 0)
		{
			System.out.println("UberUser Creation Successful");
			return 1;
		}
		else
		{
			System.out.println("UberUser Creation Failed");
			return 0;
		} 	
	}
	
	public void verifyLogin(String login, String password, String type, Statement stmt)
	{
		String sql;
		// Get the user info and make sure there is only 1
		if (type.equals("user"))
		{
			sql = "select *, count(*) as count from UU where login = '" + login + "' and password = '" + password + "'";
		}
		else //The user is a driver
		{
			sql = "select *, count(*) as count from UD where login = '" + login + "' and password = '" + password + "'";
		}
		String count = "";
		String localLogin = "";
		String localPassword = "";
		String localName = "";
		String localAddress = "";
		String localPhone = "";
		ResultSet rs=null;
		try
		{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				count = rs.getString("count");
				localLogin = rs.getString("login");
				localPassword = rs.getString("password");
				localName = rs.getString("name");
				localAddress = rs.getString("address");
				localPhone = rs.getString("phone");
			}

			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		
		int c = Integer.parseInt(count);
		//Setting values to current user until logout so it is easier on menu.
		if (c == 1)
		{
			this.login = localLogin;
			this.password = localPassword;
			this.name = localName;
			this.address = localAddress;
			this.phone = localPhone;
			this.loggedIn = true;
			
			if (type.equals("driver"))
			{
				this.driver = true;
			}
		}
	}
	
	public int userExists(String login, Statement stmt)
	{
		String sql = "select count(*) as count from UU where login = '" + login + "'";
		String output = "";
		ResultSet rs = null;
		try
		{
			rs=stmt.executeQuery(sql);
			while (rs.next())
			{
				output = rs.getString("count");
			}

			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
		}
		finally
		{
			try{
				if (rs!=null && !rs.isClosed())
					rs.close();
			}
			catch(Exception e)
			{
				System.out.println("cannot close resultset");
			}
		}
		return Integer.parseInt(output);
	}
	
	//Reset values
	public void logout()
	{
		this.login = null;
		this.password = null;
		this.name = null;
		this.address = null;
		this.phone = null;
		this.driver = false;
		this.loggedIn = false;
	}
	
	public int favoriteCar(String vin, String login, Statement stmt)
	{	
		Calendar cal = new GregorianCalendar();
	Date date = new Date(cal.getTimeInMillis());
		
		String sql = "insert into Favorites values ('" + vin + "', '" + login + "', '" + date + "')";
		int output = -1;
		try{
			output = stmt.executeUpdate(sql);
		}
		catch(Exception e)
		{
			System.out.println("cannot execute the query");
			System.out.println(e.getMessage());
		}

		if (output > 0)
		{
			System.out.println("Car Favorited Successful");
			return 1;
		}
		else
		{
			System.out.println("Car Favorited Failed");
			return 0;
		} 	
	}
	
	public int trustUser(String login1, String login2, int trust, Statement stmt)
	{
		String sql = "select Count(*) as count from Trust where login1 = '" + login1 + "' and login2 = '" + login2 + "'";
		String output = "";
		ResultSet rs=null;
	 	System.out.println("executing "+sql);
	 	try
	 	{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 			output = rs.getString("count");
	 		}
	     
	 		rs.close();
	 	}
	 	catch(Exception e)
	 	{
	 		System.out.println("cannot execute the query");
	 	}
	 	finally
	 	{
	 		try
	 		{
		 		if (rs!=null && !rs.isClosed())
		 			rs.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("cannot close resultset");
	 		}
	 	}
		
		if (output.equals("0")) //If the user did not trust the 2nd user yet insert it into trustUsers
		{
			
			sql = "insert into Trust values ('" + login1 + "', '" + login2 + "', '" + trust + "')";
			int output2 = -1;
			try
			{
				output2 = stmt.executeUpdate(sql);
			}
			catch(Exception e)
			{
				System.out.println("cannot execute the query");
				System.out.println(e.getMessage());
			}

			if (output2 > 0)
			{
				System.out.println("User Trust Set Successfully");
				return 1;
			}
			else
			{
				System.out.println("User Trust Set FAILED!!!!");
				return 0;
			}
		}
		else //If the user already has a trust setting update the trust settings for users trust to 2nd user
		{
			sql = "update Trust set isTrusted = '" + trust + "' where login1 = '" + login1 + "' and login2 = '" + login2 + "'";
			int output2 = -1;
			try
			{
				output2 = stmt.executeUpdate(sql);
			}
			catch(Exception e)
			{
				System.out.println("cannot execute the query");
				System.out.println(e.getMessage());
			}

			if (output2 > 0)
			{
				System.out.println("User Trust Update Successfully");
				return 1;
			}
			else
			{
				System.out.println("User Trust Update FAILED!!!!");
				return 0;
			}
		}
	}
	
	public int degreesOfSeperation(String username1, String username2, Statement stmt)
	{
		//Determine if degree is one
		String sql = "select temp.login from Favorites f, (select * from Favorites) as temp where f.vin = temp.vin and f.login != temp.login and f.login = '" + username1 + "'";
		String output = "";
		ResultSet rs=null;
	 	System.out.println("executing "+sql);
	 	try
	 	{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 			output += rs.getString("login");
	 		}
	     
	 		rs.close();
	 	}
	 	catch(Exception e)
	 	{
	 		System.out.println("cannot execute the query");
	 	}
	 	finally
	 	{
	 		try
	 		{
		 		if (rs!=null && !rs.isClosed())
		 			rs.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("cannot close resultset");
	 		}
	 	}
	 	
		return 0;
	}
	
	public String userAward(String choice, String limit, Statement stmt)
	{
		String c = choice.toLowerCase();
		String output = "";
		switch(c)
		{
			case "a": //Trustful Users
				output = trustfulUsers(limit, stmt);
				break;
				
			case "b": //Useful Users
				output = usefulUsers(limit, stmt);
				break;
		}
		return output;
	}
	
	public String trustfulUsers(String limit, Statement stmt)
	{
		String sql = "select login2, (select Count(*) as total from Trust), (select Count(*) as count from Trust where isTrusted = 0), total - count as trust from Trust group by login2 order by trust asc limit " + limit + "";
		String output = "";
		ResultSet rs=null;
	 	System.out.println("executing "+sql);
	 	try
	 	{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 			output += rs.getString("login2") + "\n";
	 		}
	     
	 		rs.close();
	 	}
	 	catch(Exception e)
	 	{
	 		System.out.println("cannot execute the query");
	 	}
	 	finally
	 	{
	 		try
	 		{
		 		if (rs!=null && !rs.isClosed())
		 			rs.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("cannot close resultset");
	 		}
	 	}
	 	
	 	return output;
	}
	
	public String usefulUsers(String limit, Statement stmt)
	{
		String sql = "select f.login, avg(r.rating) as avgRating from Feedback f, Rates r where f.fid = r.fid group by f.login order by avgRating asc limit " + limit + "";
		String output = "";
		ResultSet rs=null;
	 	System.out.println("executing "+sql);
	 	try
	 	{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 			output += rs.getString("login") + "\n";
	 		}
	     
	 		rs.close();
	 	}
	 	catch(Exception e)
	 	{
	 		System.out.println("cannot execute the query");
	 	}
	 	finally
	 	{
	 		try
	 		{
		 		if (rs!=null && !rs.isClosed())
		 			rs.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("cannot close resultset");
	 		}
	 	}
	 	
	 	return output;
	}
}
