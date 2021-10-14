/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;


import dao.UserDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author zackm
 */
@ManagedBean
@SessionScoped
public class RegisterPresenter implements Serializable {
    
    private String name,address,email,pass;
    
    
    public String registerAccount(){

        UserDao pst = new UserDao();
        boolean isSuccess = pst.register(name, email, pass, address);
        if(isSuccess){
            return "home.xhtml";
        }else{
            return "register.xhtml";
        }
        
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
