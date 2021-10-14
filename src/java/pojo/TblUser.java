package pojo;
// Generated Oct 14, 2021 10:33:07 PM by Hibernate Tools 4.3.1


import dao.UserDao;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;

/**
 * TblUser generated by hbm2java
 */
@ManagedBean
public class TblUser  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String email;
     private String address;
     private String pass;
     private Set tblWallets = new HashSet(0);

    public TblUser() {
    }

    public TblUser(Integer id, String name, String email, String address, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.pass = pass;
    }

    
	
    public TblUser(String name, String email, String address, String pass) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.pass = pass;
    }
    public TblUser(String name, String email, String address, String pass, Set tblWallets) {
       this.name = name;
       this.email = email;
       this.address = address;
       this.pass = pass;
       this.tblWallets = tblWallets;
    }
    
    public void getUser(){
        UserDao dao = new UserDao();
        TblUser us = dao.getById();
        System.out.println(us.getId().toString());
        setId(us.getId());
        setPass(us.getPass());
        setName(us.getName());
        setAddress(us.getAddress());
        setEmail(us.getEmail());
        System.out.println(getEmail().toString());
    }
    
     public void updateUserData(){
        UserDao dao = new UserDao();
        System.out.println("IN - UPDATE"+getEmail());
        dao.updateUser(new TblUser(getId(),getName(),getEmail(),getAddress(),getPass()));
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    public Set getTblWallets() {
        return this.tblWallets;
    }
    
    public void setTblWallets(Set tblWallets) {
        this.tblWallets = tblWallets;
    }




}


