/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import dao.UserDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.TblUser;

/**
 *
 * @author zackm
 */
@ManagedBean
@SessionScoped
public class LoginPresenter implements Serializable{
    
    private String email,pass;
    
    public String login(){
        UserDao dao = new UserDao();
        List<TblUser> us = dao.login(email, pass);
        
        
        if(us != null && !us.isEmpty() )
            return "home.xhtml";
        else
            return "login.xhtml";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
    
}
