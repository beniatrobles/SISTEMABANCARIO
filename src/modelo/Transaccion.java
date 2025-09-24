package modelo;

public class Transaccion {
	
	private int idTransaccion;
	private int idCuentaOrigen;
	private int idCuentaDestino;
	private String tipo;
	private double monto;
	private String fecha;
	
	public Transaccion() {
		this.idTransaccion = 0;
		this.idCuentaOrigen = 0;
		this.idCuentaDestino = 0;
		this.tipo = "";
		this.monto = 0.0;
		this.fecha = "";
	}

	public Transaccion(int idTransaccion, int idCuentaOrigen, int idCuentaDestino, String tipo, double monto,
			String fecha) {
		super();
		this.idTransaccion = idTransaccion;
		this.idCuentaOrigen = idCuentaOrigen;
		this.idCuentaDestino = idCuentaDestino;
		this.tipo = tipo;
		this.monto = monto;
		this.fecha = fecha;
	}

	public int getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public int getIdCuentaOrigen() {
		return idCuentaOrigen;
	}

	public void setIdCuentaOrigen(int idCuentaOrigen) {
		this.idCuentaOrigen = idCuentaOrigen;
	}

	public int getIdCuentaDestino() {
		return idCuentaDestino;
	}

	public void setIdCuentaDestino(int idCuentaDestino) {
		this.idCuentaDestino = idCuentaDestino;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

}
