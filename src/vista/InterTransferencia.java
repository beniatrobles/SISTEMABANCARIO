package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import conexion.Conexion;
import controlador.Ctrl_Cuenta;
import controlador.Ctrl_Transaccion;
import modelo.Cliente;
import modelo.Cuenta;
import modelo.Sesion;

import javax.swing.JButton;

public class InterTransferencia extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtImporte;
	private JComboBox<Cuenta> cmb_cuentaOrigen;
	private JComboBox<Cuenta> cmb_cuentaDestino;
	private JButton btnRealizarTrans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterTransferencia frame = new InterTransferencia();
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
	public InterTransferencia() {
		setClosable(true);
		getContentPane().setBackground(new Color(128, 128, 128));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Transferencias");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(134, 22, 150, 20);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblCuentaOrigen = new JLabel("Cuenta origen:");
		lblCuentaOrigen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCuentaOrigen.setBounds(29, 63, 115, 20);
		getContentPane().add(lblCuentaOrigen);
		
		JLabel lblCuentaDestino = new JLabel("Cuenta destino:");
		lblCuentaDestino.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCuentaDestino.setBounds(29, 106, 115, 20);
		getContentPane().add(lblCuentaDestino);
		
		JLabel lblImporte = new JLabel("Importe:");
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblImporte.setBounds(29, 171, 115, 20);
		getContentPane().add(lblImporte);
		
		cmb_cuentaOrigen = new JComboBox<Cuenta>();
		cmb_cuentaOrigen.setBounds(169, 64, 210, 22);
		getContentPane().add(cmb_cuentaOrigen);
		
		cmb_cuentaDestino = new JComboBox<Cuenta>();
		cmb_cuentaDestino.setBounds(169, 107, 210, 22);
		getContentPane().add(cmb_cuentaDestino);
		
		txtImporte = new JTextField();
		txtImporte.setBounds(169, 173, 210, 20);
		getContentPane().add(txtImporte);
		txtImporte.setColumns(10);
		
		btnRealizarTrans = new JButton("Transaccion");
		btnRealizarTrans.setBackground(new Color(0, 255, 0));
		btnRealizarTrans.setBounds(216, 223, 106, 23);
		btnRealizarTrans.addActionListener(this);
		getContentPane().add(btnRealizarTrans);
		setBounds(100, 100, 450, 300);
		
		this.setTitle("Tansferencia");
		
		cargarCuentasPropias();
		cargarCuantas();
		

	}
	
	public void cargarCuentasPropias() {
		
		Cliente cliente = Sesion.getClienteActual();
		Connection cn = Conexion.conectar();
		String sql = "select * from tb_cuenta where idCliente = '"+cliente.getIdCliente()+"'";
		Statement st;
		
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setIdCuenta(rs.getInt("idCuenta"));
				cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				
				
				cmb_cuentaOrigen.addItem(cuenta);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception

			System.out.println("Error al conseguir las cuentas: " + e);
		}
		
	}
	
	public void cargarCuantas() {
		
		Connection cn = Conexion.conectar();
		String sql = "select * from tb_cuenta";
		Statement st;
		
		try {
			
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setIdCuenta(rs.getInt("idCuenta"));
				cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				
				
				cmb_cuentaDestino.addItem(cuenta);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception

			System.out.println("Error al conseguir todas las cuentas: " + e);
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnRealizarTrans) {
			
			 Cuenta cuentaOrigen = (Cuenta) cmb_cuentaOrigen.getSelectedItem();
			    Cuenta cuentaDestino = (Cuenta) cmb_cuentaDestino.getSelectedItem();
			    double monto = Double.parseDouble(txtImporte.getText());

			    if (cuentaOrigen.getIdCuenta() == cuentaDestino.getIdCuenta()) {
			        System.out.println("No puedes transferir a la misma cuenta");
			        JOptionPane.showMessageDialog(null, "No puedes transferir a la misma cuenta");
			        return;
			    }

			    if (monto <= 0) {
			        System.out.println("El monto debe ser mayor que 0");
			        JOptionPane.showMessageDialog(null, "El monto debe ser mayor que 0");
			        return;
			    }

			    if (cuentaOrigen.getSaldo() < monto) {
			        System.out.println("Saldo insuficiente en la cuenta origen");
			        JOptionPane.showMessageDialog(null, "Saldo insuficiente en la cuenta origen");
			        return;
			    }

			    // 1. Actualizar saldos en memoria
			    cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
			    cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

			    Ctrl_Cuenta ctrlCuenta = new Ctrl_Cuenta();
			    Ctrl_Transaccion ctrlTrans = new Ctrl_Transaccion();

			    // 2. Guardar en la BD
			    boolean okOrigen = ctrlCuenta.actualizarSaldo(cuentaOrigen);
			    boolean okDestino = ctrlCuenta.actualizarSaldo(cuentaDestino);

			    if (okOrigen && okDestino) {
			        if (ctrlTrans.registrarTransferencia(cuentaOrigen.getIdCuenta(), cuentaDestino.getIdCuenta(), monto)) {
			            System.out.println("Transferencia completada. Nuevo saldo origen: " 
			                               + cuentaOrigen.getSaldo() + " | saldo destino: " + cuentaDestino.getSaldo());
			            JOptionPane.showMessageDialog(null, "Transferencia completada");
			        } else {
			            System.out.println("Error al registrar la transferencia en la tabla tb_transaccion");
			            JOptionPane.showMessageDialog(null, "rror al registrar la transferencia.");
			        }
			    } else {
			        System.out.println("⚠️ Error al actualizar saldos de las cuentas");
			    }
			
		}
		
	}
}
