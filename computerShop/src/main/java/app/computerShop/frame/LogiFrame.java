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

public class LogiFrame {
    private Connection connection;
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JFrame frame;
    TableModel tm;
    JPanel mainPanel,tablePanel;
    JButton soldButton,boughtButton;
    private ResultSet myRs = null;
    private Statement myStmt = null;

    public LogiFrame(Connection connection)
    {
        this.connection=connection;
    }

    public void start()
    {
        frame = new JFrame("Logi");
        frame.setResizable(false);
        frame.setBounds(700,300,1000,600);
        mainPanel = new JPanel();
        tablePanel = new JPanel();

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        TableModel.setVisibleRowCount(table,10);
        tablePanel.add(scrollPane);

        soldButton = new JButton("Sprzedaż");
        soldButton.addActionListener(new SoldButtonListener());
        boughtButton = new JButton("Historia dostaw");
        boughtButton.addActionListener(new BoughtButtonListener());


        mainPanel.add(tablePanel);
        mainPanel.add(soldButton);
        mainPanel.add(boughtButton);



        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);
    }


    public class SoldButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                myStmt = connection.createStatement();
                myRs = myStmt.executeQuery("Call getSoldData()");
                TableModel tm = new TableModel(myRs);
                tableModel = tm.getModel();
                table.setModel(tableModel);
            }catch(SQLException ex)
            {
                if(ex.getMessage().startsWith("execute"))
                {
                    System.out.println("brak uprawnien");
                    JOptionPane.showMessageDialog(frame, "Brak uprawnień", "BŁĄD", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    public class BoughtButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                myStmt = connection.createStatement();
                myRs = myStmt.executeQuery("Call getBoughtData()");
                TableModel tm = new TableModel(myRs);
                tableModel = tm.getModel();
                table.setModel(tableModel);
            }catch(SQLException ex)
            {
                if(ex.getMessage().startsWith("execute"))
                {
                    System.out.println("brak uprawnien");
                    JOptionPane.showMessageDialog(frame, "Brak uprawnień", "BŁĄD", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    ex.printStackTrace();
                }
            }
        }
    }
}
