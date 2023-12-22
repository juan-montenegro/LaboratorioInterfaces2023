class UserModel {
    constructor(
        email,
        password,
        nombres = "",
        apellidos = "",
        int_usuarios_tipo_id = 0,      
        id = 0
    ) {
        this.idpk = id;
        this.int_usuarios_tipo_id_fk = int_usuarios_tipo_id;
        this.firstName = nombres;
        this.lastName = apellidos;
        this.email = email;
        this.password = password;
    }
  
    get id() {
      return this.idpk;
    }
  
    /**
     * @param {number} id
     */
    set id(id) {
      this.idpk = id;
    }
  
    get userTypeId() {
      return this.int_usuarios_tipo_id_fk;
    }
  
    /**
     * @param {number} id
     */
    set userTypeId(id) {
      this.int_usuarios_tipo_id_fk = id;
    }
  
    get firstName() {
      return this.firstName;
    }
  
    /**
     * @param {String} firstName
     */
    set firstName(firstName) {
      this.firstName = firstName;
    }
  
    get lastName() {
      return this.lastName;
    }
  
    /**
     * @param {String} lastName
     */
    set lastName(lastName) {
      this.lastName = lastName;
    }
  
    get email() {
      return this.email;
    }
  
    /**
     * @param {String} email
     */
    set email(email) {
      this.email = email;
    }
  
    get password() {
      return this.password;
    }
  
    /**
     * @param {String} password
     */
    set password(password) {
      this.password = password;
    }
  
    toJSON() {
      return {
        id: this.idpk, 
        int_usuarios_tipo_id: this.int_usuarios_tipo_id_fk,
        nombres: this.firstName, 
        apellidos: this.lastName, 
        email: this.email, 
        clave: this.password
      }
    }
  }
module.export = UserModel;