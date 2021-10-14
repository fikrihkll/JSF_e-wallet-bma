/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import presenter.Singleton;


/**
 *
 * @author zackm
 */
public class UserDao {
    
   
    
    public void updateUserData(String name, String address){
        Connection connection = Singleton.getConnection(); 
        try{  
            PreparedStatement stmt = connection .prepareStatement("UPDATE tbl_user SET name = ?, address=? WHERE id = ?");  
            stmt.setString(1, name);  
            stmt.setString(2, address);  
            stmt.setString(3, Integer.toString(Singleton.userId));  
            int result = stmt.executeUpdate();  
            
           
                    
        }catch(Exception e){  
            System.out.println(e);  
        }  

    }
    
    public Boolean login(String email,String pass){
        
        Boolean isSuccess = false;
        Connection connection = Singleton.getConnection(); 
        User user = new User();
        try{  
            Statement stmt=connection.createStatement();    
            ResultSet rs=stmt.executeQuery("select id from tbl_user WHERE email = '"+email+"' AND pass = '"+pass+"'");    

            int id = -1;
            while(rs.next()){  
                id= rs.getInt("id");
            }  
            System.out.println(Integer.toString(id));
            if(id>0){
                Singleton.userId=id;
                isSuccess = true;
            }
            
                  
        }catch(Exception e){  
            System.out.println(e);  
        }  
        return isSuccess;
    }
    
    public User getUserData(){
        Connection connection = Singleton.getConnection(); 
        User user = new User();
        try{  
            Statement stmt=connection.createStatement();    
            ResultSet rs=stmt.executeQuery("select * from tbl_user WHERE id = "+Integer.toString(Singleton.userId));    
            
            
            
            while(rs.next()){  
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
            }  
            
                
        }catch(Exception e){  
            System.out.println(e);  
        }  
        return user;
    }
    
    public boolean register(String name,String email, String pass, String address){
        boolean isSuccess= true;
        try{  
           Connection connection = Singleton.getConnection(); 
           
            Statement stmt1=connection.createStatement();    
            ResultSet rs1=stmt1.executeQuery("select * from tbl_user WHERE email = '"+email+"'");    
            
            int counter = 0;
            while(rs1.next()){  
                counter++;
            }  
            
            if(counter==0){
                PreparedStatement stmt = connection .prepareStatement("insert into tbl_user values(?,?,?,?,?)");  
                stmt.setString(1, "0");  
                stmt.setString(2, name);  
                stmt.setString(3, email);  
                stmt.setString(4, address);  
                stmt.setString(5, pass);  
                int result = stmt.executeUpdate();  
                System.out.println("RESULT 1 "+Integer.toString(result));

                Statement stmt2=connection.createStatement();    
                ResultSet rs=stmt2.executeQuery("SELECT MAX( id ) as last_id FROM tbl_user");
                int lastId = -1;

                while(rs.next()){  
                    lastId = rs.getInt("last_id");
                }

                PreparedStatement stmt3 = connection .prepareStatement("insert into tbl_wallet values(?,?,?,?)");  
                stmt3.setString(1, "0");  
                stmt3.setString(2, "0");  
                stmt3.setString(3, Integer.toString(lastId));  
                stmt3.setString(4, "GOLD");  

                result = stmt3.executeUpdate();  

                System.out.println("RESULT 2 "+Integer.toString(result));

                Singleton.userId = lastId;
            }else
                isSuccess=false;
            
           
            
            
            
        }catch(Exception e){  
            System.out.println(e);  
        }  
        return isSuccess;
    }
    
}
