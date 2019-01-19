package app.computerShop.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class SellerFrame extends UserFrame implements ActionListener {

	SellerFrame(Connection connection) {
		super("SELLER", connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
