/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeTest;

import entity.Users;
import facades.UserFacade;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pernille
 */
public class UserFacadeUnitTest
{
    private UserFacade f;
    private Users u;
    
    public UserFacadeUnitTest()
    {
    }
    
    @Before
    public void setUp()
    {
        f = new UserFacade();
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void saveUser()
    {
         u = new Users();
        u.setUserName("UnitTest");
        u.setPassword("test");
        Users ru = f.saveUser(u);
        assertEquals(u.getUserName(), ru.getUserName());
    }
    
    @Test
    public void deleteUser()
    {
        f.deleteUser(u.getUserName());
    }
    
    @Test
    public void getUsers()
    {
        List<Users> u = f.getAllUsers();
        
        System.out.println("LAD OS VÆRE I FRED!!!!!");
    }
    
}
