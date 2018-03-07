package cs5530FinalProj;

import java.sql.*;

public class UberUser 
{
	public UberUser()
	{}
	
	public String getUberUser(String login, String password, Statement stmt)
	{
		String sql="select uuid from UberUser where login = '%"+login+"%' and password = '%"+password+"%'";
		String output="";
		ResultSet rs=null;
	 	System.out.println("executing "+sql);
	 	try
	 	{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 			output += rs.getString("uuid")+"\n";
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
	
	public int createUberUser(String login, String password, String name, String address, String phone, Statement stmt)
	{
		String sql = "insert into UberUser values ('%" + login + "%', '%" + password + "%', '%" + name + "%',"
				+ " '%" + address + "%', '%" + phone + "%')";
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
	
	public int trustUser(String login1, String login2, Statement stmt)
	{
		String sql = ""; // Not sure how to implement trust here
		String output = "";
		ResultSet rs=null;
		try
		{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 			output += "";
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
		//set up trust setup here
		return -1;
	}
}
