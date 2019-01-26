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

public class ProductListFrame {
    private Connection connection;
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JFrame frame;
    TableModel tm;
    JPanel mainPanel,tablePanel;
    JButton addNewButton,addNextButton;
    private ResultSet myRs = null;
    private Statement myStmt = null;

    public ProductListFrame(Connection connection)
    {
        this.connection=connection;
    }

    public void start()
    {
        frame = new JFrame("Tu uprawnienia trzeba podzielić");
        frame.setResizable(false);
        frame.setBounds(700,300,1000,600);
        mainPanel = new JPanel();
        tablePanel = new JPanel();

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        TableModel.setVisibleRowCount(table,10);
        tablePanel.add(scrollPane);
        getData();

        addNextButton = new JButton("Dodaj kolejny");
        addNextButton.addActionListener(new AddNextButtonActionListener());
        addNewButton = new JButton("Dodaj nowy");
        addNewButton.addActionListener(new AddNewButtonActionListener());


        mainPanel.add(tablePanel);
        mainPanel.add(addNewButton);
        mainPanel.add(addNextButton);



        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);
    }

    public void getData()
    {
        try
        {
            myStmt = connection.createStatement();
            myRs = myStmt.executeQuery("Call getProductsData()");
            TableModel tm= new TableModel(myRs);
            tableModel=tm.getModel();
            table.setModel(tableModel);

        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public class AddNewButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            AddNewComputer adc = new AddNewComputer(connection);
            adc.setVisible(true);
        }
    }
    public class AddNextButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                myStmt = connection.createStatement();
                int idcomp = (int) (tableModel.getValueAt(table.getSelectedRow(), 0));
                String sql = "CALL addNextComputer('"+idcomp+"','"+1+"')";
                myStmt.execute(sql);
                getData();
                System.out.println("computer added");
            }catch(SQLException ex)
            {
                ex.printStackTrace();
            }catch(ArrayIndexOutOfBoundsException ex)
            {
                System.out.println("wybierz cos");
            }
        }
    }


}
