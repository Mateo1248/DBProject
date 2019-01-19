package app.computerShop.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.SQLException;

import com.sun.glass.events.WindowEvent;

public class AdminFrame extends UserFrame {

	AdminFrame(Connection connection) {
		super("ADMINISTRATOR", connection);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
