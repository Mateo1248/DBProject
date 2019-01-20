package app.computerShop.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class UserListFrame {

    private Connection connection;
    JTable table;
    JScrollPane scrollPane;
    JFrame frame;
    JPanel mainPanel,buttonsPanel,tablePanel;
    JButton button1, button2, button3;
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

        button1 = new JButton("Dodaj");
        button2 = new JButton("Usuń");
        button3 = new JButton("Pokaż");
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        button3.addActionListener(new Button3Listener());

        getData();
        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);

        mainPanel.add(buttonsPanel);
        mainPanel.add(tablePanel);

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);
    }

    class Button3Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            getData();
            frame.repaint();
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
            scrollPane = new JScrollPane(table);
            System.out.println("got it");
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
