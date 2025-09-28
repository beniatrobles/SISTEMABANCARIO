package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.Sesion;
import modelo.Usuario;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.Box;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class FrmMenu extends JFrame implements ActionListener {

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
	private JDesktopPane escritorio;

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
		mntmVerPerfil.addActionListener(this);
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
		mntmIngresosCajero.addActionListener(this);
		mnCajero.add(mntmIngresosCajero);

		menuBar.add(Box.createHorizontalGlue());

		mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mnUsuarios.setVisible(false);
		menuBar.add(mnUsuarios);

		mntmGestionarUsuario = new JMenuItem("Gestionar Usuarios ");
		mntmGestionarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnUsuarios.add(mntmGestionarUsuario);

		mostrarNombre();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == mntmVerPerfil) {
			InterPerfil interPerfil = new InterPerfil();
			escritorio.add(interPerfil);
			interPerfil.setVisible(true);
		}
		
		if(e.getSource() == mntmIngresosCajero) {
			InterCajero interCajero = new InterCajero();
			escritorio.add(interCajero);
			interCajero.setVisible(true);
		}

	}

	public void mostrarNombre() {
	    Cliente cliente = Sesion.getClienteActual();

	    // Crear escritorio si no existe
	    if (escritorio == null) {
	        escritorio = new JDesktopPane();
	        escritorio.setBounds(0, 42, 984, 618);
	        escritorio.setBackground(new Color(192, 192, 192));
	        contentPane.add(escritorio);
	    }

	    JPanel panelPerfil = new JPanel();
	    panelPerfil.setLayout(null);
	    panelPerfil.setBounds(46, 20, 869, 250);
	    panelPerfil.setBackground(new Color(230, 230, 250));
	    panelPerfil.setBorder(javax.swing.BorderFactory.createTitledBorder(
	            javax.swing.BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
	            "Perfil del Cliente",
	            javax.swing.border.TitledBorder.CENTER,
	            javax.swing.border.TitledBorder.TOP,
	            new Font("Tahoma", Font.BOLD, 18),
	            new Color(25, 25, 112)
	    ));

	    // Nombre
	    JLabel lblNombre = new JLabel("Nombre:");
	    lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    lblNombre.setBounds(40, 50, 100, 25);
	    panelPerfil.add(lblNombre);

	    JLabel lblValorNombre = new JLabel(cliente.getNombre());
	    lblValorNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblValorNombre.setBounds(150, 50, 200, 25);
	    panelPerfil.add(lblValorNombre);

	    // Apellido
	    JLabel lblApellido = new JLabel("Apellido:");
	    lblApellido.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    lblApellido.setBounds(40, 90, 100, 25);
	    panelPerfil.add(lblApellido);

	    JLabel lblValorApellido = new JLabel(cliente.getApellido());
	    lblValorApellido.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblValorApellido.setBounds(150, 90, 200, 25);
	    panelPerfil.add(lblValorApellido);

	    // DNI
	    JLabel lblDni = new JLabel("DNI:");
	    lblDni.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    lblDni.setBounds(40, 130, 100, 25);
	    panelPerfil.add(lblDni);

	    JLabel lblValorDni = new JLabel(cliente.getDni());
	    lblValorDni.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblValorDni.setBounds(150, 130, 200, 25);
	    panelPerfil.add(lblValorDni);

	    // Teléfono
	    JLabel lblTelefono = new JLabel("Teléfono:");
	    lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    lblTelefono.setBounds(400, 50, 100, 25);
	    panelPerfil.add(lblTelefono);

	    JLabel lblValorTelefono = new JLabel(cliente.getTelefono());
	    lblValorTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblValorTelefono.setBounds(510, 50, 200, 25);
	    panelPerfil.add(lblValorTelefono);

	    // Email
	    JLabel lblEmail = new JLabel("Email:");
	    lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    lblEmail.setBounds(400, 90, 100, 25);
	    panelPerfil.add(lblEmail);

	    JLabel lblValorEmail = new JLabel(cliente.getEmail());
	    lblValorEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblValorEmail.setBounds(510, 90, 300, 25);
	    panelPerfil.add(lblValorEmail);

	    // Fecha Registro
	    JLabel lblFecha = new JLabel("Fecha Registro:");
	    lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    lblFecha.setBounds(400, 130, 150, 25);
	    panelPerfil.add(lblFecha);

	    JLabel lblValorFecha = new JLabel(cliente.getFechaRegistro());
	    lblValorFecha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    lblValorFecha.setBounds(560, 130, 200, 25);
	    panelPerfil.add(lblValorFecha);

	    // Añadir el panel al escritorio en lugar del contentPane
	    escritorio.add(panelPerfil);
	    escritorio.moveToFront(panelPerfil);

	    escritorio.repaint();
	}

}
