package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.Conexion;
import modelo.Cliente;

public class Ctrl_Cliente {
	
	public boolean actualizar(Cliente objeto) {
	    boolean respuesta = false;
	    Connection cn = Conexion.conectar();
	    try {
	        PreparedStatement pst = cn.prepareStatement(
	            "UPDATE tb_cliente SET nombre=?, apellido=?, dni=?, telefono=?, email=? WHERE idCliente=?"
	        );

	        pst.setString(1, objeto.getNombre());
	        pst.setString(2, objeto.getApellido());
	        pst.setString(3, objeto.getDni());
	        pst.setString(4, objeto.getTelefono());
	        pst.setString(5, objeto.getEmail());
	        pst.setInt(6, objeto.getIdCliente()); // ¡IMPORTANTE!

	        if (pst.executeUpdate() > 0) {
	            respuesta = true;
	        }
	        cn.close();

	    } catch (SQLException e) {
	        System.out.println("Error al actualizar cliente: " + e);
	    }

	    return respuesta;
	}

}
