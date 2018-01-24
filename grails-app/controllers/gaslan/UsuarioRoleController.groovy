package gaslan



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsuarioRoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioRole.list(params), model:[usuarioRoleInstanceCount: UsuarioRole.count()]
    }

    def show(UsuarioRole usuarioRoleInstance) {
        respond usuarioRoleInstance
    }

    def create() {
        respond new UsuarioRole(params)
    }

    @Transactional
    def save(UsuarioRole usuarioRoleInstance) {
        if (usuarioRoleInstance == null) {
            notFound()
            return
        }

        if (usuarioRoleInstance.hasErrors()) {
            respond usuarioRoleInstance.errors, view:'create'
            return
        }

        usuarioRoleInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioRole.label', default: 'UsuarioRole'), usuarioRoleInstance.id])
                redirect usuarioRoleInstance
            }
            '*' { respond usuarioRoleInstance, [status: CREATED] }
        }
    }

    def edit(UsuarioRole usuarioRoleInstance) {
        respond usuarioRoleInstance
    }

    @Transactional
    def update(UsuarioRole usuarioRoleInstance) {
        if (usuarioRoleInstance == null) {
            notFound()
            return
        }

        if (usuarioRoleInstance.hasErrors()) {
            respond usuarioRoleInstance.errors, view:'edit'
            return
        }

        usuarioRoleInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'UsuarioRole.label', default: 'UsuarioRole'), usuarioRoleInstance.id])
                redirect usuarioRoleInstance
            }
            '*'{ respond usuarioRoleInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioRole usuarioRoleInstance) {

        if (usuarioRoleInstance == null) {
            notFound()
            return
        }

        usuarioRoleInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'UsuarioRole.label', default: 'UsuarioRole'), usuarioRoleInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioRole.label', default: 'UsuarioRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
