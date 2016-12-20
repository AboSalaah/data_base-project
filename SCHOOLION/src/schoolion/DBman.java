package schoolion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Admin.connection;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author abosalaah
 */
public class DBman 
{
    private Connection conn;
   public DBman() throws SQLException
    {
        conn=null;
        connection conobj = new connection();
        conn=conobj.getconnection();
        if(conn==null)
        {
            System.out.println("failed to connect to database");
        }
        System.out.println("conn msh b null");
        
    }
 
 public ResultSet select(String query)
 {
        Statement mystm=null;
        ResultSet myres=null;
  if(conn!=null)
{
    try{
        mystm=(Statement)conn.createStatement();
        myres=mystm.executeQuery(query);
        return myres;
    }
    catch(SQLException exc)
    {
        exc.printStackTrace();
        JOptionPane.showMessageDialog(null, "incorrect data");
    }
       
}
  return null;
 }
 public int eid(String query)
 {
   Statement mystm=null;
   int myres=0;
  if(conn!=null)
{
    try{
        mystm=(Statement)conn.createStatement();
        myres=mystm.executeUpdate(query);
        return myres;
    }
    catch(SQLException exc)
    {
        exc.printStackTrace();
        JOptionPane.showMessageDialog(null, "incorrect data");
    }
       
}
  return 0;
 }
    
}





























