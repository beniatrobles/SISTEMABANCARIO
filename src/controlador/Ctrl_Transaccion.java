package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.Conexion;

public class Ctrl_Transaccion {
    
    public boolean registrarTransferencia(int idCuentaOrigen, int idCuentaDestino, double monto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {
            PreparedStatement pst = cn.prepareStatement(
                "INSERT INTO tb_transaccion (idCuentaOrigen, idCuentaDestino, tipo, monto, fecha) " +
                "VALUES (?, ?, 'TRANSFERENCIA', ?, NOW())"
            );

            pst.setInt(1, idCuentaOrigen);
            pst.setInt(2, idCuentaDestino);
            pst.setDouble(3, monto);

            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al registrar transferencia: " + e);
        }

        return respuesta;
    }
}
