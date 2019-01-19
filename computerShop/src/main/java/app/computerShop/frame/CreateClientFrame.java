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
	JTextArea firstnameT, lastnameT, emailT, telnrT, adressT, passwordT; 
	
	CreateClientFrame() {
		super("CLIENT REGISTRATION");
		setResizable(false);
		setBounds(700,300,500,410);
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
	
		//
		JLabel firstnameL = new JLabel("First name(login):");
		firstnameL.setBounds(20,70,100,30);
		add(firstnameL);
		
		firstnameT = new JTextArea();
		firstnameT.setBounds(130,70,150,30);
		add(firstnameT);
		
		//
		JLabel lastnameL = new JLabel("Last name: ");
		lastnameL.setBounds(20,120,100,30);
		add(lastnameL);
		
		lastnameT = new JTextArea();
		lastnameT.setBounds(130,120,150,30);
		add(lastnameT);
		
		//
		JLabel emailL = new JLabel("E-mail adress: ");
		emailL.setBounds(20,170,100,30);
		add(emailL);
		
		emailT = new JTextArea();
		emailT.setBounds(130,170,150,30);
		add(emailT);
		
		//
		JLabel telnrL = new JLabel("Tel. number: ");
		telnrL.setBounds(20,220,100,30);
		add(telnrL);
		
		telnrT = new JTextArea();
		telnrT.setBounds(130,220,150,30);
		add(telnrT);
		
		//
		JLabel adressL = new JLabel("Address: ");
		adressL.setBounds(20,270,100,30);
		add(adressL);
		
		adressT = new JTextArea();
		adressT.setBounds(130,270,150,30);
		add(adressT);
		
		//
		JLabel passwordL = new JLabel("Password: ");
		passwordL.setBounds(20,320,100,30);
		add(passwordL);
		
		passwordT = new JTextArea();
		passwordT.setBounds(130,320,150,30);
		add(passwordT);
		
		//
		JLabel info = new JLabel("All fields must be completed!");
		info.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		info.setBounds(20,20,444,30);
		info.setHorizontalAlignment(JLabel.CENTER);
		add(info);
		
		create = new JButton("REGISTER");
		create.addActionListener(this);
		create.setBounds(300,70,164,30);
		add(create);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		
		if(source == create) {
			if(!firstnameT.getText().equals("") && !lastnameT.getText().equals("") && !emailT.getText().equals("") && !adressT.getText().equals("") && !passwordT.getText().equals("")) {
				//stworz nowego klienta
			}
			else {
				JOptionPane.showMessageDialog(this,"Complet all fields!","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
