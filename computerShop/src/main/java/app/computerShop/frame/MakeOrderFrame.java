package app.computerShop.frame;
/* WCHODZAC DO TEJ RAMKI OD RAZU ZACZYNASZ TRANSAKCJE
I INSERTUJESZ ORDER(TRZEBA JAKOS TU ID CLIENTA PRZEKAZAC)
POD PLUSEM MASZ DODAWANIE PRODUKTOW(NA RAZIE DODAJE TYLKO TEN PIERWSZY KOMP ALE ULEPSZE))

 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    //to potrzebuje miec id klienta
    public MakeOrderFrame(Connection connection)
    {
        this.connection=connection;
    }
    public void startTransaction()
    {
        try
        {
            myStmt = connection.createStatement();
            String sql = "Set autocommit=0";
            myStmt.execute(sql);
            String sql3 = "SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED";
            myStmt.execute(sql3);
            String sql2 = "Start transaction";
            myStmt.execute(sql2);
            System.out.println("transaction started");
            addOrder(20);
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void addOrder(int idclient)
    {
        try
        {
            String sql = "CALL addOrder('" + idclient + "')";
            myStmt = connection.createStatement();
            myStmt.execute(sql);
            sql="SELECT last_insert_id() INTO @ordnr";
            myStmt = connection.createStatement();
            myStmt.execute(sql);
            System.out.println("order added");
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void rollback()
    {
        try
        {
            myStmt = connection.createStatement();
            String sql = "rollback";
            myStmt.execute(sql);
            System.out.println("rollback");
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
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
        addButton.addActionListener(new addButtonListener());
        deleteButton = new JButton("-");
        showButton = new JButton("Pokaż");
        showButton.addActionListener(new ShowButtonListener());
        orderButton = new JButton("Zamów");
        orderButton.addActionListener(new OrderButtonListener());

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
        orderTable = new JTable(tableModel2);
        scrollPane2 = new JScrollPane(orderTable);
        TableModel.setVisibleRowCount(orderTable,10);
        orderTablePanel.add(scrollPane2);
        getOrderData("SELECT * FROM ordered_computers");

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.add(allL);
        middlePanel.add(productTablePanel);
        middlePanel.add(cartL);
        middlePanel.add(orderTablePanel);

        mainPanel.add(BorderLayout.CENTER,middlePanel);
        mainPanel.add(BorderLayout.EAST,leftPanel);

        //showButton.addActionListener(getListener());

        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                rollback();
                rollback();
                rollback();
                rollback();
                e.getWindow().dispose();
            }
        });
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);
        startTransaction();
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
            /*
            myStmt = connection.createStatement();
            String sql = "SELECT * FROM users";
            myRs = myStmt.executeQuery(sql);
            tm= new TableModel(myRs);
            tableModel=tm.getModel();
            table.setModel(tableModel);
            */

            System.out.println("GetOrderData");
            myStmt = connection.createStatement();
            myRs2 = myStmt.executeQuery(sql);
            TableModel tm= new TableModel(myRs2);
            tableModel2=tm.getModel();
            orderTable.setModel(tableModel2);

        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public class addButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                myStmt = connection.createStatement();
                String sql = "CALL add_ordered_computer(1)";
                myStmt.execute(sql);
                System.out.println("add ordered computer");
            }catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public class OrderButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            commit();
            System.out.println("Tu jakaś ramka cyk zamówiono");
            frame.dispose();
        }
    }

    public void commit()
    {
        try
        {
            myStmt = connection.createStatement();
            String sql = "commit";
            myStmt.execute(sql);
            System.out.println("commit");
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public class ShowButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            getOrderData("SELECT c.id_computer,c.type,c.ram,c.graphic,c.disk,c.system,c.price,oc.ammount\n" +
                    "FROM ordered_computers as oc\n" +
                    "INNER JOIN computers as c\n" +
                    "ON oc.id_computer=c.id_computer " +
                    "WHERE oc.id_order=@ordnr;");
        }
    }


}
