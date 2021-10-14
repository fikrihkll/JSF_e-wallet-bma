/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author zackm
 */
public class Singleton {
    
    public static int userId = -1;
    
    private static Connection connection=null;
    public static Connection getConnection(){  
        try{
            if(connection==null || connection.isClosed()){
                try{  
                    Class.forName("com.mysql.jdbc.Driver");     
                    connection = (Connection) DriverManager.getConnection( "jdbc:mysql://localhost:3306/ewallet_ccit","root","");  
                }catch(Exception e){  
                    System.out.println(e);  
                }  
            }else{
                return connection;
            }
        }catch(Exception e){
            
        }
        
    return connection;  
    }  
    
}
