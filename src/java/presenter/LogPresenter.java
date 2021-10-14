/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;
import dao.TransactionDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Logs;

/**
 *
 * @author zackm
 */
//@ManagedBean(name = "log_bean" , eager= true)
//@SessionScoped
public class LogPresenter implements Serializable {
    
    private ArrayList<Logs> logs = new ArrayList<Logs>(
            Arrays.asList(
            new Logs(1,1000,"IN","2021-10-06 20:20")
            )
    );

    public ArrayList<Logs> getLogs() {
        TransactionDao transDao = new TransactionDao();
        ArrayList<Logs> newLogs = transDao.getLogs();
        return newLogs;
    }
    
    public LogPresenter() {
        
    }
    
}
