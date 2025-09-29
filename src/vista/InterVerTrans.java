package vista;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import conexion.Conexion;

public class InterVerTrans extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTable tabla_trans;
    private DefaultTableModel modeloTabla;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterVerTrans frame = new InterVerTrans();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public InterVerTrans() {
    	setClosable(true);
        setTitle("Todas las transferencias");
        getContentPane().setBackground(new Color(128, 128, 128));
        getContentPane().setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Transferencias");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1.setBounds(283, 11, 150, 20);
        getContentPane().add(lblNewLabel_1);

        // 🔹 Definimos modelo de la tabla
        String[] columnas = { "ID", "Cuenta Origen", "Cuenta Destino", "Tipo", "Monto", "Fecha" };
        modeloTabla = new DefaultTableModel(null, columnas);

        tabla_trans = new JTable(modeloTabla);
        tabla_trans.setBounds(10, 42, 699, 286);
        getContentPane().add(tabla_trans);

        setBounds(100, 100, 735, 440);

        cargarTransferencias(); // 🔹 Llamamos al método que llena la tabla
    }

    private void cargarTransferencias() {
        Connection cn = Conexion.conectar();
        String sql = "SELECT idTransaccion, idCuentaOrigen, idCuentaDestino, tipo, monto, fecha FROM tb_transaccion";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Limpiar antes de insertar
            modeloTabla.setRowCount(0);

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("idTransaccion");
                fila[1] = rs.getObject("idCuentaOrigen");  // puede ser NULL
                fila[2] = rs.getObject("idCuentaDestino"); // puede ser NULL
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getDouble("monto");
                fila[5] = rs.getTimestamp("fecha");

                modeloTabla.addRow(fila);
            }

            cn.close();

        } catch (Exception e) {
            System.out.println("Error al cargar transferencias: " + e);
        }
    }
}
