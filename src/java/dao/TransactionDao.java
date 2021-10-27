/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Logs;
import model.Wallet;
import presenter.Singleton;

/**
 *
 * @author zackm
 */
public class TransactionDao {
    
    Wallet wallet=null;
    
    public boolean topup(int amount){
        boolean res = true;
        try{
            Connection connection = Singleton.getConnection(); 
           
            PreparedStatement stmt = connection .prepareStatement("UPDATE tbl_wallet SET balance = balance+ ? WHERE user_id = ?");  
            stmt = connection.prepareStatement("START TRANSACTION");
            
            stmt = connection .prepareStatement("UPDATE tbl_wallet SET balance = balance+ ? WHERE user_id = ?");  
            stmt.setString(1, Integer.toString(amount));  
            stmt.setString(2, Integer.toString(Singleton.userId));  
            int result = stmt.executeUpdate();  
            
            stmt = connection .prepareStatement("INSERT INTO tbl_log VALUES(0,(SELECT id FROM tbl_wallet WHERE user_id = "+Singleton.userId+"),'IN',"+amount+",NOW())");  
            int result2 = stmt.executeUpdate();  
            
            if(result >0 && result2 > 0){
                stmt = connection.prepareStatement("COMMIT");
            }else{
                res = false;
                stmt = connection.prepareStatement("ROLLBACK");
            }
            stmt.executeUpdate();
            
            System.out.println("RESULT TRANS 2 "+Integer.toString(result2));
            System.out.println("RESULT TOPUP 1 "+Integer.toString(result));    
            
        }catch(Exception e){
            res = false;
            System.out.println(e.toString());
        }
        return res;
    }
    
    public ArrayList<Logs> getLogs(){
        ArrayList<Logs> list = new ArrayList();
        try{
            Connection connection = Singleton.getConnection(); 
            
            Statement stmtBl= connection.createStatement();    
            ResultSet rs=stmtBl.executeQuery("SELECT * FROM tbl_log WHERE wallet_id = (SELECT id FROM tbl_wallet WHERE user_id = "+Singleton.userId+" )");
            
            while(rs.next()){  
                list.add(
                new Logs(rs.getInt("id"),rs.getInt("amount"),rs.getString("type"),rs.getString("date"))
                );
            }  
            System.out.println("IN");
            System.out.println(list.size());
        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    
    public boolean transfer(int destinationId, int amount){
        boolean isSuccess = false;
       
        Connection connection = Singleton.getConnection(); 
        try{
            
            
            PreparedStatement stmt = connection .prepareStatement("START TRANSACTION");
            stmt.executeUpdate();
            
            Statement stmtBl= connection.createStatement();    
            ResultSet rs=stmtBl.executeQuery("SELECT balance FROM tbl_wallet WHERE user_id = "+Singleton.userId);
            
            int balance = 0;
            
            while(rs.next()){  
                balance = rs.getInt("balance");
            }  
            
            if(balance >=amount){
                System.out.println(Integer.toString(destinationId));
                stmt = connection .prepareStatement("UPDATE tbl_wallet SET balance = balance+ ? WHERE id = ?");  
                stmt.setString(1, Integer.toString(amount));  
                stmt.setString(2, Integer.toString(destinationId));  
                int result = stmt.executeUpdate();  
                 
                stmt = connection .prepareStatement("UPDATE tbl_wallet SET balance = balance- ? WHERE user_id = ?");  
                stmt.setString(1, Integer.toString(amount));  
                stmt.setString(2, Integer.toString(Singleton.userId));  
                int result2 = stmt.executeUpdate(); 
                
                stmt = connection .prepareStatement("INSERT INTO tbl_log VALUES(0,(SELECT id FROM tbl_wallet WHERE user_id = "+Singleton.userId+"),'OUT',"+amount+",NOW())");  
                int result3 = stmt.executeUpdate();  
                
                stmt = connection .prepareStatement("INSERT INTO tbl_log VALUES(0,"+destinationId+",'IN',"+amount+",NOW())");  
                int result4 = stmt.executeUpdate();  
                
                System.out.println("RESULT TRANS 1 "+Integer.toString(result)+"RESULT TRANS 2 "+Integer.toString(result2)+"RESULT TRANS 3 "+Integer.toString(result3)+"RESULT TRANS 4 "+Integer.toString(result4));   
                
                if(result > 0 && result2 > 0 && result3 > 0 && result4 > 0){
                    stmt = connection .prepareStatement("COMMIT");
                }else{
                    stmt = connection .prepareStatement("ROLLBACK");
                    System.out.println("ROLL");
                }
                stmt.executeUpdate();
                
                isSuccess =true;
            }
           
        }catch(Exception e){
            System.out.println(e.toString());
            
            try{
                PreparedStatement stmt = connection .prepareStatement("ROLLBACK");
                stmt.executeUpdate();
                System.out.println("ROLL EX");
            }catch(Exception e2){
                
            }
            
        }  
        
        return isSuccess;
    }
    
    
    
    public int getCardId(){
        if(wallet==null){
            getWalletInfo();
        }
        return wallet.getId();
    }
    
    public String getType(){
        if(wallet==null){
            getWalletInfo();
        }
        return wallet.getType();
    }
    
    public int getBalance(){
        getWalletInfo();
        return wallet.getBalance();
    }
    
    public Wallet getWalletInfo(){
        Connection connection = Singleton.getConnection(); 
        int balance= 0;
        try{  
            Statement stmt= connection.createStatement();    
            ResultSet rs=stmt.executeQuery("SELECT tbl_wallet.id,tbl_wallet.user_id,tbl_wallet.balance,tbl_wallet.type,tbl_user.name FROM tbl_wallet INNER JOIN tbl_user ON tbl_wallet.user_id = "+Singleton.userId+" AND tbl_user.id = "+Singleton.userId);    
            
            while(rs.next()){  
                wallet = new Wallet(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("balance"),
                        rs.getString("type"),
                        rs.getString("name")
                );
            }  
                    
        }catch(Exception e){  
            System.out.println(e);  
        }  
        return wallet;
    }
    
}


