package app.computerShop.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class AdminFrame extends UserFrame {

	JPanel mainPanel;
	JButton orders,products, users;
	private Connection connection;

	AdminFrame(Connection connection) {
		super("ADMINISTRATOR", connection);
		this.connection=connection;
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,0));

		JPanel clientNamePanel = new JPanel(new GridBagLayout());
		JLabel clientNameLabel = new JLabel("Hello Client123");
		clientNamePanel.add(clientNameLabel);
		mainPanel.add(clientNamePanel);

		JPanel buttonsPanel = new JPanel();
		orders = new JButton("Orders");
		orders.addActionListener(new OrdersButtonListener());
		users = new JButton("Users");
		users.addActionListener(new WorkersButtonListener());
		products = new JButton("Products");
		products.addActionListener(new ProductsButtonListener());
		buttonsPanel.add(orders);
		buttonsPanel.add(products);
		buttonsPanel.add(users);

		mainPanel.add(buttonsPanel);
		this.getContentPane().add(BorderLayout.CENTER,mainPanel);
	}

	class OrdersButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("go to orders");
		}
	}

	class ProductsButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Go to Products");
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
			System.out.println("Go to orders");
		}
	}


}
