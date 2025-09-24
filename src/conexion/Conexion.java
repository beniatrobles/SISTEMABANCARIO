package conexion;

import java.sql.Connection;   
import java.sql.DriverManager; 
import java.sql.SQLException;  



public class Conexion {
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection cn = DriverManager.getConnection(
                "jdbc:mysql://localhost/bd_sistema_bancario", "root", "");
            return cn;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e);
        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e);
        }
        return null;
    }
}
