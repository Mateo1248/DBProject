package app.computerShop.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ClientFrame extends UserFrame implements ActionListener {

	ClientFrame(Connection connection) {
		super("CLIENT", connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
