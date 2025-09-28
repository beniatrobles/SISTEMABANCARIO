package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.Conexion;
import modelo.Cuenta;

public class Ctrl_Cuenta {
	
	public boolean eliminar(Cuenta cuenta) {
		
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		
		try {
			PreparedStatement pst = cn.prepareStatement("DELETE FROM tb_cuenta WHERE idCuenta=?");
			pst.setInt(1, cuenta.getIdCuenta());
			
			if(pst.executeUpdate() > 0) {
				respuesta = true;
			}
			
			cn.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error al eliminar cuenta: " + e);
		}
		
		return respuesta;
		
	}

}
