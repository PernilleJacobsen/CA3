package facades;

import deploy.DeploymentConfiguration;
import entity.Users;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.PasswordHash;

public class UserFacade
{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

//  private final  Map<String, Users> users = new HashMap<>();

    public UserFacade()
    {
        
    }

    EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public Users saveUser(Users user)
    {
        EntityManager em = getEntityManager();
//        String hashedPassword = PasswordHash.createHash(user.getPassword());
//        user.setPassword(hashedPassword);
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
//        return user != null && PasswordHash.validatePassword(password, user.getPassword()) ? user.getRoles() :null;
    }
    
    public List<Users> getAllUsers()
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            List<Users> users= em.createQuery("SELECT u FROM Users u").getResultList();
            em.getTransaction().commit();
            return users;
            
        }finally
        {
            em.close();
        } 
    }
    
    public void deleteUser(String username)
    {
        EntityManager em = getEntityManager();
        try
        {
            
            em.getTransaction().begin();
            Users user = em.find(Users.class, username);
            em.remove(user);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

}
