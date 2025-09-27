package modelo;

public class Sesion {

	private static Usuario usuarioActual;
	private static Cliente clienteActual;

	public static void setUsuarioActual(Usuario usuario) {

		usuarioActual = usuario;

	}

	public static Usuario getUsuarioActual() {

		return usuarioActual;

	}

	public static Cliente getClienteActual() {
		return clienteActual;
	}

	public static void setClienteActual(Cliente cliente) {
		clienteActual = cliente;
	}

}
