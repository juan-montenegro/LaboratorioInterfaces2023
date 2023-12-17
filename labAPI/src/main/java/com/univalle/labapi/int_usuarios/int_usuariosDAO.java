package com.univalle.labapi.int_usuarios;

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

    /**
     *
     * @return
     */
    public List<int_usuarios> getAllUsers();

    /**
     *
     * @param idUser
     * @return
     */
    public int_usuarios getUser(int idUser);
    
    /**
     *
     * @param email
     * @param password
     * @return
     */
    public int_usuarios getLoginUser(String email, String password);

    /**
     *
     * @param name
     * @param apellidos
     * @param email
     * @param pwd
     * @param int_usuarios_tipo_id
     * @return
     */
    public int insertUser(String name, String apellidos, String email, String pwd, int int_usuarios_tipo_id);

    /**
     *
     * @param user2Update
     * @return
     */
    public int updateUser(int_usuarios user2Update);

    /**
     *
     * @param userDB
     * @return
     */
    public int deleteUser(int_usuarios userDB);

    /**
     *
     * @param userID
     * @return
     */
    public int deleteUser(int userID);
}
