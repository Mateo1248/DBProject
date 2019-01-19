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
		super("SKLEP KOMPUTEROWY");
		setResizable(false);
		setBounds(700,300,500,320);
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add description
		JLabel descLab = new JLabel("Witaj w naszym sklepie, zaloguj się lub zarejestruj jesli jeszcze tego nie zrobiłeś.");
		descLab.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		descLab.setBounds(0,20,484,30);
		descLab.setHorizontalAlignment(JLabel.CENTER);
		add(descLab);
		
		//add picture
		ImageIcon pcimg = new ImageIcon(new ImageIcon("img\\pcshop.png").getImage().getScaledInstance(210, 160, Image.SCALE_DEFAULT));
		JLabel imglab=new JLabel();
		imglab.setBounds(20,70,210,160);
		imglab.setIcon(pcimg);
	    add(imglab);
	    
	    
	    JLabel l = new JLabel("Login:");
	    l.setBounds(250,70,200,20);
	    add(l);
	    
	    login = new JTextArea();
	    login.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	    login.setBounds(250,90,200,25);
	    add(login);
		
	    
	    JLabel p = new JLabel("Hasło:");
	    p.setBounds(250,115,200,20);
	    add(p);
	    
	    password = new JTextArea();
	    password.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	    password.setBounds(250,135,200,25);
	    add(password);
	    
	    JLabel x = new JLabel("Poziom uprawnień:");
	    x.setBounds(250,165,200,20);
	    add(x);
	    
	    String []levels = {"...", "administrator", "sprzedawca", "klient"};
	    userlevel = new JComboBox<String>(levels);
	    userlevel.setBounds(250,185,200,25);
	    userlevel.setSelectedIndex(0);
	    userlevel.addActionListener(this);
	    add(userlevel);
	    
	    
	    loginBtn = new JButton("LOGOWANIE");
	    loginBtn.setBounds(250,230,200,30);
	    loginBtn.addActionListener(this);
	    add(loginBtn);
	    
	    
	    createBtn = new JButton("REJESTRACJA");
	    createBtn.setBounds(20,230,200,30);
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
				JOptionPane.showMessageDialog(this,"Wypełnij wszystkie pola i wybierz odpowiedni poziom uprawnień!","BŁĄD",JOptionPane.ERROR_MESSAGE);
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
