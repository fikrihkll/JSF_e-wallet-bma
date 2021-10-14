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
import model.User;

/**
 *
 * @author zackm
 */
@ManagedBean
@SessionScoped
public class EditDataPresenter implements Serializable {
    String name,address,email;
    UserDao dao = new UserDao();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUser(){
        User user = dao.getUserData();
        setAddress(user.getAddress());
        setEmail(user.getEmail());
        setName(user.getName());
        return "yoho";
    }
    
    public void updateUserData(){
        
        dao.updateUserData(name, address);
    }
}
