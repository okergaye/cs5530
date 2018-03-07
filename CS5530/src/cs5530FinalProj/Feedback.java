package cs5530FinalProj;

import java.sql.*;

public class Feedback {
	public Feedback()
	{}
	
	// Feedback will use login as a forgein key so we know what user created the feedback fid primary key
	public String getFeedback(String login, String fid, Statement stmt)
	{
		String sql="select text from UberUser where login = '%"+login+"%' and fid = '%"+fid+"%'";
		String output="";
		ResultSet rs=null;
	 	System.out.println("executing "+sql);
	 	try
	 	{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 			output += rs.getString("text")+"\n";
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
	
	public int createFeedback(String fid, String login, String text, String date, String score, Statement stmt)
	{
		String sql = "insert into Feedback values ('%" + fid + "%', '%" + login + "%', '%" + text + "%',"
				+ " '%" + date + "%', '%" + score + "%')";
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
			System.out.println("Feedback Creation Successful");
			return 1;
		}
		else
		{
			System.out.println("Feedback Creation Failed");
			return 0;
		} 	
	}
	
	public int rateFeedback(String fid, String login, String score, Statement stmt)
	{
		String sql="select login from Feedback where fid = '%"+fid+"%'";
		String output="";
		ResultSet rs=null;
	 	System.out.println("executing "+sql);
	 	try
	 	{
	 		rs=stmt.executeQuery(sql);
	 		while (rs.next())
	 		{
	 			output = rs.getString("login");
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
	    
	 	if (output.equals(login)) //Check if user is rating their own feedback
	 	{
	 		System.out.println("Unvaliable to rate own feedback");
	 		return 0;
	 	}
	 	else //User rating others feedback
	 	{
	 		//Insert into new table here? or update score?
	 		// update method
	 		sql = "update Feedback set score = '%" + score + "%' where fid = '%" + fid + "%'";
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
				System.out.println("Feedback Rating Successful");
				return 1;
			}
			else
			{
				System.out.println("Feedback Rating Failed");
				return 0;
			}
			
			/* creating new table
			sql = "insert into Ratings values ('%" + fid + "%', '%" + login + "%', '%" + score + "%')";
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
				System.out.println("Feedback Rating Successful");
				return 1;
			}
			else
			{
				System.out.println("Feedback Rating Failed");
				return 0;
			}
			*/
	 	}
	}
}
