/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolion;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author abosalaah
 */
public class Querymaker 
{
    DBman dbsuperman;
    public Querymaker() throws SQLException
    {
        dbsuperman=new DBman();
    }
    
    public int insertguardian(String gfname,String glname,String gAddress,String phone,String mail,String gid,String wphone,String job)
    {
        String apos="'"; String comma=",";
        String query="insert into guardian values("+apos+gfname+apos+comma+apos+glname+apos+comma+apos+gAddress+apos+comma+apos+phone+
                apos+comma+apos+mail+apos+comma+apos+gid+apos+comma+apos+wphone+apos+comma+apos+job+apos+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public int insertparent(String fname,String lname,String mothername,String motherjob)
    {
        String apos="'"; String comma=",";
        String query="insert into parent values("+apos+fname+apos+comma+apos+lname+apos+comma+apos+mothername+apos+comma+apos+motherjob+apos+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    
    public int insertstudent(String fname,String mname,String lname,String sid,String sex,String day,String month,String year,String address,
            String phone ,String mail,String cid,String bnumber,String gid)
    {
        String comma=",";
        String query="insert into student values("+fname+comma+mname+comma+lname+comma+sid+comma+
                sex+comma+"'"+year+"-"+month+"-"+day+"'"+comma+address+comma+phone+comma+mail+comma+
                cid+comma+bnumber+comma+gid+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    
    public ResultSet getclassesforstudent(String cid)
    {
         String query="select *from class where (cid like"+"'"+cid+"%"+"'"+")"+" and "+"(csize<capacity)"+";";
         ResultSet myres=dbsuperman.select(query);
        return  myres;
    }
    
    public int insertevent(String eid,String name,String supervisor,String startday,String startmonth,String startyear,String endday,
            String endmonth,String endyear,String fees,String follower,String efor,String capacity)
    {
        String comma=",";
        String query="insert into event values("+eid+comma+name+comma+supervisor+comma+"'"+startyear+"-"+startmonth+"-"+startday+"'"+comma+
                "'"+endyear+"-"+endmonth+"-"+endday+"'"+comma+fees+comma+follower+comma+efor+comma+capacity+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    
    public ResultSet searchforevent(String eid,String name,String supervisor,String startday,String startmonth,String startyear,String endday,
            String endmonth,String endyear,String fees,String follower,String efor,String capacity)
    {
        //bool to find if i enter in any if condition
    boolean foundwhere=false;
    System.out.println(follower);
    //int to count if i were in the first count i will not add ana and
    int firstwhere=0;
    String query="select *from event where ";
    String apos="'";
    if(!name.isEmpty())
    {
        query+="name="+apos+name+apos; foundwhere=true;
        ++firstwhere;
    }
    if(!supervisor.isEmpty())
    {
       if(firstwhere>0) {query+=" and ";}
       query+="supervisor="+apos+supervisor+apos; 
       foundwhere=true;
       ++firstwhere;
    }
    if(!eid.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="eid="+apos+eid+apos;
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
    if(!efor.equals("non")){
        if(firstwhere>0)query+=" and ";
        query+="participation="+apos+efor+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!capacity.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="capacity="+capacity; 
        foundwhere=true;
        ++firstwhere;
    }
    query+=";";
    System.out.println(foundwhere);
    if(!foundwhere)return null;
    else return dbsuperman.select(query);
    }
    
    
    public ResultSet selectalldata(String data)
    {
        String query="select *from "+data+";";
        return dbsuperman.select(query);
        
    }
    
    public boolean iseventexistforstudent(String eid) throws SQLException
    {
        String query="select *from event where (participation='student' or participation ='both') and eid="+eid+";";
        ResultSet myres=dbsuperman.select(query);
        System.out.println("myres "+myres);
        if (!myres.isBeforeFirst())return false;
        else 
        {
            while(myres.next())
            {
                System.out.println("ana hnaa");
               int size=Integer.parseInt(myres.getString("capacity"));
               System.out.println(size);
               if(size>0)return true;
            }
            return false;
        
        }
    }
    public boolean checkiffollowerin(String eid) throws SQLException
    {
        String query="select *from event where  eid="+eid+";";
        ResultSet myres=dbsuperman.select(query);
            while(myres.next())
            {
                System.out.println("ana gwa follower in");
               int follower=Integer.parseInt(myres.getString("follower"));
               System.out.println(follower);
               if(follower==1)return true;
               else return false;
            }
            
        
        return false;
    }
    public boolean checkfor3followers(String sid,String eid) throws SQLException
    {
        String query="select *from follower where sid="+sid+" and "+"eid="+eid+";";
        ResultSet myres=dbsuperman.select(query);
          if (!myres.isBeforeFirst())return true;
        else 
        {
            int cnt=0;
            while(myres.next())
            {
               ++cnt;
            }
            if(cnt<3)return true;
            else return false;
        
        }
        
    }
    public int insertfollower(String followername,String sid,String eid)
    {
        String query="insert into follower values("+followername+","+sid+","+eid+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public int insertstudentinevent(String sid,String eid)
    {
        System.out.println("ana get hnaa");
        String query="insert into sje values("+eid+","+sid+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public int updateeventsize(String eid)
    {
        String query="update event set capacity=capacity-1 where eid="+eid+";";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public boolean iseventexistforguardian(String eid) throws SQLException
    {
         String query="select *from event where (participation='parent' or participation ='both')  and eid="+eid+";";
        ResultSet myres=dbsuperman.select(query);
         if (!myres.isBeforeFirst())return false;
        else 
        {
            while(myres.next())
            {
               int size=Integer.parseInt(myres.getString("capacity"));
               if(size>0)return true;
            }
            return false;
        
        }
    }
    public int insertguardianinevent(String gid,String eid)
    {
        String query="insert into gje values("+eid+","+gid+");";
         int myres=dbsuperman.eid(query);
        return myres;
    }
    public ResultSet geteventrowselected(String eid)
    {
        String query="select *from event where eid="+eid+";";
        return dbsuperman.select(query);
    }
    public int updaterowevent(String eid,String name,String supervisor,String sday,String smonth,String syear,String eday,String emonth,String eyear,
            String fees,String follower,String efor,String capacity,String preveid)
    {
        String apos="'"; String comma=","; String dash="-";
        if(fees.isEmpty())fees="default";
        System.out.println("eid= "+eid);
        System.out.println("preveid= "+preveid);
         String query="update event set name="+apos+name+apos+","+"supervisor="+apos+supervisor+apos+","+
            "fees="+fees+comma+"startdate="+apos+syear+dash+
                    smonth+dash+sday+apos+comma+
                    "enddate="+apos+eyear+dash+
                    emonth+dash+eday+apos+comma+"follower="+follower+comma+
                    "participation="+apos+efor+apos+comma+"eid="+apos+eid+apos+" where "+
                    "eid="+apos+preveid+apos+";";
        
        return dbsuperman.eid(query);
        
    }
    public int updaterowsevent(String eid,String name,String supervisor,String sday,String smonth,String syear,String eday,String emonth,String eyear,
            String fees,String follower,String efor,String capacity,String idintable)
    {
         
                   
                    String comma=","; String apos="'"; String dash="-";
                    String query="update event set ";
                    boolean found=false; int firstset=0; 
                    
     if(!name.isEmpty())
    {
        query+="name="+apos+name+apos; found=true;
        ++firstset;
    }
    if(!supervisor.isEmpty())
    {
       if(firstset>0) {query+=",";}
        query+="supervisor="+apos+supervisor+apos; 
       found=true;
       ++firstset;
    }
    if(!eid.isEmpty()){
        if(firstset>0)query+=",";
        query+="eid="+apos+eid+apos;
        found=true;
        ++firstset;
    }
    if(!sday.equals("00")&&!smonth.equals("00")&!syear.equals("0000"))
    {
        if(firstset>0)query+=",";
        query+="startdate="+apos+syear+"-"+smonth+"-"+sday+apos;
        found=true;
        ++firstset;
    }
    if(!eday.equals("00")&&!emonth.equals("00")&!eyear.equals("0000"))
    {
        if(firstset>0)query+=",";
        query+="enddate="+apos+eyear+"-"+emonth+"-"+eday+apos;
        found=true;
        ++firstset;
    }
    if(!fees.isEmpty()){
        if(firstset>0)query+=",";
         query+="fees="+fees; 
        found=true;
        ++firstset;
    }
    if(!follower.equals("non")){
        if(firstset>0)query+=","; 
         query+="follower="+follower; 
        found=true;
        ++firstset;    
    }
    if(!efor.equals("non")){
        if(firstset>0)query+=",";
         query+="participation="+apos+efor+apos; 
        found=true;
        ++firstset;
    }
    query+=" where eid="+apos+idintable+apos;
    query+=";";
    System.out.println(query);
         return dbsuperman.eid(query);
    }
    public int inertclass(String year,String stage,String num,String sf,String size,String capacity,String level,String tid,String cid,String sid)
    {
        String comma=",";
        String query="insert into class values("+year+comma+stage+comma+num+comma+sf+comma+size+comma+capacity+comma+level+comma+tid+comma+cid+comma+sid+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public int deleteselectedeventrow(String eid)
    {
        String query="delete from event where eid="+eid+";";
        return dbsuperman.eid(query);
    }
    
    public ResultSet searchforclass(String year,String stage,String number,String sf,String size,String capacity,String level,
            String tid,String cid,String sid)
    {
        //bool to find if i enter in any if condition
    boolean foundwhere=false;
    
    //int to count if i were in the first count i will not add ana and
    int firstwhere=0;
    String query="select *from class where ";
    String apos="'";
    if(!year.equals("n"))
    {
        query+="year="+apos+year+apos; foundwhere=true;
        ++firstwhere;
    }
    if(!stage.equals("n"))
    {
       if(firstwhere>0) {query+=" and ";}
       query+="stage="+apos+stage+apos; 
       foundwhere=true;
       ++firstwhere;
    }
    if(!number.equals("n")){
        if(firstwhere>0)query+=" and ";
        query+="number="+apos+number+apos;
        foundwhere=true;
        ++firstwhere;
    }
    
    if(!sf.equals("n")){
        if(firstwhere>0)query+=" and ";
         query+="sciencefield="+apos+sf+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!size.isEmpty()){
        if(firstwhere>0)query+=" and "; 
         query+="csize="+size; 
        foundwhere=true;
        ++firstwhere;    
    }
    if(!capacity.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="capacity="+capacity; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!level.equals("n")){
        if(firstwhere>0)query+=" and ";
        query+="level="+apos+level+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!tid.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="tid="+apos+tid+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!cid.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="cid="+apos+cid+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!sid.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="sid="+apos+sid+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    query+=";";
    System.out.println(query);
    System.out.println(foundwhere);
    if(!foundwhere)return null;
    else return dbsuperman.select(query);
    }
      public int updateclasssize(String cid)
    {
        String query="update class set csize=csize+1 where cid="+cid+";";
        int myres=dbsuperman.eid(query);
        return myres;
    }
      
    public int updaterowsclass(String year,String stage,String number,String sf,String size,String capacity,String level,
            String tid,String cid,String sid,String prevcid)
    {
                   String comma=","; String apos="'"; String dash="-";
                    String query="update class set ";
                    boolean found=false; int firstset=0; 
                    
     if(!year.equals("n"))
    {
        query+="year="+apos+year+apos; found=true;
        ++firstset;
    }
    if(!stage.equals("n"))
    {
       if(firstset>0) {query+=",";}
        query+="stage="+apos+stage+apos; 
       found=true;
       ++firstset;
    }
    if(!number.equals("n")){
        if(firstset>0)query+=",";
        query+="number="+apos+number+apos;
        found=true;
        ++firstset;
    }
    
    if(!sf.equals("n")){
        if(firstset>0)query+=",";
         query+="sciencefield="+apos+sf+apos; 
        found=true;
        ++firstset;
    }
    if(!size.isEmpty()){
        if(firstset>0)query+=","; 
         query+="csize="+size; 
        found=true;
        ++firstset;    
    }
    if(!capacity.isEmpty()){
        if(firstset>0)query+=",";
         query+="capacity="+capacity; 
        found=true;
        ++firstset;
    }
    if(!level.equals("n")){
        if(firstset>0)query+=",";
         query+="level="+apos+level+apos; 
        found=true;
        ++firstset;
    }
    if(!tid.isEmpty()){
        if(firstset>0)query+=",";
         query+="tid="+apos+tid+apos; 
        found=true;
        ++firstset;
    }
    if(!cid.isEmpty()){
        if(firstset>0)query+=",";
         query+="cid="+apos+cid+apos; 
        found=true;
        ++firstset;
    }
    if(!sid.isEmpty()){
        if(firstset>0)query+=",";
         query+="sid="+apos+sid+apos; 
        found=true;
        ++firstset;
    }
    query+=" where cid="+apos+prevcid+apos;
    query+=";";
    System.out.println(query);
         return dbsuperman.eid(query);
    }
    
    public int updaterowclass(String year,String stage,String number,String sf,String size,String capacity,String level,
            String tid,String cid,String sid,String prevcid)
    {
        
         String comma=","; String apos="'"; 
        if(tid.isEmpty())tid="default";
        else tid="'"+tid+"'";
         if(sid.isEmpty())sid="default";
        else sid="'"+sid+"'";
         String query="update class set year="+apos+year+apos+","+"stage="+apos+stage+apos+","+
            "csize="+size+comma+"capacity="+capacity+comma+
                    "level="+apos+level+apos+comma+"number="+apos+number+apos+comma+"sciencefield="+apos+sf+apos+comma+
                 "tid="+tid+comma+"cid="+apos+cid+apos+comma+"sid="+sid+
                 
                 " where "+
                    "cid="+prevcid+";";
        
        return dbsuperman.eid(query);
        
                   
    }
    
    public ResultSet getclassrowselected(String cid)
    {
        String query="select *from class where cid="+"'"+cid+"'";
        return dbsuperman.select(query);
    }
    public int deleteselectedclassrow(String cid)
    {
        String query="delete from class where cid="+cid+";";
        return dbsuperman.eid(query);
    }
    public int insertbus(String num,String driver,String supervisor,String capacity,String size)
    {
        String comma=",";
        String query="insert into bus values ("+num+comma+driver+comma+supervisor+comma+capacity+comma+size+");";
        return dbsuperman.eid(query);
    }
    public ResultSet searchforbus(String num,String driver,String supervisor,String capacity,String size)
    {
        String query="select *from bus where ";
        boolean found=false; int first=0;
        if(!num.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="bnumber= "+"'"+num+"'";
            ++first;
            found=true;
        }
        if(!driver.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="driver= "+"'"+driver+"'";
            ++first;
             found=true;
        }
        if(!supervisor.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="supervisor= "+"'"+supervisor+"'";
            ++first;
             found=true;
        }
        if(!capacity.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="capacity= "+capacity;
            ++first;
             found=true;
        }
        if(!size.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="bsize= "+size;
            ++first;
             found=true;
        }
        query+=";";
        System.out.println(found);
        System.out.println(query);
        if(found)
        {
            return dbsuperman.select(query);
        }
        else return null;
    }
    public int updaterowbus(String num,String driver,String supervisor,String capacity,String size,String prevbnumber)
    {
        String apos="'"; String comma=",";
        String query="update bus set bnumber="+apos+num+apos+comma+"driver="+apos+driver+apos+comma+"supervisor="+apos+supervisor+apos+comma+
                "capacity="+capacity+comma+"bsize="+size+ " where bnumber="+apos+prevbnumber+apos+";";
        return dbsuperman.eid(query);
    }
    public ResultSet getbusrowselected(String bnumber)
    {
        String query="select *from bus where bnumber="+"'"+bnumber+"'";
        return dbsuperman.select(query);
    }
    public int updaterowsbus(String num,String driver,String supervisor,String capacity,String size,String prevbnumber)
    {
        String query="update bus set ";
        boolean found=false; int first=0;
        if(!num.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="bnumber="+"'"+num+"'";
            ++first;
            found=true;
        }
        if(!driver.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="driver="+"'"+driver+"'";
            ++first;
             found=true;
        }
        if(!supervisor.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="supervisor="+"'"+supervisor+"'";
            ++first;
             found=true;
        }
        if(!capacity.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="capacity="+capacity;
            ++first;
             found=true;
        }
        if(!size.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="bsize="+size;
            ++first;
             found=true;
        }
        query+=" where bnumber="+"'"+prevbnumber+"'"+";";
       
        if(found)
        {
            return dbsuperman.eid(query);
        }
        else return 0;
    }
    public int deleteselectedbusrow(String bnumber)
    {
        String query="delete from bus where bnumber="+bnumber+";";
        return dbsuperman.eid(query);
    }
    public boolean checkforbussize(String bnumber) throws SQLException
    {
        String query="select bsize,capacity from bus where bnumber="+bnumber+";";
        ResultSet myres=dbsuperman.select(query);
        if(myres!=null)
        {
             if (!myres.isBeforeFirst())return false;
        else 
        {
            while(myres.next())
            {
               int size=(myres.getInt("bsize"));
               int cap=(myres.getInt("capacity"));
               if(size<cap)return true;
               else return false;
            }
           
        
        }
             
        }
        
        return false;
    }
    public boolean ifstudeninbus(String sid,String bnum) throws SQLException
    {
        String query="select bnumber from student where sid="+sid+";";
        System.out.println("ana hna");
        ResultSet myres=dbsuperman.select(query);
        if(myres!=null)
        {
             if (!myres.isBeforeFirst())return false;
        else 
        {
            while(myres.next())
            {
               String bnumber=myres.getString("bnumber");
               if(bnumber=="null")
                return true;
               else 
               {
                   System.out.println("ana hna");
                   if(bnum.equals(bnumber))return false;
                   int myres2=dbsuperman.eid("update bus set bsize = bsize-1 where bnumber="+bnumber+";");
                   return true;
                   
               }
            }
           
        
        }
             
        }
        
        return false;
        
    }
    public int studentparicipateinbus(String sid,String bnumber)
    {
        String query="update student set bnumber="+bnumber+" where sid="+sid+";";
        int myres=dbsuperman.eid(query);
        int myres2=dbsuperman.eid("update bus set bsize = bsize+1 where bnumber="+bnumber+";");
        return myres;
    }
}
