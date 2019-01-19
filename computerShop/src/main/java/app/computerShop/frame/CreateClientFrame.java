package app.computerShop.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CreateClientFrame extends JFrame implements ActionListener{

	JButton create;
	JTextArea firstnameT, lastnameT, emailT, telnrT, adressT, passwordT, loginT; 
	
	CreateClientFrame() {
		super("REJESTRACJA KLIENTA");
		setResizable(false);
		setBounds(700,300,500,460);
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
		JLabel adressL = new JLabel("Adres wysyłki: ");
		adressL.setBounds(20,370,110,30);
		add(adressL);
		
		adressT = new JTextArea();
		adressT.setBounds(130,370,150,30);
		add(adressT);
		
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
			if(!firstnameT.getText().equals("") && !lastnameT.getText().equals("") && !emailT.getText().equals("") && !adressT.getText().equals("") && !passwordT.getText().equals("") && !loginT.getText().equals("")) {
				//stworz nowego klienta
			}
			else {
				JOptionPane.showMessageDialog(this,"Wypełnij wszystkie pola!","BŁĄD",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
