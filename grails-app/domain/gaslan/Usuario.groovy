package gaslan

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Usuario implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
        String nombre
        String apellidoPaterno
        String apellidoMaterno
        String email
        String telefono 
        String direccion
        
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Usuario(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	Set<Role> getAuthorities() {
		UsuarioRole.findAllByUsuario(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
                apellidoPaterno (nullable: true)
                apellidoMaterno (nullable: true)
                email   (nullable: true)
                telefono (nullable: true)
                direccion (nullable: true)
	}

	static mapping = {
		password column: '`password`'
	}
}
