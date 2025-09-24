package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Ctrl_Usuario;
import modelo.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtPassword;
	private JButton btnIniciarSesion;
	private FrmMenu menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setTitle("Login-Sistema Bancario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BANCO CENTRAL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(297, 366, 384, 67);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/banco.png")));
		lblNewLabel.setBounds(-10, 0, 984, 425);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("USUARIO");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(263, 463, 120, 14);
		contentPane.add(lblNewLabel_2);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBounds(393, 460, 177, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CONTRASEÑA");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(275, 519, 83, 14);
		contentPane.add(lblNewLabel_3);
		
		txtPassword = new JTextField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBounds(393, 516, 177, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.setBackground(new Color(128, 128, 128));
		btnIniciarSesion.setForeground(new Color(0, 0, 0));
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.setBounds(405, 568, 146, 30);
		contentPane.add(btnIniciarSesion);
	}
	
	private void login() {
		if (!txtUsuario.getText().isEmpty() && !txtPassword.getText().isEmpty()) {

			Ctrl_Usuario controlUsuario = new Ctrl_Usuario();

			Usuario usuario = new Usuario();
			usuario.setUsuario(txtUsuario.getText().trim());
			usuario.setPassword(txtPassword.getText().trim());

			if (controlUsuario.loginUser(usuario)) {
				JOptionPane.showMessageDialog(null, "Login correcto");
				menu = new FrmMenu();
				menu.setVisible(true);
				this.dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Usuario o password incorrectos");
				txtUsuario.setText("");
				txtPassword.setText("");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Ingrese sus credenciales.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnIniciarSesion) {
			login();
		}
		
	}
}
