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
	
	public boolean actualizarSaldo(Cuenta cuenta) {
		boolean respuesta = false;
	    Connection cn = Conexion.conectar();
	    
	    try {
	    	
	    	PreparedStatement pst = cn.prepareStatement(
		            "UPDATE tb_cuenta SET saldo=? WHERE idCuenta=?"
		        );
	    	
	    	pst.setDouble(1, cuenta.getSaldo());
	    	pst.setInt(2,cuenta.getIdCuenta());
	    	
	    	 if (pst.executeUpdate() > 0) {
		            respuesta = true;
		        }
			
		} catch (SQLException e) {
	        System.out.println("Error  actualizar saldo: " + e);
	    }
	    
	    return respuesta;
	}

}
