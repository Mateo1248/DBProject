package app.computerShop.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcceptOrderFrame {
    int idSELLER=22;
    private Connection connection;
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JFrame frame;
    TableModel tm;
    JButton showAll,showOpen,finishOrder;
    JPanel mainPanel,tablePanel;
    private ResultSet myRs = null;
    private Statement myStmt = null;
    public AcceptOrderFrame(Connection connection)
    {
        this.connection=connection;
    }

    public void start()
    {
        frame = new JFrame("OrdersFrame");
        frame.setResizable(false);
        frame.setBounds(700,300,1000,600);
        mainPanel = new JPanel();
        tablePanel = new JPanel();

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        TableModel.setVisibleRowCount(table,10);
        tablePanel.add(scrollPane);
        getOrderData();

        showAll=new JButton("showAll");
        showAll.addActionListener(new showAllButtonListener());
        showOpen=new JButton("showOpen");
        showOpen.addActionListener(new showOpenButtonListener());
        finishOrder= new JButton("finishOrder");
        finishOrder.addActionListener(new FinishOrderButtonListener());


        mainPanel.add(tablePanel);
        mainPanel.add(showAll);
        mainPanel.add(showOpen);
        mainPanel.add(finishOrder);

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);
    }

    public void getOrderData()
    {
        try
        {
            myStmt = connection.createStatement();
            String sql="Call getOrderData()";
            myRs = myStmt.executeQuery(sql);
            TableModel tm= new TableModel(myRs);
            tableModel=tm.getModel();
            table.setModel(tableModel);

        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void getOpenOrderData()
    {
        try
        {
            myStmt = connection.createStatement();
            String sql="Call getOpenOrderData()";
            myRs = myStmt.executeQuery(sql);
            TableModel tm= new TableModel(myRs);
            tableModel=tm.getModel();
            table.setModel(tableModel);

        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public class FinishOrderButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                myStmt = connection.createStatement();
                int id = (int) (tableModel.getValueAt(table.getSelectedRow(), 0));
                String sql = "CALL finishOrder("+ id +")";
                myStmt.execute(sql);
                getOrderData();
                System.out.println(id);
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }catch(ArrayIndexOutOfBoundsException ex)
            {
                System.out.println("Musisz wybrac jakis produkt");
            }
        }
    }
    public class showAllButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            getOrderData();
            System.out.println("show all");
        }
    }
    public class showOpenButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            getOpenOrderData();
            System.out.println("show open");
        }
    }
}
