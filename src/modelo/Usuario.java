package modelo;

public class Usuario {
	
	
	private int idUsuario;
	private int idCliente;
	private String usuario;
	private String password;
	private int estado;
	
	
	public Usuario() {
		this.idUsuario = 0;
		this.idCliente = 0;
		this.usuario = "";
		this.password = "";
		this.estado = 0;
	}


	public Usuario(int idUsuario, int idCliente, String usuario, String password, int estado) {
		super();
		this.idUsuario = idUsuario;
		this.idCliente = idCliente;
		this.usuario = usuario;
		this.password = password;
		this.estado = estado;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	

}
