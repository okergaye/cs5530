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
	
	public int trustUser(String login1, String login2, String trust, Statement stmt)
	{
		String sql = "select Count(*) as counter from TrustUsers where login1 = '%" + login1 + "%' and login2 = '%" + login2 + "%'";
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
			sql = "insert into TrustedUsers values ('%" + login1 + "%', '%" + login2 + "%', '%" + trust + "%',)"; // Not sure how to implement trust here
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
			sql = "update TrustedUsers set trust = '%" + trust + "%' where login1 = '%" + login1 + "%' and login2 = '%" + login2 + "%'";
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
