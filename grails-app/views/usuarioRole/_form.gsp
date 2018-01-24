<%@ page import="gaslan.UsuarioRole" %>



<div class="fieldcontain ${hasErrors(bean: usuarioRoleInstance, field: 'role', 'error')} required">
	<label for="role">
		<g:message code="usuarioRole.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="role" name="role.id" from="${gaslan.Role.list()}" optionKey="id" required="" value="${usuarioRoleInstance?.role?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: usuarioRoleInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="usuarioRole.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${gaslan.Usuario.list()}" optionKey="id" required="" value="${usuarioRoleInstance?.usuario?.id}" class="many-to-one"/>

</div>

