package app.computerShop.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MakeOrderFrame {
    private Connection connection;
    JTable productTable,orderTable;
    DefaultTableModel tableModel1,tableModel2;
    JScrollPane scrollPane1,scrollPane2;
    JFrame frame;
    TableModel tm;
    JPanel mainPanel, productTablePanel,orderTablePanel,middlePanel,leftPanel;
    JButton addButton, deleteButton, showButton, orderButton;
    JTextField loginTF,passwordTF,levelTF;
    JLabel cartL,allL;
    private ResultSet myRs1 = null;
    private ResultSet myRs2 = null;
    private Statement myStmt = null;

    public MakeOrderFrame(Connection connection)
    {
        this.connection=connection;
    }

    public void start()
    {
        frame = new JFrame("UserListFrame");
        frame.setResizable(false);
        frame.setBounds(700,300,1000,600);
        mainPanel = new JPanel();
        middlePanel = new JPanel();
        leftPanel = new JPanel();
        productTablePanel = new JPanel();
        orderTablePanel = new JPanel();

        cartL= new JLabel("Koszyk");
        allL= new JLabel("Wszystkie produkty");

        addButton = new JButton("+");
        deleteButton = new JButton("-");
        showButton = new JButton("Pokaż");
        orderButton = new JButton("Zamów");
        leftPanel.add(addButton);
        leftPanel.add(deleteButton);
        leftPanel.add(showButton);
        leftPanel.add(orderButton);

        getProductData("SELECT * FROM computers");
        productTable = new JTable(tableModel1);
        scrollPane1 = new JScrollPane(productTable);
        TableModel.setVisibleRowCount(productTable,10);
        productTablePanel.add(scrollPane1);

        //bedzie jakas procedura ale na razie nie wiem jak ma to kupowanie wygladac w sumie;
        getOrderData("SELECT c.id_computer,c.type,c.ram,c.graphic,c.disk,c.system,c.price,c.ammount\n" +
                "FROM ordered_computers as oc\n" +
                "INNER JOIN computers as c\n" +
                "ON oc.id_computer=c.id_computer;");
        orderTable = new JTable(tableModel2);
        scrollPane1 = new JScrollPane(orderTable);
        TableModel.setVisibleRowCount(orderTable,10);
        orderTablePanel.add(scrollPane1);

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.add(allL);
        middlePanel.add(productTablePanel);
        middlePanel.add(cartL);
        middlePanel.add(orderTablePanel);

        mainPanel.add(BorderLayout.CENTER,middlePanel);
        mainPanel.add(BorderLayout.EAST,leftPanel);

        //showButton.addActionListener(getListener());


        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);
    }

    public void getProductData(String sql)
    {
        try
        {
            myStmt = connection.createStatement();
            myRs1 = myStmt.executeQuery(sql);
            TableModel tm= new TableModel(myRs1);
            tableModel1=tm.getModel();
            System.out.println("GetProductData");
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void getOrderData(String sql)
    {
        try
        {
            System.out.println("GetOrderData");
            myStmt = connection.createStatement();
            myRs2 = myStmt.executeQuery(sql);
            TableModel tm= new TableModel(myRs2);
            tableModel2=tm.getModel();

        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
