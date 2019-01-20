package app.computerShop.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ClientFrame extends UserFrame {
    JPanel mainPanel;
    JButton orders,products;
	ClientFrame(Connection connection) {
        super("KLIENT", connection);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,0));

        JPanel clientNamePanel = new JPanel(new GridBagLayout());
        JLabel clientNameLabel = new JLabel("Hello Client123");
        clientNamePanel.add(clientNameLabel);
        mainPanel.add(clientNamePanel);

        JPanel buttonsPanel = new JPanel();
        orders = new JButton("Orders");
        products = new JButton("Products");
        buttonsPanel.add(orders);

        buttonsPanel.add(products);
        mainPanel.add(buttonsPanel);
        this.getContentPane().add(BorderLayout.CENTER,mainPanel);
	}


}
