package app.computerShop.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class CreateClientFrame extends JFrame implements ActionListener{

	private JButton create;
	private JTextArea firstnameT, lastnameT, emailT, telnrT, postcodeT, cityT, passwordT, loginT,addressT;
	private Connection connection;
	
	CreateClientFrame(Connection con) {
		super("REJESTRACJA KLIENTA");
		this.connection = con;
		setResizable(false);
		setBounds(700,300,500,600);
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
	
		//
		JLabel loginL = new JLabel("Login: ");
		loginL.setBounds(20,70,100,30);
		add(loginL);
		
		loginT = new JTextArea();
		loginT.setBounds(130,70,150,30);
		add(loginT);
		
		//
		JLabel passwordL = new JLabel("Hasło: ");
		passwordL.setBounds(20,120,100,30);
		add(passwordL);
		
		passwordT = new JTextArea();
		passwordT.setBounds(130,120,150,30);
		add(passwordT);
		
		//
		JLabel firstnameL = new JLabel("Imię:");
		firstnameL.setBounds(20,170,100,30);
		add(firstnameL);
		
		firstnameT = new JTextArea();
		firstnameT.setBounds(130,170,150,30);
		add(firstnameT);
		
		//
		JLabel lastnameL = new JLabel("Nazwisko: ");
		lastnameL.setBounds(20,220,100,30);
		add(lastnameL);
		
		lastnameT = new JTextArea();
		lastnameT.setBounds(130,220,150,30);
		add(lastnameT);
		
		//
		JLabel emailL = new JLabel("Adres e-mail: ");
		emailL.setBounds(20,270,100,30);
		add(emailL);
		
		emailT = new JTextArea();
		emailT.setBounds(130,270,150,30);
		add(emailT);
		
		//
		JLabel telnrL = new JLabel("Numer tel.: ");
		telnrL.setBounds(20,320,100,30);
		add(telnrL);
		
		telnrT = new JTextArea();
		telnrT.setBounds(130,320,150,30);
		add(telnrT);
		
		//
		JLabel postcodeL = new JLabel("Kod pocztowy: ");
		postcodeL.setBounds(20,370,110,30);
		add(postcodeL);
		
		postcodeT = new JTextArea();
		postcodeT.setBounds(130,370,150,30);
		add(postcodeT);
		
		//
		JLabel cityL = new JLabel("Miasto: ");
		cityL.setBounds(20,420,110,30);
		add(cityL);
		
		cityT = new JTextArea();
		cityT.setBounds(130,420,150,30);
		add(cityT);

		//
		JLabel addressL = new JLabel("Adres: ");
		addressL.setBounds(20,470,110,30);
		add(addressL);

		addressT = new JTextArea();
		addressT.setBounds(130,470,150,30);
		add(addressT);
		
		//
		JLabel info = new JLabel("Obowiązkowo wypełnij wszystkie pola!");
		info.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		info.setBounds(20,20,444,30);
		info.setHorizontalAlignment(JLabel.CENTER);
		add(info);
		
		create = new JButton("ZAREJESTRUJ SIĘ");
		create.addActionListener(this);
		create.setBounds(300,70,164,30);
		add(create);
	}
	
	
	@Override	
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		
		if(source == create) {
			if(!firstnameT.getText().equals("") && !lastnameT.getText().equals("") && !emailT.getText().equals("") && !telnrT.getText().equals("") && !cityT.getText().equals("") && !postcodeT.getText().equals("") && !passwordT.getText().equals("") && !loginT.getText().equals("")) {
				if(telnrT.getText().length()==9) {
					try {
						Statement stmt =	connection.createStatement();
						String addclient = "CALL addClient('"+firstnameT.getText()+"','"+lastnameT.getText()+"','"+emailT.getText()+"','"+telnrT.getText()+"','"+postcodeT.getText()+"','"+cityT.getText()+"','"+addressT.getText()+"','"+loginT.getText()+"','"+passwordT.getText()+"')";
						stmt.execute(addclient);
					} catch (SQLException e) {
                        System.out.println(e.getMessage());
                        if(e.getMessage().equals("badlogin"))
                        {
                            JOptionPane.showMessageDialog(this,"Login zajęty","BŁĄD",JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            e.printStackTrace();
                        }
					}
				}
				else {
					JOptionPane.showMessageDialog(this,"Number telefonu powinien zawierac 9 cyfr!","BŁĄD",JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(this,"Wypełnij wszystkie pola!","BŁĄD",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	public class MyWindowAdapter extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			try { connection.close(); System.out.println("registration exit"); }
			catch (SQLException e1) { e1.printStackTrace(); }
		}
	}
}
