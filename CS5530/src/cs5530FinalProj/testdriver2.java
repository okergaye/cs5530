package cs5530FinalProj;


import java.lang.*;
import java.sql.*;
import java.io.*;

public class testdriver2 {

	/**
	 * @param args
	 */
	public static void displayMenu()
	{
		System.out.println("        Welcome to UUber System     ");
    	System.out.println("1. Registration:");
    	System.out.println("2. Login:");
    	System.out.println("3. enter your own query:");
    	System.out.println("4. exit:");
    	System.out.println("pleasse enter your choice:");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Example for cs5530");
		Connector con=null;
		String choice;
        String name;
        String address;
        String phone;
        String login;
        String password;
        String sql=null;
        int c=0;
        try
		{
			//remember to replace the password
			con= new Connector();
			System.out.println ("Database connection established");
			 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			     
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
				if (c<1 | c>4)
					continue;
				if (c==1)
				{
					System.out.println("        Registration     ");
					System.out.println("1. Standard User:");
					System.out.println("2. Driver User:");
					System.out.println("3. Go Back:");
			    	System.out.println("pleasse enter your choice:");
			    	
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
					
					if (c == 1) //Register to standard user
					{
						UberUser uu = new UberUser();
						System.out.println(uu.createUberUser(login, password, name, address, phone, con.stmt));
					}
					else
					{
						UberDriver ud = new UberDriver();
						System.out.println(ud.createUberDriver(login, password, name, address, phone, con.stmt));
					}
				}
				else if (c==2)
				{
					System.out.println("Login:");
					while ((login = in.readLine()) == null && login.length() == 0);
					System.out.println("Password:");
					while ((password = in.readLine()) == null && password.length() == 0);
					
					//Verify login here
				}
				else if (c==3)
				{	 
					System.out.println("please enter your query below:");
					while ((sql = in.readLine()) == null && sql.length() == 0);
					System.out.println(sql);
					ResultSet rs=con.stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					int numCols = rsmd.getColumnCount();
					while (rs.next())
					{
						 //System.out.print("cname:");
						for (int i=1; i<=numCols;i++)
						System.out.print(rs.getString(i)+"  ");
						System.out.println("");
					}
					System.out.println(" ");
					rs.close();
				}
				else
				{   
					System.out.println("EoM");
					con.stmt.close(); 
					break;
				}
			}
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
