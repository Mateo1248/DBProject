package app.computerShop.frame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class LoginFrame extends JFrame implements ActionListener {

	JButton adminBtn, sellerBtn, customerBtn;
	
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
		
		//add description
		JLabel descLab = new JLabel("WITAJ, WYBIERZ POZIOM UPRAWNIEŃ");
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
	    
	    //add buttons
	    adminBtn = new JButton("ADMIN");
	    adminBtn.setBounds(250,85,200,35);
	    adminBtn.addActionListener(this);
	    add(adminBtn);
		
	    sellerBtn = new JButton("SELLER");
	    sellerBtn.setBounds(250,140,200,35);
	    sellerBtn.addActionListener(this);
	    add(sellerBtn);
	    
	    customerBtn = new JButton("CUSTOMER");
	    customerBtn.setBounds(250,195,200,35);
	    customerBtn.addActionListener(this);
	    add(customerBtn);
	}

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		
		if(source==adminBtn) {
			System.out.println("siema admin");
		}
		else if(source==sellerBtn) {
			System.out.println("siema sprzedawca");
		}
		else if(source==customerBtn) {
			System.out.println("siema klient");
		}
	}
}
