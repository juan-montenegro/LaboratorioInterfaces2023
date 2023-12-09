package com.univalle.guiInterfacesLab2023.model;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.*;

/**
 * The int_usuariosDAOImp class implements the int_usuariosDAO interface in order to implement
 * all the methods to handle the int_usuarios table. 
 *  
 * @author Bladimir Bacca Cortes (bladimir.bacca@correounivalle.edu.co)
 * @version $Revision: 1.0 $
 *
 */
public class int_usuariosDAOImp implements int_usuariosDAO {
	
	/**
	 * List of all users in the int_usuarios table.
	 */
	private List<int_usuarios> Users;
	/**
	 * dbConn is an instance of the database connection.
	 */
	private Connection dbConn = null; 	
	
	/**
	 * Constructor of int_usuariosDAOImp class
	 * 
	 * @param dbConn Database connection.
	 */
	int_usuariosDAOImp (Connection dbConn) throws SQLException {
		this.Users = new ArrayList<>();
		this.dbConn = dbConn;
		
		try
		{
			Statement stm = this.dbConn.createStatement();
			ResultSet rs = stm.executeQuery("select id, nombres, apellidos, email, clave, int_usuarios_tipo_id from int_usuarios");
			while (rs.next())
			{
				int_usuarios intUser = new int_usuarios(rs.getString("nombres"), rs.getString("apellidos"), rs.getInt("int_usuarios_tipo_id"));
				intUser.setClave(rs.getString("clave"));
				intUser.setEmail(rs.getString("email"));
				
				this.Users.add(intUser);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
                        throw e;
		}
	}
	
	/**
	 * This method gets an instance of int_usuarios class corresponding to a particular registry of the 
	 * int_usuarios table.
	 * 
	 * @param idUser Id of the user to get from the int_usuarios table.
         * @return 
	 */
        @Override
	public int_usuarios getUser(int idUser) {
		int_usuarios resUser = null;
		
		try
		{
			Statement stm = this.dbConn.createStatement();
			ResultSet rs = stm.executeQuery("select id, nombres, apellidos, email, clave, int_usuarios_tipo_id from int_usuarios where id="+idUser);
			while(rs.next())
			{
				resUser = new int_usuarios(rs.getString("nombres"), rs.getString("apellidos"), rs.getInt("int_usuarios_tipo_id"));
				resUser.setClave(rs.getString("clave"));
				resUser.setEmail(rs.getString("email"));
				resUser.setId(rs.getInt("id"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resUser;
	}
	
	/**
	 * This method gets an instance of int_usuarios class corresponding to a particular registry of the 
	 * int_usuarios table using the names and last names.
	 * 
	 * @param names String with the names of the user to get from the int_usuarios table.
	 * @param lastNames String with the last names of the user to get from the int_usuarios table.
     * @return 
	 */
        @Override
	public int_usuarios getUser(String names, String lastNames)
	{
		int_usuarios resUser = null;
		
		try
		{
			Statement stm = this.dbConn.createStatement();
			ResultSet rs = stm.executeQuery("select id, nombres, apellidos, email, clave, int_usuarios_tipo_id from int_usuarios where nombres='"+names+"' AND apellidos='"+lastNames+"'");
			while(rs.next())
			{
				resUser = new int_usuarios(rs.getString("nombres"), rs.getString("apellidos"), rs.getInt("int_usuarios_tipo_id"));
				resUser.setClave(rs.getString("clave"));
				resUser.setEmail(rs.getString("email"));
				resUser.setId(rs.getInt("id"));
				break;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return resUser;
	}
	
	/**
	 * This method gets an updated list of all users saved on the int_usuarios table.
	 * 
	 * @return List object with all users of int_usuarios table.
	 */
        @Override
	public List<int_usuarios> getAllUsers()
	{
		this.Users.clear();
		
		try
		{
			Statement stm = this.dbConn.createStatement();
			ResultSet rs = stm.executeQuery("select id, nombres, apellidos, email, clave, int_usuarios_tipo_id from int_usuarios");
			while (rs.next())
			{
				int_usuarios intUser = new int_usuarios(rs.getString("nombres"), rs.getString("apellidos"), rs.getInt("int_usuarios_tipo_id"));
				intUser.setClave(rs.getString("clave"));
				intUser.setEmail(rs.getString("email"));
				intUser.setId(rs.getInt("id"));
				
				this.Users.add(intUser);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return this.Users;
	}
	
	/**
	 * This method inserts a new user into the int_usuarios table.
	 * 
	 * @param name String object with the user name.
	 * @param apellidos String object with the user last name.
	 * @param email String object with the email of the user.
	 * @param pwd String object with the password of the user.
	 * @param int_usuarios_tipo_id Integer value corresponding to the user profile in the int_usuarios_tipo table.
         * @return 
	 */
        @Override
	public int insertUser(String name, String apellidos, String email, String pwd, int int_usuarios_tipo_id)
	{
		int resRows = 0;
		
		try
		{
			Statement stm = this.dbConn.createStatement();
			resRows = stm.executeUpdate("insert into int_usuarios set nombres='"+name+"', apellidos='"+apellidos+"', email='"+email+"', clave=PASSWORD('"+pwd+"'), int_usuarios_tipo_id="+int_usuarios_tipo_id);
			
			int_usuarios newUser = new int_usuarios(name, apellidos, int_usuarios_tipo_id);
			this.Users.add(newUser);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return resRows;
	}
	
	/**
	 * This method updates a registry of int_usuarios table using an instance of int_usuarios class.
	 * 
	 * @param user2Update int_usuarios object where all information of the user is saved.
         * @return 
	 */
        @Override
	public int updateUser(int_usuarios user2Update)
	{
		int resRows = 0;
		
		try
		{
			Statement stm = this.dbConn.createStatement();
			resRows = stm.executeUpdate("update int_usuarios set nombres='"+user2Update.getNombres()+"', apellidos='"+user2Update.getApellidos()+"', email='"+user2Update.getEmail()+"', clave=PASSWORD("+user2Update.getClave()+"), int_usuarios_tipo_id="+user2Update.getIdUserType()+" where id="+user2Update.getId());
			
			int_usuarios newUser = new int_usuarios(user2Update.getNombres(), user2Update.getApellidos(), user2Update.getIdUserType());
			newUser.setEmail(user2Update.getEmail());
			newUser.setClave(user2Update.getClave());
			
			//this.Users.set(user2Update.getId(), newUser);
			
			Iterator i = this.Users.iterator();
			int usersIndex = 0;
			while (i.hasNext())
			{
				int idUserDB = ((int_usuarios) i.next()).getId();
				if (idUserDB == user2Update.getId())
				{
					this.Users.remove(usersIndex);
					this.Users.set(usersIndex, user2Update);
					break;
				}
				
				usersIndex++;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return resRows;
	}
	
	/**
	 * This method deletes a registry of int_usuarios table.
	 * 
	 * @param userDB int_usuarios object corresponding to the user to be deleted.
         * @return 
	 */
        @Override
	public int deleteUser(int_usuarios userDB)
	{
		int resRows = 0;
		
		try
		{
			Statement stm = this.dbConn.createStatement();
			resRows = stm.executeUpdate("delete from int_usuarios where id="+userDB.getId());
			
			int idUser = this.Users.indexOf(userDB);
			this.Users.remove(idUser);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return resRows;
	}
	
	/**
	 * This method deletes a registry of int_usuarios table.
	 * 
	 * @param userID ID of the corresponding to the user to be deleted.
         * @return 
	 */
        @Override
	public int deleteUser(int userID)
	{
		int resRows = 0;
		
		try
		{
			Statement stm = this.dbConn.createStatement();
			resRows = stm.executeUpdate("delete from int_usuarios where id="+userID);
			
			Iterator i = this.Users.iterator();
			int usersIndex = 0;
			while (i.hasNext())
			{
				int idUserDB = ((int_usuarios) i.next()).getId();
				if (idUserDB == userID)
				{
					this.Users.remove(usersIndex);
					break;
				}
				
				usersIndex++;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return resRows;
	}
}
