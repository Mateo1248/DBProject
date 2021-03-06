package app.computerShop.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SellerFrame extends UserFrame {

	JPanel mainPanel;
	JButton orders,products,workerButton,logiButton;
	private Connection connection;
	private Statement myStmt = null;
	int myid;
	SellerFrame(Connection connection,int myid) {
		super("Seller", connection);
		this.connection=connection;
		this.myid=myid;
		setSessionID(myid);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,0));

		JPanel sellerNamePanel = new JPanel(new GridBagLayout());
		JLabel sellerNameLabel = new JLabel("Hello Seller123");
		sellerNamePanel.add(sellerNameLabel);
		mainPanel.add(sellerNamePanel);

		JPanel buttonsPanel = new JPanel();
		orders = new JButton("Orders");
		orders.addActionListener(new OrderButtonListener());
		products = new JButton("Products");
		products.addActionListener(new ProductsButtonListener());
		workerButton=new JButton("Pracownicy");
		workerButton.addActionListener(new WorkersButtonListener());
		logiButton = new JButton("logi");
		logiButton.addActionListener(new LogiButtonListener());
		buttonsPanel.add(orders);
		buttonsPanel.add(products);
		buttonsPanel.add(workerButton);
		buttonsPanel.add(logiButton);


		mainPanel.add(buttonsPanel);
		this.getContentPane().add(BorderLayout.CENTER,mainPanel);
	}

	class OrderButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Go to Products");
			AcceptOrderFrame m = new AcceptOrderFrame(connection);
			m.start();
		}
	}
	class ProductsButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Go to Products");
			ProductListFrame m = new ProductListFrame(connection);
			m.start();
		}
	}
	class LogiButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Go to Logi");
			LogiFrame usrlf= new LogiFrame(connection);
			usrlf.start();
			System.out.println("Go to orders");
		}
	}

	public void setSessionID(int id)
	{
		try
		{
			myStmt = connection.createStatement();
			String sql = "Call setSessionID('"+id+"')";
			myStmt.execute(sql);
			System.out.println("id set to" + id);
		}catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	class WorkersButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Go to Users");
			UserListFrame usrlf= new UserListFrame(connection);
			usrlf.start();
		}
	}



}
