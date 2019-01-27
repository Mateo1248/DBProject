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
    JPanel mainPanel,tablePanel;
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
        tablePanel = new JPanel();
        JPanel deladdPanel = new JPanel(new GridBagLayout());
        JPanel searchPanel = new JPanel(new GridBagLayout());
        JPanel tfandlabelPanel = new JPanel(new GridLayout(2,0));

        JLabel loginL = new JLabel("login");
        JLabel passL = new JLabel("hasło");
        JLabel levelL = new JLabel("typ");
        JPanel searchLabelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,90,0));
        searchLabelsPanel.add(loginL);
        searchLabelsPanel.add(passL);
        searchLabelsPanel.add(levelL);

        addButton = new JButton("Dodaj");
        deleteButton = new JButton("Usuń");
        showButton = new JButton("Pokaż");
        showButton.addActionListener(new getListener());
        addButton.addActionListener(new addButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());


        deladdPanel.add(addButton);
        deladdPanel.add(deleteButton);

        loginTF=new JTextField(15);
        passwordTF=new JTextField(15);
        levelTF=new JTextField(15);
        searchPanel.add(loginTF);
        searchPanel.add(passwordTF);
        searchPanel.add(levelTF);
        searchPanel.add(showButton);

        tfandlabelPanel.add(searchLabelsPanel);
        tfandlabelPanel.add(searchPanel);

        getData("Select * FROM users");
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);

        mainPanel.add(BorderLayout.NORTH,deladdPanel);
        mainPanel.add(BorderLayout.SOUTH,tfandlabelPanel);
        mainPanel.add(BorderLayout.CENTER,tablePanel);

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
                System.out.println("Usunięto wiersz");
                System.out.println(tableModel.getValueAt(table.getSelectedRow(),0));
                myStmt.execute("CALL deleteUser("
                        + tableModel.getValueAt(table.getSelectedRow(),0)+" );");
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
