package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.DBHandler;
import controller.UserHandler;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class LoginInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginInterface frame = new LoginInterface();
					frame.setVisible(true);
					frame.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public LoginInterface() throws SQLException, ClassNotFoundException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(62, 158, 67, 27);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(141, 158, 294, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel feedbackUsername = new JLabel("");
		feedbackUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackUsername.setForeground(Color.WHITE);
		feedbackUsername.setBounds(152, 183, 284, 16);
		contentPane.add(feedbackUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(62, 202, 61, 16);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 197, 294, 26);
		contentPane.add(passwordField);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(162, 247, 202, 29);
		contentPane.add(btnConfirm);
		contentPane.getRootPane().setDefaultButton(btnConfirm);
		
		JLabel feedbackPassword = new JLabel("");
		feedbackPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackPassword.setForeground(Color.WHITE);
		feedbackPassword.setBounds(152, 224, 284, 16);
		contentPane.add(feedbackPassword);
		
		JLabel feedbackAll = new JLabel("");
		feedbackAll.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackAll.setForeground(Color.WHITE);
		feedbackAll.setBounds(191, 274, 146, 16);
		contentPane.add(feedbackAll);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginInterface.class.getResource("/view/spaceLog.jpg")));
		label.setBounds(0, 0, 510, 324);
		contentPane.add(label);
		
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String user = usernameField.getText();
				if (checkUsername(user))
				{
					feedbackUsername.setText("Ok");
				} else {
					feedbackUsername.setText("Username must be six char or longer");
				}
			}
		});
		
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				char[] password = passwordField.getPassword();
				if (checkPassword(password))
				{
					feedbackPassword.setText("Ok");
				} else {
					feedbackPassword.setText("Password must be six char or longer");
				}
			}
		}); 
		
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)  {
				String user = usernameField.getText();
				char[] password = passwordField.getPassword();
				if (checkAll(user,password)) {
					try {
						String pass = new String(password);
						//establish database connection
						DBHandler dbcon = new DBHandler();
						Connection con = dbcon.getConnection();
						UserHandler.login(user, pass, con);
						dispose();
					} catch (SQLException e1) {} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} 
				} else {
					feedbackAll.setText("There are errors");
				}
			}
		});
		
	}
	
	//CHECK
	private boolean checkUsername(String username) {
		if (username.length() >= 6) {
			return true ;
		} else {
			return false; 
		}
	}
		
	private boolean checkPassword(char[] password) {
		String pass = new String(password);
		if (pass.length() >= 6) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkAll(String username, char[] password)
	{
		return checkUsername(username) && checkPassword(password);
	}
}
