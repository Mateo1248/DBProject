package app.computerShop.frame;



import java.awt.Color;
import java.sql.Connection;

import javax.swing.JFrame;

public abstract class UserFrame extends JFrame {

	private Connection connection;
	
	UserFrame(String title, Connection connection) {
		super(title);
		this.connection = connection;
		setResizable(false);
		setBounds(700,300,1000,600);
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setVisible(true);
	}
}
