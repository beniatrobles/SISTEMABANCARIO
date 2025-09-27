package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.Sesion;
import modelo.Usuario;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.Box;

import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JTextField;



public class FrmMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnPerfil;
	private JMenu mnCajero;
	private JMenu mnTransferencias;
	private JMenuItem mntmIngresosCajero;
	private JMenuItem mntmVerPerfil;
	private JMenuItem mntmRealizarTransferencia;
	private JMenuItem mntmVerTransferencias;
	private JMenu mnUsuarios;
	private JMenuItem mntmGestionarUsuario;
	private JPanel panel;
	private JTextField txtNombre;
	private JTextField txtApellido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenu frame = new FrmMenu();
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
	public FrmMenu() {
		setBackground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setTitle("Sistema Bancario");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 984, 42);
		contentPane.add(menuBar);
		
		mnPerfil = new JMenu("Perfil");
		mnPerfil.setBackground(new Color(128, 128, 128));
		mnPerfil.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnPerfil);
		
		mntmVerPerfil = new JMenuItem("Ver Perfil");
		mntmVerPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnPerfil.add(mntmVerPerfil);
		
		mnTransferencias = new JMenu("Transferencias");
		mnTransferencias.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnTransferencias);
		
		mntmRealizarTransferencia = new JMenuItem("Realizar Transferencia");
		mntmRealizarTransferencia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnTransferencias.add(mntmRealizarTransferencia);
		
		mntmVerTransferencias = new JMenuItem("Ver Transferencias");
		mntmVerTransferencias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnTransferencias.add(mntmVerTransferencias);
		
		mnCajero = new JMenu("Cajero");
		mnCajero.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnCajero);
		
		mntmIngresosCajero = new JMenuItem("Ingreso / Retirada ");
		mntmIngresosCajero.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnCajero.add(mntmIngresosCajero);
		
		menuBar.add(Box.createHorizontalGlue()); 
		
		mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mnUsuarios.setVisible(false);
		menuBar.add(mnUsuarios);
		
		
		mntmGestionarUsuario = new JMenuItem("Gestionar Usuarios ");
		mntmGestionarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnUsuarios.add(mntmGestionarUsuario);
		
		panel = new JPanel();
		panel.setBounds(46, 108, 869, 374);
		contentPane.add(panel);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		mostrarNombre();
		
		
	}
	
	private void mostrarNombre() {
		
		Cliente cliente = Sesion.getClienteActual();
		
		txtNombre.setText(cliente.getNombre());
		txtApellido.setText(cliente.getApellido());
		
		
	}
}
