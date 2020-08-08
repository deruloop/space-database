package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class CreditInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(Connection con,String user_id,boolean type) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditInterface frame = new CreditInterface(con,user_id,type);
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
	public CreditInterface(Connection con,String user_id,boolean type) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 492, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Back");
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClose.setBounds(-42, 17, 103, 41);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setBounds(52, 113, 386, 170);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>This project was made for the university’s exam of Database in the 2017/2018 academic year by:</html>");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(16, 5, 368, 63);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		panel_1.setBounds(80, 73, 227, 91);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Calicchia Cristiano<br>Sanelli Gabriele<br>Scaccia Flavio<br></html>");
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		panel_2.setBounds(6, 5, 374, 63);
		panel.add(panel_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegisterInterface.class.getResource("/view/SpaceRegist.jpg")));
		label.setBounds(0, 0, 492, 410);
		contentPane.add(label);
		
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
	
	@SuppressWarnings("unused")
	private boolean checkAll(String name, String surname, String username, char[] password, String e_mail)
	{
		return checkName(name) && checkSurname(surname) && checkUsername(username) && checkPassword(password) && checkEmail(e_mail);
	}
}
