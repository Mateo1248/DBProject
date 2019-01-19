package app.computerShop.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AdminFrame extends UserFrame implements ActionListener {

	AdminFrame(Connection connection) {
		super("ADMIN", connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
