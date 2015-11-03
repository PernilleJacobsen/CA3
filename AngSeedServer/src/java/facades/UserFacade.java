package facades;

import entity.Users;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserFacade
{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
//  private final  Map<String, Users> users = new HashMap<>();

    public UserFacade()
    {
        //Test Users
//    Users user = new Users("user","test");
//    user.AddRole("Users");
//    users.put(user.getUserName(),user );
//    Users admin = new Users("admin","test");
//    admin.AddRole("Admin");
//    users.put(admin.getUserName(),admin);
//    
//    Users both = new Users("user_admin","test");
//    both.AddRole("Users");
//    both.AddRole("Admin");
//    users.put(both.getUserName(),both );
    }

    EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public Users saveUser(Users user)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return em.find(Users.class, user.getUserName());
        } finally
        {
            em.close();
        }
    }

    public Users getUserByUserId(String userName)
    {
        EntityManager em = getEntityManager();
        return em.find(Users.class, userName);
    }
    /*
     Return the Roles if users could be authenticated, otherwise null
     */

    public List<String> authenticateUser(String userName, String password)
    {
        Users user = getUserByUserId(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

}
