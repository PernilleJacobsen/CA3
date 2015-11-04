/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sletMigSener;

import entity.Users;
import facades.UserFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jeanette
 */
public class sletMigSener
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        
        UserFacade f = new UserFacade();
        Users u = new Users("user", "test");
        u.AddRole("User");
        f.saveUser(u);
        
        Users a = new Users("admin","test");
        a.AddRole("Admin");
        f.saveUser(a);
    }
}
