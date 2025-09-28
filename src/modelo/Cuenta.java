package modelo;

public class Cuenta {
	
	private int idCuenta;
	private int idCliente;
	private String numeroCuenta;
	private double saldo;
	private int estado;
	private String fechaApertura;
	
	public Cuenta() {
		this.idCuenta = 0;
		this.idCliente = 0;
		this.numeroCuenta = "";
		this.saldo = 0.0;
		this.estado = 0;
		this.fechaApertura = "";
	}

	public Cuenta(int idCuenta, int idCliente, String numeroCuenta, double saldo, int estado, String fechaApertura) {
		super();
		this.idCuenta = idCuenta;
		this.idCliente = idCliente;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.estado = estado;
		this.fechaApertura = fechaApertura;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	@Override
	public String toString() {
	    return numeroCuenta + " | Saldo: $" + saldo;
	}
	
	

}
