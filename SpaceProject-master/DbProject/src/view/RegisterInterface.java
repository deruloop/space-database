package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class RegisterInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField usernameField;
	private JTextField surnameField;
	private JTextField nameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(Connection con,String user_id,boolean type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterInterface frame = new RegisterInterface(con,user_id,type);
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
	public RegisterInterface(Connection con,String user_id,boolean type) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 492, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(61, 94, 67, 27);
		contentPane.add(lblName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(61, 172, 67, 27);
		contentPane.add(lblUsername);
		
		emailField = new JTextField();
		
		emailField.setBounds(132, 248, 294, 26);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JLabel feedbackUsername = new JLabel("");
		feedbackUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackUsername.setForeground(Color.RED);
		feedbackUsername.setBounds(136, 195, 284, 16);
		contentPane.add(feedbackUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setBounds(59, 214, 61, 16);
		contentPane.add(lblPassword);
		
		JLabel feedbackSurname = new JLabel("");
		feedbackSurname.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackSurname.setForeground(Color.RED);
		feedbackSurname.setBounds(136, 156, 284, 16);
		contentPane.add(feedbackSurname);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setForeground(Color.BLACK);
		lblSurname.setBounds(61, 133, 67, 27);
		contentPane.add(lblSurname);
		
		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(61, 248, 67, 27);
		contentPane.add(lblEmail);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(132, 172, 294, 26);
		contentPane.add(usernameField);
		
		surnameField = new JTextField();
		surnameField.setColumns(10);
		surnameField.setBounds(132, 133, 294, 26);
		contentPane.add(surnameField);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(132, 94, 294, 26);
		contentPane.add(nameField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(132, 210, 294, 26);
		contentPane.add(passwordField);
		
		JLabel feedbackPassword = new JLabel("");
		feedbackPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackPassword.setForeground(Color.RED);
		feedbackPassword.setBounds(136, 234, 284, 16);
		contentPane.add(feedbackPassword);
		
		JLabel feedbackEmail = new JLabel("");
		feedbackEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackEmail.setForeground(Color.RED);
		feedbackEmail.setBounds(136, 273, 199, 16);
		contentPane.add(feedbackEmail);
		
		JLabel feedbackName = new JLabel("");
		feedbackName.setForeground(Color.RED);
		feedbackName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackName.setBounds(136, 117, 284, 16);
		contentPane.add(feedbackName);
		
		JButton btnClose = new JButton("Back");
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClose.setBounds(-42, 0, 103, 41);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setBounds(49, 47, 386, 328);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFillTheForm = new JLabel("<html><b>Fill the form below to register a user</b></html>");
		lblFillTheForm.setBounds(49, 14, 303, 27);
		panel.add(lblFillTheForm);
		lblFillTheForm.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFillTheForm.setForeground(Color.BLACK);
		
		JButton btnConfirm = new JButton("Create");
		btnConfirm.setBounds(98, 275, 202, 29);
		panel.add(btnConfirm);
		
		JLabel feedbackAll = new JLabel("");
		feedbackAll.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackAll.setBounds(122, 301, 158, 16);
		panel.add(feedbackAll);
		feedbackAll.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		feedbackAll.setForeground(Color.RED);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.RED));
		panel_1.setBounds(52, 242, 296, 27);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Check this if you want it to be an Admin");
		label_1.setBounds(8, 0, 254, 27);
		panel_1.add(label_1);
		label_1.setForeground(Color.BLACK);
		
		JCheckBox checkboxAdmin = new JCheckBox("");
		checkboxAdmin.setBounds(264, 2, 28, 23);
		panel_1.add(checkboxAdmin);
		checkboxAdmin.setForeground(Color.BLACK);
		
	    
		
		
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)  {
				String name = nameField.getText();
				String surname = surnameField.getText();
				String user = usernameField.getText();
				String e_mail = emailField.getText();
				char[] password = passwordField.getPassword();
				boolean type = false;
				if (checkboxAdmin.isSelected()==true) {
					type=true;
				}
				
				if (checkAll(name,surname,user,password,e_mail)) {
					try {
						String pass = new String(password);
						UserHandler.register(name.toLowerCase(),surname.toLowerCase(),user,pass,e_mail,type,con);
						feedbackAll.setText("");
						feedbackName.setText("");
						feedbackSurname.setText("");
						feedbackUsername.setText("");
						feedbackPassword.setText("");
						feedbackEmail.setText("");
					} catch (SQLException e1) {} 
				} else {
					feedbackAll.setText("There are errors");
				}
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegisterInterface.class.getResource("/view/SpaceRegist.jpg")));
		label.setBounds(0, 0, 492, 410);
		contentPane.add(label);
		
		nameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String name = nameField.getText();
				if (checkName(name))
				{
					feedbackName.setText("<html><font color='green'>Ok!</font></html>");
				} else {
					feedbackName.setText("Name must only have letters");
				}
			}
		});
		
		surnameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String surname = surnameField.getText();
				if (checkName(surname))
				{
					feedbackSurname.setText("<html><font color='green'>Ok!</font></html>");
				} else {
					feedbackSurname.setText("Surname must only have letters");
				}
			}
		});
		
		usernameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String user = usernameField.getText();
				if (checkUsername(user))
				{
					feedbackUsername.setText("<html><font color='green'>Ok!</font></html>");
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
					feedbackPassword.setText("<html><font color='green'>Ok!</font></html>");
				} else {
					feedbackPassword.setText("Password must be six char or longer");
				}
			}
		}); 
		
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String e_mail = emailField.getText();
				if (checkEmail(e_mail))
				{
					feedbackEmail.setText("<html><font color='green'>Ok!</font></html>");
				} else {
					feedbackEmail.setText("email must contain char '@'");
				}
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainInterface1 MainIn;
				try {
					MainIn = new MainInterface1(con,user_id,type);
					MainIn.setVisible(true);
				    MainIn.setResizable(false);
				    dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	//CHECK
	private boolean checkName(String name) {
		if (Pattern.matches("[a-zA-Z]+", name)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkSurname(String surname) {
		if (Pattern.matches("[a-zA-Z]+", surname)) {
			return true;
		} else {
			return false;
		}
	}
	
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
	
	private boolean checkEmail(String e_mail) {
		return e_mail.contains("@");
	}
	
	private boolean checkAll(String name, String surname, String username, char[] password, String e_mail)
	{
		return checkName(name) && checkSurname(surname) && checkUsername(username) && checkPassword(password) && checkEmail(e_mail);
	}
}
