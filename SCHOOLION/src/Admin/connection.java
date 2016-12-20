/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author abosalaah
 */
public class connection {
   private Connection myConn;
   public connection()
    {
          myConn = null;
    }
   public Connection getconnection() throws SQLException
    {
        String user = "root";
        String pass = "root3243";
        String connectionURL = "jdbc:mysql://localhost:3306/schoolion?autoReconnect=true&useSSL=false";
        try {
            myConn = (Connection) DriverManager.getConnection(connectionURL, user, pass);
        }
        catch(SQLException exc)
        {
            System.out.println("d5l fel catch");
            JOptionPane.showMessageDialog(null, exc);
            return null;
        }
        return myConn;
    }
    
}

