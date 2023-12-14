package com.univalle.labapi.int_usuarios;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementa la interfaz int_usuariosDAO para implementar 
 * todos los métodos para manejar la tabla int_usuarios.
 */
public class int_usuariosDAOImp implements int_usuariosDAO {
	
	/**
	 * Lista de todos los usuarios en la tabla int_usuarios.
	 */
	private List<int_usuarios> Users;
        private int_usuarios currentUser;
	/**
	 * Instancia de la conexión a la base de datos.
	 */
	private Connection dbConn = null; 	
	
    /**
     * Constructor de la clase int_usuariosDAOImp
     * 
     * @param dbConn Conexión a la base de datos.
     */
	public int_usuariosDAOImp (Connection dbConn) {
            this.Users = new ArrayList<>();
            this.dbConn = dbConn;
            try {
                Statement stm = this.dbConn.createStatement();
                ResultSet rs = stm.executeQuery("select id, nombres, apellidos, email, clave, int_usuarios_tipo_id from int_usuarios");
                while (rs.next())
                {
                    int_usuarios intUser = new int_usuarios(rs.getString("nombres"), rs.getString("apellidos"), rs.getInt("int_usuarios_tipo_id"));
                    intUser.setClave(rs.getString("clave"));
                    intUser.setEmail(rs.getString("email"));
                    
                    this.Users.add(intUser);
                }
            } catch (SQLException ex) {
                Logger.getLogger(int_usuariosDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
    /** Obtiene un objeto int_usuarios por su ID desde la base de datos.
     * 
     * @param idUser El ID del usuario que se desea obtener.
     * @return El objeto int_usuarios con el ID especificado, o null si no se encuentra.
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
                    Logger.getLogger(int_usuariosDAOImp.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return resUser;
	}
	
	/**
	 * This method gets an instance of int_usuarios class corresponding to a particular registry of the 
	 * int_usuarios table using the names and last names.
	 * 
	 * @param email String with the email of the user to get from the int_usuarios table.
	 * @param password String with the password of the user to get from the int_usuarios table.
         * @return 
	 */
        @Override
	public int_usuarios getLoginUser(String email, String password) {
            int_usuarios resUser = null;

            try {
                Statement stm = this.dbConn.createStatement();
                ResultSet rs = stm.executeQuery("select id, nombres, apellidos, email, clave, int_usuarios_tipo_id from int_usuarios where email='"+email+"' AND clave='"+password+"'");
                while(rs.next()) {
                    resUser = new int_usuarios(
                            rs.getString("nombres"), 
                            rs.getString("apellidos"), 
                            rs.getInt("int_usuarios_tipo_id"),
                            rs.getString("email"),
                            rs.getString("clave"),
                            rs.getInt("id")
                    );
                    this.currentUser = resUser;
                    break;
                }
            }
            catch (SQLException e) {
                Logger.getLogger(int_usuariosDAOImp.class.getName()).log(Level.SEVERE, null, e);                                
            }

            return resUser;
	}
	
  /**
     * Obtiene una lista de todos los usuarios desde la base de datos y actualiza la lista interna.
     * 
     * @return Una lista de todos los usuarios.
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
                    Logger.getLogger(int_usuariosDAOImp.class.getName())
                            .log(Level.SEVERE, null, e);

                }
		
		return this.Users;
	}
	
    /**
     * Inserta un nuevo usuario en la base de datos y lo agrega a la lista interna.
     * 
     * @param name               El nombre del usuario.
     * @param apellidos          Los apellidos del usuario.
     * @param email              El correo electrónico del usuario.
     * @param pwd                La contraseña del usuario.
     * @param int_usuarios_tipo_id El ID del tipo de usuario.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la inserción tiene éxito).
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
		catch (SQLException e) {
                    Logger.getLogger(int_usuariosDAOImp.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return resRows;
	}
    /**
     * Actualiza un usuario existente en la base de datos y en la lista interna.
     * 
     * @param user2Update El objeto int_usuarios que se desea actualizar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la actualización tiene éxito).
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
            catch (SQLException e) {
                Logger.getLogger(int_usuariosDAOImp.class.getName()).log(Level.SEVERE, null, e);
            }

            return resRows;
    }   
	
    /**
     * Elimina un usuario existente de la base de datos y lo elimina de la lista interna.
     * 
     * @param userDB El objeto int_usuarios que se desea eliminar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la eliminación tiene éxito).
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
                Logger.getLogger(int_usuariosDAOImp.class.getName()).log(Level.SEVERE, null, e);
            }

            return resRows;
    }
	
    /**
     * Elimina un usuario por su ID de la base de datos y lo elimina de la lista interna.
     * 
     * @param userID El ID del usuario que se desea eliminar.
     * @return El número de filas afectadas en la base de datos (debería ser 1 si la eliminación tiene éxito).
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
            Logger.getLogger(int_usuariosDAOImp.class.getName()).log(Level.SEVERE, null, e);
        }

        return resRows;

    }

    public int_usuarios getCurrentUser() {
        return currentUser;
    }
    
        
    
}
