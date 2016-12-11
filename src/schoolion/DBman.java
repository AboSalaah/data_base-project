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
    //function to insert students in database
    public ResultSet insertstudent(String fname,String sname,String lname,String sid,String sex,String day,String month,String year,String address,String phone,String mail,String cid,String bnumber,String gid) throws SQLException
    {
        Statement myStmt = null;
        ResultSet myRs = null;
        if(conn!=null)
        {
            // 2. Create a statement objects for sending sql statements to the database
           try{ myStmt = (Statement)conn.createStatement();
            // 3. Execute SQL query which return a single resultset object
            String qosmfto7="("; String qosm2fol=")"; String apos="'"; String comma=","; String dash="-";
            String insert="insert into student values";
            String febus="";
            if(bnumber=="")
            {
                febus="default";
            }
            else febus=apos+bnumber+apos;
            myRs = myStmt.executeQuery(insert+qosmfto7+apos+fname+apos+comma+apos+sname+apos+comma+apos+lname+apos+comma+apos+sid+apos+comma+apos+sex+apos+comma+apos+day+dash+month+dash+year+apos+comma+apos+address+apos+comma+apos+phone+apos+comma+apos+mail+apos+comma+apos+cid+apos+comma+febus+comma+gid+apos+qosm2fol+";");
            return myRs;
           }
          catch (SQLException exc) {
              System.out.println("d5l fel catch");
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            
        
           }
        }
        System.out.println("conn b null");
      return null;  
    }
    
    
    
    //function to insert new event
    int insert(String query) throws SQLException
    {
        Statement myStmt = null;
        int myRs ;
        if(conn!=null)
        {
            // 2. Create a statement objects for sending sql statements to the database
           try{ myStmt = (Statement)conn.createStatement();
            // 3. Execute SQL query which return a single resultset object
            
            myRs = myStmt.executeUpdate(query);
            return myRs;
           }
          catch (SQLException exc) 
          {
              exc.printStackTrace();
              System.out.println("d5l fel catch");
        } 
           finally {
            

            if (myStmt != null) {
                myStmt.close();
            }

            
        
           }
        }
      return 0;  
    }
//function to use when we want to outbut the all data in event table 3shan msln lw hwa ms7 7ad w 3ayez yshof el table b3d el ta3del kda ya3ni
public ResultSet getalleventdata()
{
  Statement mystm=null;
  ResultSet myres=null;
  if(conn!=null)
{
    try{
        mystm=(Statement)conn.createStatement();
        myres=mystm.executeQuery("select*from event");
        return myres;
    }
    catch(SQLException exc)
    {
        exc.printStackTrace();
    }
       
}
  
  return null;
}
//function used to search for a speciefc events
public ResultSet searchforevent(String eventname,String supervisor,String eventid,String fees,String startday,String startmonth,String startyear,String endday,
        String endmonth,String endyear,String follower,String participation)
{
  
  //bool to find if i enter in any if condition
    boolean foundwhere=false;
    //int to count if i were in the first count i will not add ana and
    int firstwhere=0;
    String query="select *from event where ";
    String apos="'";
    if(!eventname.isEmpty())
    {
        query+="name="+apos+eventname+apos; foundwhere=true;
        ++firstwhere;
    }
    if(!supervisor.isEmpty())
    {
       if(firstwhere>0) {query+=" and ";}
       query+="supervisor="+apos+supervisor+apos; 
       foundwhere=true;
       ++firstwhere;
    }
    if(!eventid.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="eid="+apos+eventid+apos;
        foundwhere=true;
        ++firstwhere;
    }
    if(!startday.equals("00")&&!startmonth.equals("00")&!startyear.equals("0000"))
    {
        if(firstwhere>0)query+=" and ";
        query+="startdate="+apos+startyear+"-"+startmonth+"-"+startday+apos;
        foundwhere=true;
        ++firstwhere;
    }
    if(!endday.equals("00")&&!endmonth.equals("00")&!endyear.equals("0000"))
    {
       if(firstwhere>0) query+=" and ";
        query+="enddate="+apos+endyear+"-"+endmonth+"-"+endday+apos;
        foundwhere=true;
        ++firstwhere;
    }
    if(!fees.isEmpty()){
        if(firstwhere>0)query+=" and ";
         query+="fees="+fees; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!follower.equals("non")){
        if(firstwhere>0)query+=" and "; 
         query+="follower="+follower; 
        foundwhere=true;
        ++firstwhere;    
    }
    if(!participation.equals("non")){
        if(firstwhere>0)query+=" and ";
        query+="participation="+apos+participation+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    query+=";";
    System.out.println(query);
    if(!foundwhere)return null;
    else
    {
      Statement mystm=null;
      ResultSet myres=null;
      if(conn!=null)
    {
    try
    {
        mystm=(Statement)conn.createStatement();
        myres=mystm.executeQuery(query);
        return myres;
    }
    catch(SQLException exc)
    {
        exc.printStackTrace();
    }
       
    }
      
    return null;
    }
    
}

 //fucntion t5leny lma a select row mo3yn fe table event yzhr fel informations
public ResultSet selectedrow(String tableclick)
{
  Statement mystm=null;
  ResultSet myres=null;
  if(conn!=null)
{
    try{
        mystm=(Statement)conn.createStatement();
        String query="select *from event where eid="+tableclick+";";
        myres=mystm.executeQuery(query);
        return myres;
    }
    catch(SQLException exc)
    {
        exc.printStackTrace();
    }
       
}
  
  return null;
}

//function to update aw delete sf mo3yn
 public int  updateordeleteeventselected(String query)
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
    }
       
}
  return 0;
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





























