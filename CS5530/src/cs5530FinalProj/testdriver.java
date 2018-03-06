package cs5530FinalProj;

import java.util.*;

public class testdriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Connector2 con= new Connector2();
			Order order= new Order();
			
			String result=order.getOrders("login", "testLogin", con.stmt);
			System.out.println(result);
			con.closeConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
	}

}
