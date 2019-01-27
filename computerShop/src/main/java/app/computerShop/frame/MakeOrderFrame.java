package app.computerShop.frame;
/* WCHODZAC DO TEJ RAMKI OD RAZU ZACZYNASZ TRANSAKCJE
I INSERTUJESZ ORDER(TRZEBA JAKOS TU ID CLIENTA PRZEKAZAC)
POD PLUSEM MASZ DODAWANIE PRODUKTOW(NA RAZIE DODAJE TYLKO TEN PIERWSZY KOMP ALE ULEPSZE))

TU TRZEBA OGARNAC TEGO KLIENTA JUZ TYLKO
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
    TableModel tm1,tm2;
    JPanel mainPanel, productTablePanel,orderTablePanel,middlePanel,leftPanel;
    JButton addButton, deleteButton, showButton, orderButton;
    JTextField loginTF,passwordTF,levelTF;
    JLabel cartL,allL,priceSumL;
    private ResultSet myRs1 = null;
    private ResultSet myRs2 = null;
    private Statement myStmt = null;
    private int idd;

    //to potrzebuje miec id klienta
    public MakeOrderFrame(Connection connection,int id)
    {
        this.connection=connection;
        idd=id;
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
            addOrder(idd);
            getOrderData();

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
        priceSumL = new JLabel("Suma: 0.00");


        addButton = new JButton("+");
        addButton.addActionListener(new addButtonListener());
        deleteButton = new JButton("-");
        deleteButton.addActionListener(new DeleteButtonListener());
        showButton = new JButton("Pokaż");
        showButton.addActionListener(new ShowButtonListener());
        orderButton = new JButton("Zamów");
        orderButton.addActionListener(new OrderButtonListener());

        leftPanel.add(addButton);
        leftPanel.add(deleteButton);
        leftPanel.add(showButton);
        leftPanel.add(orderButton);

        productTable = new JTable(tableModel1);
        scrollPane1 = new JScrollPane(productTable);
        TableModel.setVisibleRowCount(productTable,10);
        productTablePanel.add(scrollPane1);
        getProductData();

        //bedzie jakas procedura ale na razie nie wiem jak ma to kupowanie wygladac w sumie;
        orderTable = new JTable(tableModel2);
        scrollPane2 = new JScrollPane(orderTable);
        TableModel.setVisibleRowCount(orderTable,10);
        orderTablePanel.add(scrollPane2);
        getOrderData();

        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        middlePanel.add(allL);
        middlePanel.add(productTablePanel);
        middlePanel.add(cartL);
        middlePanel.add(orderTablePanel);
        middlePanel.add(priceSumL);

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

    public void getProductData()
    {
        try
        {
            String sq="call getProductsData()";
            myStmt = connection.createStatement();
            myRs1 = myStmt.executeQuery(sq);
            tm1= new TableModel(myRs1);
            tableModel1=tm1.getModel();
            productTable.setModel(tableModel1);
            System.out.println("GetProductData");
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void getOrderData()
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
            String sq = "CALL getCartData()";
            myStmt = connection.createStatement();
            myRs2 = myStmt.executeQuery(sq);
            tm2= new TableModel(myRs2);
            tableModel2=tm2.getModel();
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
                int idcomp=(int)(tableModel1.getValueAt(productTable.getSelectedRow(),0));
                String sql = "CALL addAndDeleteComputer('"+idcomp+"')";
                myStmt.execute(sql);
                refreshCart();
                System.out.println("add ordered computer");
            }catch(SQLException ex)
            {
                //System.out.println(ex.getMessage());
                if(ex.getMessage().equals("TOOMUCH"))
                {
                    System.out.println("Nie mamy tyle");
                }
                else if(ex.getMessage().equals("emptystock"))
                {
                    System.out.println("Nie mamy tyle");
                }
                else
                {
                    ex.printStackTrace();
                }
            }catch(ArrayIndexOutOfBoundsException ex)
            {
                System.out.println("Musisz wybrac jakis produkt");
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

    class DeleteButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                System.out.println(tableModel2.getValueAt(orderTable.getSelectedRow(),0));
                int sel=(int)(tableModel2.getValueAt(orderTable.getSelectedRow(),0));
                String sql="CALL deleteAllOfSelectedOrderedComputers('" + sel + "')";
                /*myStmt.execute("DELETE FROM ordered_computers where id_order=@ordnr AND id_computer = "
                        + tableModel2.getValueAt(orderTable.getSelectedRow(),0)+" ;");
                */
                myStmt.execute(sql);
                refreshCart();
                tableModel2.fireTableDataChanged();
            }catch(SQLException ex)
            {
                ex.printStackTrace();
            }catch(ArrayIndexOutOfBoundsException ex)
            {
                System.out.println("Nie ma czego usunać");
            }

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
            refreshCart();
        }
    }

    public void refreshCart()
    {
        getOrderData();
        getProductData();
        refreshSum();
    }

    public void refreshSum()
    {
        try
        {
            System.out.println("sum calculating");
            myStmt = connection.createStatement();
            String sql = "SELECT sumCount() as ac";
            myRs2 = myStmt.executeQuery(sql);
            if(myRs2.next()){
                System.out.println(myRs2.getDouble("ac"));
                priceSumL.setText("Suma: "+myRs2.getDouble("ac"));
            }
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

}
