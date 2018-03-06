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
		String sql = "insert into UberUser values ('%" + login + "%', '%" + name + "%', '%" + password + "%',"
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
}
