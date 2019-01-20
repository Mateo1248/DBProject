package app.computerShop.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserListFrame {

    private Connection connection;
    JTable table;
    JScrollPane scrollPane;
    JFrame frame;
    JPanel mainPanel,buttonsPanel,tablePanel;
    JButton addButton, deleteButton, showButton;
    private ResultSet myRs = null;
    private Statement myStmt = null;

    public UserListFrame(Connection connection)
    {
        this.connection=connection;
    }

    public void start()
    {
        frame = new JFrame("UserListFrame");
        frame.setResizable(false);
        frame.setBounds(700,300,1000,600);
        mainPanel = new JPanel();
        buttonsPanel = new JPanel();
        tablePanel = new JPanel();

        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");
        showButton = new JButton("Pokaż");
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(showButton);
        showButton.addActionListener(new showButtonListener());
        addButton.addActionListener(new addButtonListener());

        getData();
        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);

        mainPanel.add(buttonsPanel);
        mainPanel.add(tablePanel);

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);
    }

    class showButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            getData();
            frame.repaint();
        }
    }

    class addButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            AddSellerFrame asf = new AddSellerFrame(connection);
            asf.start();
        }
    }

    public void getData()
    {
        try
        {
            myStmt = connection.createStatement();
            String sql = "SELECT * FROM users";
            myRs = myStmt.executeQuery(sql);
            table = new JTable(TableModel.buildTableModel(myRs));
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
