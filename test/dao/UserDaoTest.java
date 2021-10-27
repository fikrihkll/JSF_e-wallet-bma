/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojo.TblLog;
import pojo.TblUser;
import pojo.TblWallet;
import presenter.Singleton;

/**
 *
 * @author zackm
 */
public class UserDaoTest {
    
    public UserDaoTest() {
    }
    
    UserDao instance = new UserDao();
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class UserDao.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "M@Mail.com";
        String pass = "cibaduyut";
        
        int expResult = 1;
        int result = instance.login(email, pass).size();
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testLoginFailed() {
        System.out.println("login");
        String email = "M@Mail.com";
        String pass = "33333";
        
        int expResult = 0;
        int result = instance.login(email, pass).size();
        assertEquals(expResult, result);   
    }

    /**
     * Test of getById method, of class UserDao.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        TblUser expResult = new TblUser(1,"Michael Artiti","M@Mail.com","Old Trafford St.12","cibaduyut");
        TblUser result = instance.getById(1);
        
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getAddress(), result.getAddress());
        assertEquals(expResult.getEmail(), result.getEmail());
        assertEquals(expResult.getId(), result.getId());
        
    }
    
    @Test
    public void testGetByIdFailed() {
        System.out.println("getById");
        
        TblUser result = instance.getById(1111);
        
        assertEquals(null, result);
        
    }

    

    /**
     * Test of updateUser method, of class UserDao.
     */
//    @Test
//    public void testUpdateUser() {
//        System.out.println("updateUser");
//        TblUser user = null;
//        UserDao instance = new UserDao();
//        instance.updateUser(user);
//    }

    

    /**
     * Test of register method, of class UserDao.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        String name = "Anotnio Conte";
        String email = "aconte4@mail.com";
        String pass = "123";
        String address = "Streetford End St.2";
        UserDao instance = new UserDao();
        boolean expResult = true;
        boolean result = instance.register(name, email, pass, address);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void testRegisterFailed() {
        System.out.println("register");
        String name = "Anotnio Conte";
        String email = "aconte2@mail.com";
        String pass = "123";
        String address = "Streetford End St.2";
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.register(name, email, pass, address);
        assertEquals(expResult, result);
        
    }
    
}
