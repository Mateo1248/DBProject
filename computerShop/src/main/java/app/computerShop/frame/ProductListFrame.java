package app.computerShop.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductListFrame {
    private Connection connection;
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JFrame frame;
    TableModel tm;
    JPanel mainPanel,tablePanel;
    JButton showButton;
    private ResultSet myRs = null;
    private Statement myStmt = null;

    public ProductListFrame(Connection connection)
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

        getData("SELECT * FROM computers");
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);

        mainPanel.add(tablePanel);

        showButton = new JButton("Pokaż");
        //showButton.addActionListener(getListener());


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


}
