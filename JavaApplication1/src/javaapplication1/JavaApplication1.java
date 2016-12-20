/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author abosalaah
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "mysql3243";

        try {
            // 1. Get a connection to database
             String connectionURL = "jdbc:mysql://localhost:3306/students?autoReconnect=true&useSSL=false";
            myConn = (Connection) DriverManager.getConnection(connectionURL, user, pass);

            // 2. Create a statement objects for sending sql statements to the database
            myStmt = (Statement) myConn.createStatement();

            // 3. Execute SQL query which return a single resultset object
            myRs = myStmt.executeQuery("select * from information");

            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("lname") + ", " + myRs.getString("fname")+", "+myRs.getInt("sid"));
            }

        } catch (SQLException exc) {
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }    
}
    

