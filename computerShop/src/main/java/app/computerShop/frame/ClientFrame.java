package app.computerShop.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ClientFrame extends UserFrame {
    JPanel mainPanel;
    JButton orders,products;
    Connection connection;
    private int myid;
	ClientFrame(Connection connection,int id) {
        super("KLIENT", connection);
        this.myid=id;
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
        products = new JButton("Products");
        products.addActionListener(new ProductsButtonListener());
        buttonsPanel.add(orders);

        buttonsPanel.add(products);
        mainPanel.add(buttonsPanel);
        this.getContentPane().add(BorderLayout.CENTER,mainPanel);
	}

    class OrdersButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Go to Products");
            MakeOrderFrame m= new MakeOrderFrame(connection,myid);
            m.start();
        }
    }

    class ProductsButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Go to Products");
            ProductListFrame prodlf= new ProductListFrame(connection);
            prodlf.start();
        }
    }

}
