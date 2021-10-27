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
import pojo.TblUser;

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
        TblUser us = dao.getById(Singleton.userId);
        System.out.println(us.getTblWallets());
        User user = new User(us.getName(),us.getAddress(),us.getEmail(),us.getId());
        setAddress(user.getAddress());
        setEmail(user.getEmail());
        setName(user.getName());
        return "yoho";
    }
    
   
}
