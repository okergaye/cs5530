package cs5530FinalProj;

import java.sql.*;

public class UberDriver 
{
	public UberDriver()
	{}
	
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
}
