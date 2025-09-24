package modelo;

public class Cliente {
	
	private int idCliente;
	private String nombre;
	private String apellido;
	private String dni;
	private String telefono;
	private String email;
	private String fechaRegistro;
	
	
	public Cliente() {
		this.idCliente = 0;
		this.nombre = "";
		this.apellido = "";
		this.dni = "";
		this.telefono = "";
		this.email = "";
		this.fechaRegistro = "";
	}


	public Cliente(int idCliente, String nombre, String apellido, String dni, String telefono, String email,
			String fechaRegistro) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.fechaRegistro = fechaRegistro;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	

}
