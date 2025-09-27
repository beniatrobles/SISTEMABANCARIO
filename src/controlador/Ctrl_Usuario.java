package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Cliente;
import modelo.Sesion;
import modelo.Usuario;

public class Ctrl_Usuario {
	
	// metodo para iniciar sesion
	public boolean loginUser(String user, String password) {
	    Connection cn = Conexion.conectar();
	    String sql = "SELECT u.idUsuario, u.idCliente, u.usuario, u.password, u.estado, " +
	                 "c.nombre, c.apellido, c.dni, c.telefono, c.email, c.fechaRegistro " +
	                 "FROM tb_usuario u " +
	                 "INNER JOIN tb_cliente c ON u.idCliente = c.idCliente " +
	                 "WHERE u.usuario = ? AND u.password = ? AND u.estado = 1";

	    try {
	        PreparedStatement pst = cn.prepareStatement(sql);
	        pst.setString(1, user);
	        pst.setString(2, password);

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            // Construimos el usuario
	            Usuario usuario = new Usuario(
	                rs.getInt("idUsuario"),
	                rs.getInt("idCliente"),
	                rs.getString("usuario"),
	                rs.getString("password"),
	                rs.getInt("estado")
	            );

	            // Construimos el cliente
	            Cliente cliente = new Cliente(
	                rs.getInt("idCliente"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("dni"),
	                rs.getString("telefono"),
	                rs.getString("email"),
	                rs.getString("fechaRegistro")
	            );

	            // Guardamos en sesión
	            Sesion.setUsuarioActual(usuario);
	            Sesion.setClienteActual(cliente);

	            cn.close();
	            return true;
	        }

	        cn.close();

	    } catch (SQLException e) {
	        System.out.println("Error al iniciar sesión: " + e);
	    }

	    return false;
	}

}
