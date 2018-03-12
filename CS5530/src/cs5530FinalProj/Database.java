package cs5530FinalProj;

import java.sql.*;

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
	
	
	public int createUberUser(String login, String password, String name, String address, String phone, String type, Statement stmt)
	{
		String sql;
		//Creates the user to be in
		if (type.equals("user"))
		{
			sql = "insert into UU values ('" + login + "', '" + password + "', '" + name + "', '" + address + "', '" + phone + "')";
		}
		else //The user is a driver
		{
			sql = "insert into UD values ('" + login + "', '" + password + "', '" + name + "', '" + address + "', '" + phone + "')";
		}
		
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
		String sql = "select count (*) as count from Users where login = '" + login + "'";
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
		String sql = "insert into Favorites values ('" + vin + "', '" + login + "')";
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
	
	public int trustUser(String login1, String login2, String trust, Statement stmt)
	{
		String sql = "select Count(*) as counter from Trust where login1 = '%" + login1 + "%' and login2 = '%" + login2 + "%'";
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
			sql = "insert into Trust values ('%" + login1 + "%', '%" + login2 + "%', '%" + trust + "%')";
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
				System.out.println("User Trusted Settings Creation Successful");
				return 1;
			}
			else
			{
				System.out.println("User Trusted Settings Creation Failed");
				return 0;
			}
		}
		else //If the user already has a trust setting update the trust settings for users trust to 2nd user
		{
			sql = "update Trust set trust = '%" + trust + "%' where login1 = '%" + login1 + "%' and login2 = '%" + login2 + "%'";
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
				System.out.println("User Trusted Settings Updated Successful");
				return 1;
			}
			else
			{
				System.out.println("User Trusted Settings Updated Failed");
				return 0;
			}
		}
	}
}