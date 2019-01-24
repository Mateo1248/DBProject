package app.computerShop.frame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class AdminFrame extends UserFrame {

	JPanel mainPanel;
	JButton products, users, backupexport, backupimport;
	private Connection connection;

	JButton test;

	AdminFrame(Connection connection) {
		super("ADMINISTRATOR", connection);
		this.connection=connection;
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,0));

		JPanel clientNamePanel = new JPanel(new GridBagLayout());
		JLabel clientNameLabel = new JLabel("Hello Client123");
		clientNamePanel.add(clientNameLabel);
		mainPanel.add(clientNamePanel);

		JPanel buttonsPanel = new JPanel();
		users = new JButton("Users");
		users.addActionListener(new WorkersButtonListener());
		products = new JButton("Products");
		products.addActionListener(new ProductsButtonListener());
		backupexport = new JButton("Eksport DB");
		backupexport.addActionListener(new ExportBackupButtonListener());
		backupimport = new JButton("Import DB");
		backupimport.addActionListener(new ImportBackupButtonListener());
		buttonsPanel.add(products);
		buttonsPanel.add(users);
		buttonsPanel.add(backupexport);
		buttonsPanel.add(backupimport);

		test = new JButton("test");
		test.addActionListener(new Testb());
		buttonsPanel.add(test);

		mainPanel.add(buttonsPanel);
		this.getContentPane().add(BorderLayout.CENTER,mainPanel);
	}

    class Testb implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Go to Products");
            MakeOrderFrame m= new MakeOrderFrame(connection);
            m.start();
        }
    }

	class ProductsButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Go to Products");
            ProductListFrame prodlf= new ProductListFrame(connection);
            prodlf.start();
		}
	}
	
	
	class WorkersButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Go to Users");
			UserListFrame usrlf= new UserListFrame(connection);
			usrlf.start();
			System.out.println("Go to orders");
		}
	}

	
	class ExportBackupButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			 JFileChooser filechooser = new JFileChooser();
			 filechooser.setDialogTitle("Wpisz lub wybierz plik do ktorego eksportowac.");
			 filechooser.addChoosableFileFilter(new FileFilter() {
				    public String getDescription() {
				        return "MySql backups (*.sql)";
				    }
				    public boolean accept(File f) {
				        if (f.isDirectory()) {
				            return true;
				        } else {
				            return f.getName().toLowerCase().endsWith(".sql");
				        }
				    }
			});
			 filechooser.setAcceptAllFileFilterUsed(false);
		    int result = filechooser.showOpenDialog(null);
		 
		    if (result == JFileChooser.APPROVE_OPTION) {
			    File file = filechooser.getSelectedFile();
			    String filepath;
			    if(!file.getName().toLowerCase().endsWith(".sql"))
			    	filepath = file.getAbsolutePath() + ".sql";
			    else
			    	filepath = file.getAbsolutePath();
			    filepath = filepath.replace('\\', '/');
			    if(exportDB(filepath))
			    	System.out.println("export finished");
			    else
			    	System.out.println("export error");
		    }
		}
	}
	
	
	class ImportBackupButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser filechooser = new JFileChooser();
			 filechooser.setDialogTitle("Wpisz lub wybierz plik ktory eksportowac.");
			 filechooser.addChoosableFileFilter(new FileFilter() {
				    public String getDescription() {
				        return "MySql backups (*.sql)";
				    }
				    public boolean accept(File f) {
				        if (f.isDirectory()) {
				            return true;
				        } else {
				            return f.getName().toLowerCase().endsWith(".sql");
				        }
				    }
			});
			filechooser.setAcceptAllFileFilterUsed(false);
			int result = filechooser.showOpenDialog(null);
			 
			if (result == JFileChooser.APPROVE_OPTION) {
			    File file = filechooser.getSelectedFile();
			    String filepath = file.getAbsolutePath();
				if(importDB(filepath))
				   	System.out.println("import finished");
				else
				   	System.out.println("import error");
			}
		}
	}
	
	
	 private boolean exportDB(String goalpath) {
		 	String mysqldumpdir = System.getProperty("user.dir") + "\\dumptool\\mysqldump.exe";
		 	mysqldumpdir = mysqldumpdir.replace('\\', '/');
		 	String executeCmd=mysqldumpdir + " -uadmin -padmin -B computershop -r " + goalpath;
		 	Process runtimeProcess;
		    try {
		        runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		        int processComplete = runtimeProcess.waitFor();

		        if (processComplete == 0) {
		            return true;
		        } 
		        else {
		        	return false;		        
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    return false;
	}
	 
	 
	 private boolean importDB(String frompath) {
		 	String mysqldumpdir = System.getProperty("user.dir") + "\\dumptool\\mysql.exe";
		 	mysqldumpdir = mysqldumpdir.replace('\\', '/');
		    String []executeCmd= {mysqldumpdir, "--user=admin", "--password=admin", "-e", "source "+ frompath};
		    Process runtimeProcess;
		    try {
		        runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		        int processComplete = runtimeProcess.waitFor();

		        if (processComplete == 0) {
		            return true;
		        } 
		        else {
		        	return false;		        
		        }
		    } 
		    catch (Exception ex) {
		    	ex.printStackTrace();
		    }
		    return false;
	}
}
