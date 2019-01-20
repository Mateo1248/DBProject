package app.computerShop.frame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;

public abstract class UserFrame extends JFrame {

	private Connection connection;
	String title;
	
	UserFrame(String title, Connection connection) {
		super(title);
		this.title=title;
		this.connection = connection;
		setResizable(false);
		setBounds(700,300,1000,600);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		this.addWindowListener(new MyWindowAdapter());
		setVisible(true);
	}

	public class MyWindowAdapter extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			try { connection.close(); System.out.println( title + " connection closed"); }
			catch (SQLException e1) { e1.printStackTrace(); }
		}
	}

}
