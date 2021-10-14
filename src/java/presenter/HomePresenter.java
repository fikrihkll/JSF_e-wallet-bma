/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.TransactionDao;
import dao.UserDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Wallet;

/**
 *
 * @author zackm
 */
@ManagedBean
@SessionScoped
public class HomePresenter implements Serializable {
    
    TransactionDao transDao = new TransactionDao();
    UserDao dao = new UserDao();
    
   
    
    int destinationId,amountTransfer,amountTopup;

 
    
    
    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getAmountTransfer() {
        return amountTransfer;
    }

    public void setAmountTransfer(int amountTransfer) {
        this.amountTransfer = amountTransfer;
    }

    public int getAmountTopup() {
        return amountTopup;
    }

    public void setAmountTopup(int amountTopup) {
        this.amountTopup = amountTopup;
    }
    
    public String getUser(){
        return dao.getUserData().getName();
    }
    
    public String getHolder(){
        
        return "Fikri";
    }
    
    public String getType(){
        return transDao.getType();
    }
    
    public int getCardId(){
        return transDao.getCardId();
    }
    
    public String getBalance(){
        return "Rp."+Integer.toString(transDao.getBalance());
    }
    
    public String transfer(){
        boolean isSuccess = transDao.transfer(destinationId, amountTransfer);
        
        
        
        return "home.xhtml?faces-redirect=true";
    }
    
    public String topup(){
        
        transDao.topup(amountTopup);
        
        return "home.xhtml";
    }
    
}
