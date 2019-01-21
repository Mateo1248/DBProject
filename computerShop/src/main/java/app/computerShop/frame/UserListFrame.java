package app.computerShop.frame;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserListFrame {

    private Connection connection;
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JFrame frame;
    TableModel tm;
    JPanel mainPanel,buttonsPanel,tablePanel;
    JButton addButton, deleteButton, showButton;
    JTextField loginTF,passwordTF,levelTF;
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
        showButton.addActionListener(new getListener());
        addButton.addActionListener(new addButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());

        loginTF=new JTextField(20);
        passwordTF=new JTextField(20);
        levelTF=new JTextField(20);

        getData("SELECT * FROM users");
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);

        mainPanel.add(loginTF);
        mainPanel.add(passwordTF);
        mainPanel.add(levelTF);
        mainPanel.add(buttonsPanel);
        mainPanel.add(tablePanel);

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);
    }

    public void getData(String sql)
    {
        try
        {
            myStmt = connection.createStatement();
            myRs = myStmt.executeQuery(sql);
            TableModel tm= new TableModel(myRs);
            tableModel=tm.getModel();

        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    class DeleteButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                myStmt.execute("DELETE FROM users where login='sfs';");
                tableModel.fireTableDataChanged();
            }catch(SQLException ex)
            {
                ex.printStackTrace();
            }

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

    class getListener implements ActionListener
    {
        String login;
        String pass;
        String level;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                login = loginTF.getText().trim();
                pass = passwordTF.getText().trim();
                level = levelTF.getText().trim();
                // Replace any single quote chars w/ space char or SQL will think the ' is the end of the string
                login = login.replace('\'', ' ');
                pass = pass.replace('\'', ' ');
                level = level.replace('\'', ' ');
                if (login.length() > 0 ||
                        pass.length() > 0 ||
                        level.length() > 0)
                {
                    // build the query and execute it. Provide the results to the table model
                    {
                        myStmt = connection.createStatement();
                        String sql = buildQuery(login,pass,level);
                        myRs = myStmt.executeQuery(sql);
                        tm= new TableModel(myRs);
                        tableModel=tm.getModel();
                        table.setModel(tableModel);
                    }
                }
                else
                {
                    myStmt = connection.createStatement();
                    String sql = "SELECT * FROM users";
                    myRs = myStmt.executeQuery(sql);
                    tm= new TableModel(myRs);
                    tableModel=tm.getModel();
                    table.setModel(tableModel);
                }
            } catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public String buildQuery(String login,String pass,String level) {

        String whereClause = " where";
        // Build the where clause
        if(login.length() > 0)
            whereClause += (" login = '" + login + "'");

        if(pass.length() > 0) {
            if(whereClause.length() > 6)
                whereClause += " AND";
            whereClause += (" password = '" + pass + "'");
        }

        if(level.length() > 0) {
            if(whereClause.length() > 6)
                whereClause += " AND";
            whereClause += (" level = '" + level + "'");
        }
        System.out.println("select * from users" + whereClause);
        return "select * from users" + whereClause;

    }
}
