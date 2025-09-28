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
import modelo.Cliente;
import modelo.Cuenta;
import modelo.Sesion;

import javax.swing.JButton;

public class InterCajero extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txt_importe;
	private JComboBox<Cuenta> cmb_cuenta;
	private JButton btnIngresar;
	private JButton btnRetirar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterCajero frame = new InterCajero();
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
	public InterCajero() {
		setClosable(true);
		getContentPane().setBackground(new Color(128, 128, 128));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Elige la cuenta:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(42, 72, 115, 20);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cajero");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(170, 22, 115, 20);
		getContentPane().add(lblNewLabel_1);

		cmb_cuenta = new JComboBox<Cuenta>();
		cmb_cuenta.setBounds(167, 70, 189, 22);
		getContentPane().add(cmb_cuenta);

		JLabel lblImporte = new JLabel("Importe:");
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblImporte.setBounds(42, 136, 115, 20);
		getContentPane().add(lblImporte);

		txt_importe = new JTextField();
		txt_importe.setBounds(167, 138, 189, 20);
		getContentPane().add(txt_importe);
		txt_importe.setColumns(10);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIngresar.setBackground(new Color(51, 204, 0));
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(71, 216, 101, 23);
		getContentPane().add(btnIngresar);

		btnRetirar = new JButton("Retirar");
		btnRetirar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRetirar.setBackground(new Color(255, 51, 51));
		btnRetirar.addActionListener(this);
		btnRetirar.setBounds(227, 216, 129, 23);
		getContentPane().add(btnRetirar);
		setBounds(100, 100, 450, 300);

		cargarComboCuentas();

	}

	public void cargarComboCuentas() {

		Cliente cliente = Sesion.getClienteActual();

		Connection cn = Conexion.conectar();
		String sql = "select * from tb_cuenta where idCliente = '" + cliente.getIdCliente() + "'";
		Statement st;
		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setIdCuenta(rs.getInt("idCuenta"));
				cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
				cuenta.setSaldo(rs.getDouble("saldo"));

				cmb_cuenta.addItem(cuenta);
			}

		} catch (SQLException e) {
			// TODO: handle exception

			System.out.println("Error al conseguir las cuentas: " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Cuenta cuentaSeleccionada = (Cuenta) cmb_cuenta.getSelectedItem();
		double importe = Double.parseDouble(txt_importe.getText());

		if (e.getSource() == btnIngresar) {

			double nuevoSaldo = cuentaSeleccionada.getSaldo() + importe;
			cuentaSeleccionada.setSaldo(nuevoSaldo);

			Ctrl_Cuenta ctrlCuenta = new Ctrl_Cuenta();
			if (ctrlCuenta.actualizarSaldo(cuentaSeleccionada)) {
				System.out.println("Saldo actualizado correctamente. Nuevo saldo: " + nuevoSaldo);
				JOptionPane.showMessageDialog(null, "Saldo actualizado correctamente. Nuevo saldo: " + nuevoSaldo);

				txt_importe.setText("");
				cargarComboCuentas();

			} else {
				System.out.println("Error al actualizar saldo");
			}

		}

		if (e.getSource() == btnRetirar) {
			// Validar que haya saldo suficiente
			if (cuentaSeleccionada.getSaldo() >= importe) {
				double nuevoSaldo = cuentaSeleccionada.getSaldo() - importe;
				cuentaSeleccionada.setSaldo(nuevoSaldo);

				Ctrl_Cuenta ctrlCuenta = new Ctrl_Cuenta();
				if (ctrlCuenta.actualizarSaldo(cuentaSeleccionada)) {
					System.out.println("Saldo retirado correctamente. Nuevo saldo: " + nuevoSaldo);
					JOptionPane.showMessageDialog(null, "Saldo retirado correctamente. Nuevo saldo: " + nuevoSaldo);
					txt_importe.setText("");
					cargarComboCuentas();
				} else {
					System.out.println("Error al actualizar saldo");
				}

			} else {
				System.out.println("Saldo insuficiente");
				JOptionPane.showMessageDialog(null, "Saldo insuficiente");
			}
		}

	}
}
