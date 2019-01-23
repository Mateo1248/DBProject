package app.computerShop.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddSellerFrame {
    private Connection connection;
    JFrame frame;
    JPanel mainPanel;
    JButton addButton;
    private Statement myStmt = null;
    JTextField getFirstName;
    JTextField getLastName;
    JTextField getSalary;
    JTextField getLogin;
    JTextField getPassword;

    public AddSellerFrame(Connection connection)
    {
        this.connection=connection;
    }

    public void start()
    {
        frame = new JFrame("UserListFrame");
        frame.setResizable(false);
        frame.setBounds(700,300,1000,600);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6,0));
        JLabel firstNameLabel = new JLabel("Imię");
        JLabel lastNameLabel = new JLabel("Nazwisko");
        JLabel salaryLabel = new JLabel("Pensja");
        JLabel loginLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Hasło");
        getFirstName = new JTextField("imię",20);
        getLastName = new JTextField("nazwisko",20);
        getSalary = new JTextField("pensja",20);
        getLogin = new JTextField("login",20);
        getPassword = new JTextField("hasło",20);

        JPanel panel1 = new JPanel(new GridBagLayout());
        panel1.add(firstNameLabel);
        panel1.add(getFirstName);

        JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.add(lastNameLabel);
        panel2.add(getLastName);

        JPanel panel3 = new JPanel(new GridBagLayout());
        panel3.add(salaryLabel);
        panel3.add(getSalary);

        JPanel panel4 = new JPanel(new GridBagLayout());
        panel4.add(loginLabel);
        panel4.add(getLogin);

        JPanel panel5 = new JPanel(new GridBagLayout());
        panel5.add(passwordLabel);
        panel5.add(getPassword);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);

        addButton = new JButton("DODAJ");
        addButton.addActionListener(new AddButtonListener());
        mainPanel.add(addButton);

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setVisible(true);

    }

    class AddButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Tu bedzie dodawanie tylko coś z procedura problem");
            try
            {
            	Double salary = Double.parseDouble(getSalary.getText());
                String sql = "CALL addSeller('"+getFirstName.getText()+"','"+getLastName.getText()+
                        "',"+salary+",'"+getLogin.getText()+"','"+getPassword.getText()+"')";
                System.out.println(sql);

                myStmt = connection.createStatement();
                myStmt.execute(sql);
                System.out.println("dodano");


            }catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
                if(ex.getMessage().equals("badlogin"))
                {
                    JOptionPane.showMessageDialog(frame,"Login zajęty","BŁĄD",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    ex.printStackTrace();
                }
            }

        }
    }
}
