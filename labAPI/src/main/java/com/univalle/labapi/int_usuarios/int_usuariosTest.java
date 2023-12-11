package com.univalle.labapi.int_usuarios;

import java.io.IOException;
import java.sql.*;

public class int_usuariosTest {
        @SuppressWarnings("empty-statement")
	public static void test() {
            String url = "jdbc:mariadb://localhost/interfacesdb";
	    Connection conn = null;
	    int_usuariosDAOImp intUsuariosDAO;
	    
	    // Connecting to database...
	    try {
	    	//String driver = "com.mysql.jdbc.Driver";
	    	//Class.forName(driver).newInstance();
	    	Class.forName("org.mariadb.jdbc.Driver");
	    	
	    	conn = DriverManager.getConnection(url, "intuser", "1234");
	    }
	    catch (ClassNotFoundException | SQLException e) {
	    	System.out.println("No se  puede cargar el Driver de MYSQL");
	    	e.printStackTrace();
	    	return;
	    }
	    
	    // Main functionalities...
	    try {
	    	// int_usuariosDAOImp instance
	    	intUsuariosDAO = new int_usuariosDAOImp(conn);
	    	
	    	// Getting all current users
	    	System.out.println("Getting all users: ");
	    	for (int_usuarios user : intUsuariosDAO.getAllUsers())
	    	{
	    		System.out.println("User ID: "+user.getId());
	    		System.out.println("Name: "+user.getNombres()+" "+user.getApellidos());
	    		System.out.println("Email: "+user.getEmail());
	    		System.out.println("--------------------");
	    	}
	    	System.out.println("***********************************************");
	    	
	    	// Stopping...
	    	System.out.println("STOP !, Verify the information using PHPMYADMIN, then press '1' and ENTER...");
		 	try { while(!(System.in.read() == '1')); 
		 		/*int myChar = System.in.read();*/ }
		 	catch(IOException e) { }
	    	
	    	// Getting a particular user, ID = 2
	    	int_usuarios userID2 = intUsuariosDAO.getUser(2);
	    	System.out.println("User with ID 2...");
	    	System.out.println("User ID: "+userID2.getId());
    		System.out.println("Name: "+userID2.getNombres()+" "+userID2.getApellidos());
    		System.out.println("Email: "+userID2.getEmail());
    		System.out.println("***********************************************");
    		
    		// Stopping...
    		System.out.println("STOP !, Verify the information using PHPMYADMIN, then press '2' and ENTER...");
		 	try { while(!(System.in.read() == '2')); 
		 		/*int myChar = System.in.read();*/ }
		 	catch(IOException e) { }
    		
    		// Inserting a new user...
    		System.out.println("Inserting a new user ...");
    		int numRows = intUsuariosDAO.insertUser("Test User - DAO", "Last Name DAO", "testdao@univalle.edu.co", "mypass", 2);
    		System.out.println("Rows affected: "+numRows);
    		System.out.println("***********************************************");
    		
    		// Stopping...
    		System.out.println("STOP !, Verify the information using PHPMYADMIN, then press '3' and ENTER...");
		 	try { while(!(System.in.read() == '3')); 
		 		/*int myChar = System.in.read();*/ }
		 	catch(IOException e) { }
    		
    		// Updating a new user...
    		System.out.println("Updating last user inserted ...");
    		int_usuarios userLastInserted = intUsuariosDAO.getUser("Test User - DAO", "Last Name DAO");
    		int_usuarios user2Update = intUsuariosDAO.getUser(userLastInserted.getId());
    		user2Update.setEmail("testDao@gmail.com");
    		user2Update.setClave("4321");
    		numRows = intUsuariosDAO.updateUser(user2Update);
    		System.out.println("Rows affected: "+numRows);
    		int_usuarios userUpdated = intUsuariosDAO.getUser(userLastInserted.getId());
	    	System.out.println("User with ID: "+userLastInserted.getId());
	    	System.out.println("User ID: "+userUpdated.getId());
    		System.out.println("Name: "+userUpdated.getNombres()+" "+userUpdated.getApellidos());
    		System.out.println("Email: "+userUpdated.getEmail());
    		System.out.println("***********************************************");
    		
    		// Stopping...
    		System.out.println("STOP !, Verify the information using PHPMYADMIN, then press '4' and ENTER...");
		 	try { while(!(System.in.read() == '4')); 
		 		/*int myChar = System.in.read();*/ }
		 	catch(IOException e) { }
    		
    		// Deleting user
    		System.out.println("Deleting last user inserted ...");
    		numRows = intUsuariosDAO.deleteUser(userLastInserted.getId());
    		System.out.println("Rows affected: "+numRows);
	    }
	    catch (SQLException e)
	    {
	    	e.printStackTrace();
	    }
	    
	    // Closing...
	    finally
	    {
	    	if (conn!=null)
	    	{
	    		try {conn.close();}
	    		catch (SQLException e) {
                            e.printStackTrace();
                        }
	    	}
	    }
	}
}
