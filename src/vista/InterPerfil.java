package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import conexion.Conexion;
import controlador.Ctrl_Cliente;
import modelo.Cliente;
import modelo.Cuenta;
import modelo.Sesion;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class InterPerfil extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreCliente;
	private JTextField txtApellidoCliente;
	private JTextField txtDniCliente;
	private JTextField txtTelefonoCliente;
	private JTextField txtEmailCliente;
	private JList<Cuenta> listCuentas;
	private DefaultListModel<Cuenta> modeloLista;
	private JButton btnEliminarCuenta;
	private JButton btnActualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterPerfil frame = new InterPerfil();
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
	public InterPerfil() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 726, 496);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(33, 26, 403, 397);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(33, 89, 77, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(33, 141, 77, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("DNI:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(33, 202, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Telefono:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(219, 89, 77, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(219, 141, 46, 14);
		panel.add(lblNewLabel_4);

		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNombreCliente.setBounds(101, 87, 86, 20);
		panel.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);

		txtApellidoCliente = new JTextField();
		txtApellidoCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtApellidoCliente.setColumns(10);
		txtApellidoCliente.setBounds(101, 139, 86, 20);
		panel.add(txtApellidoCliente);

		txtDniCliente = new JTextField();
		txtDniCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDniCliente.setColumns(10);
		txtDniCliente.setBounds(101, 200, 86, 20);
		panel.add(txtDniCliente);

		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTelefonoCliente.setColumns(10);
		txtTelefonoCliente.setBounds(294, 87, 86, 20);
		panel.add(txtTelefonoCliente);

		txtEmailCliente = new JTextField();
		txtEmailCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmailCliente.setColumns(10);
		txtEmailCliente.setBounds(294, 139, 86, 20);
		panel.add(txtEmailCliente);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(164, 363, 101, 23);
		btnActualizar.addActionListener(this);
		panel.add(btnActualizar);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.setBackground(new Color(51, 204, 0));

		JLabel lblNewLabel_5 = new JLabel("Datos del Cliente");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(126, 11, 163, 14);
		panel.add(lblNewLabel_5);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(448, 27, 241, 396);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_5_1 = new JLabel("Cuentas del Cliente");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5_1.setBounds(32, 11, 190, 14);
		panel_1.add(lblNewLabel_5_1);

		listCuentas = new JList<Cuenta>();
		listCuentas.setBackground(new Color(192, 192, 192));
		listCuentas.setBounds(18, 52, 204, 299);
		panel_1.add(listCuentas);

		modeloLista = new DefaultListModel<Cuenta>();
		listCuentas.setModel(modeloLista);

		btnEliminarCuenta = new JButton("Eliminar Cuenta");
		btnEliminarCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminarCuenta.setBackground(new Color(255, 51, 51));
		btnEliminarCuenta.setBounds(56, 362, 129, 23);
		panel_1.add(btnEliminarCuenta);

		cargarCliente();
		cargarCuentas();

	}

	public void cargarCliente() {

		Cliente cliente = Sesion.getClienteActual();

		txtNombreCliente.setText(cliente.getNombre());
		txtApellidoCliente.setText(cliente.getApellido());
		txtDniCliente.setText(cliente.getDni());
		txtTelefonoCliente.setText(cliente.getTelefono());
		txtEmailCliente.setText(cliente.getEmail());

	}

	public void cargarCuentas() {
		Cliente cliente = Sesion.getClienteActual();
		Connection cn = Conexion.conectar();
		String sql = "select * from tb_cuenta where idCliente = '" + cliente.getIdCliente() + "'";
		Statement st;

		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
			    cuenta.setNumeroCuenta(rs.getString("numeroCuenta"));
			    cuenta.setSaldo(rs.getDouble("saldo"));
			    cuenta.setFechaApertura(rs.getString("fechaApertura"));
			    cuenta.setIdCuenta(rs.getInt("idCuenta"));
			    cuenta.setIdCliente(rs.getInt("idCliente"));
			    cuenta.setEstado(rs.getInt("estado"));
				// Agregar al modelo
				modeloLista.addElement(cuenta);
			}

		} catch (SQLException e) {
			// TODO: handle exception

			System.out.println("Error al conseguir las cuentas: " + e);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnActualizar) {
			Ctrl_Cliente controlCliente = new Ctrl_Cliente();
			
			try {
				
				Cliente cliente = new Cliente();
				
				cliente.setIdCliente(Sesion.getClienteActual().getIdCliente());
				
				cliente.setNombre(txtNombreCliente.getText().trim());
				cliente.setApellido(txtApellidoCliente.getText().trim());
				cliente.setDni(txtDniCliente.getText().trim());
				cliente.setTelefono(txtTelefonoCliente.getText().trim());
				cliente.setEmail(txtEmailCliente.getText().trim());
				
				
				if(controlCliente.actualizar(cliente)) {
					JOptionPane.showMessageDialog(null, "Cliente: "+cliente.getNombre()+" actualizado correctamente");
					
					Sesion.setClienteActual(cliente);
					
					cargarCliente();
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Error al actualizar el cliente");
				}
				
			} catch (Exception ex) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + ex.getMessage());
			}
			
			
			
			
		}
		
	}
}
