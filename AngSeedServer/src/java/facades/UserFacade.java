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

    public Users saveUser(Users user) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        EntityManager em = getEntityManager();
        String hashedPassword = PasswordHash.createHash(user.getPassword());
        user.setPassword(hashedPassword);
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

    public List<String> authenticateUser(String userName, String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        Users user = getUserByUserId(userName);
        return user != null && PasswordHash.validatePassword(password, user.getPassword()) ? user.getRoles() :null;
    }

}
