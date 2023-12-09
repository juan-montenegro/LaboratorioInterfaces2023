package com.univalle.guiInterfacesLab2023.model;

import java.util.List;

/**
 * Interface corresponding to the int_usuarios table defining all the operations to be performed on
 * the int_usuarios table. It includes: listing all users, getting a particular user, inserting a new
 * user, updating data of one user, and deleting a particular user.
 * 
 * @author Bladimir Bacca Cortes (bladimir.bacca@correounivalle.edu.co)
 * @version $Revision: 1.0 $
 *
 */
public interface int_usuariosDAO {
	public List<int_usuarios> getAllUsers();
	public int_usuarios getUser(int idUser);
	public int_usuarios getUser(String names, String lastNames);
	public int insertUser(String name, String apellidos, String email, String pwd, int int_usuarios_tipo_id);
	public int updateUser(int_usuarios user2Update);
	public int deleteUser(int_usuarios userDB);
	public int deleteUser(int userID);
}
