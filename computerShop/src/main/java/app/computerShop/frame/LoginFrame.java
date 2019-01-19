package app.computerShop.frame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class LoginFrame extends JFrame implements ActionListener {
	
	JButton loginBtn, createBtn;
	JTextArea login, password;
	JComboBox userlevel;
	
	public static void main(String[] args) {
		LoginFrame lf = new LoginFrame();
		lf.setVisible(true);
	}
	
	LoginFrame() {
		super("COMPUTER SHOP");
		setResizable(false);
		setBounds(400,400,500,320);
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add description
		JLabel descLab = new JLabel("Welcome in our shop, log in or register if you didn't do it yet.");
		descLab.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		descLab.setBounds(0,20,484,30);
		descLab.setHorizontalAlignment(JLabel.CENTER);
		add(descLab);
		
		//add picture
		ImageIcon pcimg = new ImageIcon(new ImageIcon("img\\pcshop.png").getImage().getScaledInstance(210, 160, Image.SCALE_DEFAULT));
		JLabel imglab=new JLabel();
		imglab.setBounds(20,85,210,160);
		imglab.setIcon(pcimg);
	    add(imglab);
	    
	    
	    JLabel l = new JLabel("Login:");
	    l.setBounds(250,85,200,20);
	    add(l);
	    
	    login = new JTextArea();
	    login.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	    login.setBounds(250,105,200,20);
	    add(login);
		
	    
	    JLabel p = new JLabel("Password:");
	    p.setBounds(250,125,200,20);
	    add(p);
	    
	    password = new JTextArea();
	    password.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	    password.setBounds(250,145,200,20);
	    add(password);
	    
	    
	    String []levels = {"select level...", "admin", "seller", "client"};
	    userlevel = new JComboBox<String>(levels);
	    userlevel.setBounds(250,170,200,20);
	    userlevel.setSelectedIndex(0);
	    userlevel.addActionListener(this);
	    add(userlevel);
	    
	    
	    loginBtn = new JButton("LOGIN");
	    loginBtn.setBounds(250,195,95,35);
	    loginBtn.addActionListener(this);
	    add(loginBtn);
	    
	    
	    createBtn = new JButton("REGISTER");
	    createBtn.setBounds(355,195,95,35);
	    createBtn.addActionListener(this);
	    add(createBtn);
	}

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		
		if(source == loginBtn) {
			if(!login.getText().equals("") && !password.getText().equals("") && !(userlevel.getSelectedIndex()==0)) {
				try {
					Connection con = getConnection(login.getText(), password.getText(), "computershop");
					System.out.println("connected successfull!");
					
					switch( userlevel.getSelectedIndex() ) {
					
					//admin
					case 1 :
						new AdminFrame(con);
						break;
					//seller
					case 2 :
						new SellerFrame(con);
						break;
					//client
					case 3:
						new ClientFrame(con);
					}
				} 
				catch (ClassNotFoundException | SQLException e) {
					System.out.println("Connected error!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this,"Complet all fields and choose user level!","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(source == createBtn) {
			CreateClientFrame ccf = new CreateClientFrame();
			ccf.setVisible(true);
		}

	}
	
	private Connection getConnection(String login, String password, String dbName) throws ClassNotFoundException, SQLException {
		Class.forName( "com.mysql.cj.jdbc.Driver" );
		String url = "jdbc:mysql://localhost/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		return DriverManager.getConnection( url, login, password);
	}
}
