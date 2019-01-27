package app.computerShop.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewComputer extends JFrame implements ActionListener {

    private JButton create;
    private JTextArea typeT, ramT, graphicT, diskT, systemT, priceT, ammountT;
    private Connection connection;

    AddNewComputer(Connection con) {
        super("REJESTRACJA KLIENTA");
        this.connection = con;
        setResizable(false);
        setBounds(700,300,500,600);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        //
        JLabel typeL = new JLabel("Typ:");
        typeL.setBounds(20,170,100,30);
        add(typeL);

        typeT = new JTextArea();
        typeT.setBounds(130,170,150,30);
        add(typeT);

        //
        JLabel ramL = new JLabel("RAM: ");
        ramL.setBounds(20,220,100,30);
        add(ramL);

        ramT = new JTextArea();
        ramT.setBounds(130,220,150,30);
        add(ramT);

        //
        JLabel graphicL = new JLabel("Grafika: ");
        graphicL.setBounds(20,270,100,30);
        add(graphicL);

        graphicT = new JTextArea();
        graphicT.setBounds(130,270,150,30);
        add(graphicT);

        //
        JLabel diskL = new JLabel("Dysk");
        diskL.setBounds(20,320,100,30);
        add(diskL);

        diskT = new JTextArea();
        diskT.setBounds(130,320,150,30);
        add(diskT);

        //
        JLabel systemL = new JLabel("System");
        systemL.setBounds(20,370,110,30);
        add(systemL);

        systemT = new JTextArea();
        systemT.setBounds(130,370,150,30);
        add(systemT);

        //
        JLabel priceL = new JLabel("Cena");
        priceL.setBounds(20,420,110,30);
        add(priceL);

        priceT = new JTextArea();
        priceT.setBounds(130,420,150,30);
        add(priceT);

        //
        JLabel ammountL = new JLabel("Ilość");
        ammountL.setBounds(20,470,110,30);
        add(ammountL);

        ammountT = new JTextArea();
        ammountT.setBounds(130,470,150,30);
        add(ammountT);

        //
        JLabel info = new JLabel("Obowiązkowo wypełnij wszystkie pola!");
        info.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        info.setBounds(20,20,444,30);
        info.setHorizontalAlignment(JLabel.CENTER);
        add(info);

        create = new JButton("Zamów");
        create.addActionListener(this);
        create.setBounds(300,70,164,30);
        add(create);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source==create)
        {
            try
            {
                Statement stmt = connection.createStatement();
                String sql = "CALL addComputer('" + typeT.getText() + "','" + ramT.getText() + "','" + graphicT.getText() + "','" + diskT.getText() + "','" + systemT.getText() + "','" + priceT.getText() + "','" + ammountT.getText() + "')";
                stmt.execute(sql);
            }
            catch(SQLException ex)
            {
                if(ex.getMessage().equals("brakuprawnien"))
                {
                    System.out.println("brak uprawnien");
                    JOptionPane.showMessageDialog(this,"Brak uprawnień","BŁĄD",JOptionPane.ERROR_MESSAGE);
                }
                else if (ex.getMessage().startsWith("execute command"))
                {
                    System.out.println("brak uprawnien");
                    JOptionPane.showMessageDialog(this,"Brak uprawnień","BŁĄD",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    ex.printStackTrace();
                }
            }

        }
    }

}
