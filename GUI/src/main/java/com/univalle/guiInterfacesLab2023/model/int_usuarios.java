package com.univalle.guiInterfacesLab2023.model;

/**
* <b> Description</b> <p align=justify>
* This class corresponds to the data model of table int_usuarios, of the database intdb. 
* It also includes the set and get methods to modify the internal values.
* <p>
* @author Bladimir Bacca Cortes (bladimir.bacca@correounivalle.edu.co)
* @version $Revision: 1.0 $
*/

public class int_usuarios {
	/**
	 Field id, primary key.
	 */
	private int idpk;
	/**
	 * Field int_usuarios_tipo_id, foreign key of table int_usuarios_tipo
	 */
	private int int_usuarios_tipo_id_fk;
	/**
	 * Field nombres, users name.
	 */
	private String nombres;
	/**
	 * Field apellidos, users last names.
	 */
	private String apellidos;
	/**
	 * Field email, users email.
	 */
	private String email;
	/**
	 * Field clave, users password.
	 */
	private String clave;
	
	/**
	 * Constructor of the class int_usuarios.
	 * 
	 * @param nombres User names to be initialized.
	 * @param apellidos User last names to be initialized.
	 * @param int_usuarios_tipo_id ID of the user profile.
	 */
	int_usuarios(String nombres, String apellidos, int int_usuarios_tipo_id)
	{
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.int_usuarios_tipo_id_fk = int_usuarios_tipo_id;
	}
	
	/**
	 * To get the user id of the current instance.
	 * 
	 * @return Integer value with user id.
	 */
	public int getId()
	{
		return this.idpk;
	}
	
	/**
	 * To change the user id to the current instance.
	 * 
	 * @param Id Integer value holding the user id.
	 */
	public void setId(int Id)
	{
		this.idpk = Id;
	}
	
	/**
	 * To get the user int_usuarios_tipo_id of the current instance.
	 * 
	 * @return Integer value with user int_usuarios_tipo_id.
	 */
	public int getIdUserType()
	{
		return this.int_usuarios_tipo_id_fk;
	}
	
	/**
	 * To change the user int_usuarios_tipo_id to the current instance.
	 * 
	 * @param Id Integer value holding the user int_usuarios_tipo_id.
	 */
	public void setIdUserType(int Id)
	{
		this.int_usuarios_tipo_id_fk = Id;
	}
	
	/**
	 * To get the user names of the current instance.
	 * 
	 * @return String object with user names.
	 */
	public String getNombres()
	{
		return this.nombres;
	}
	
	/**
	 * To change the user names to the current instance.
	 * 
	 * @param nombres String object holding the user names.
	 */
	public void setNombres(String nombres)
	{
		this.nombres = nombres;
	}
	
	/**
	 * To get the user last names of the current instance.
	 * 
	 * @return String object with user last names.
	 */
	public String getApellidos()
	{
		return this.apellidos;
	}
	
	/**
	 * To change the user last names to the current instance.
	 * 
	 * @param apellidos String object holding the user last names.
	 */
	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}
	
	/**
	 * To get the user email of the current instance.
	 * 
	 * @return String object with user email.
	 */
	public String getEmail()
	{
		return this.email;
	}
	
	/**
	 * To change the user email to the current instance.
	 * 
	 * @param email String object holding the user email.
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * To get the user password of the current instance.
	 * 
	 * @return String object with user password.
	 */
	public String getClave()
	{
		return this.clave;
	}
	
	/**
	 * To change the user password to the current instance.
	 * 
	 * @param clave String object holding the user password.
	 */
	public void setClave(String clave)
	{
		this.clave = clave;
	}
}
