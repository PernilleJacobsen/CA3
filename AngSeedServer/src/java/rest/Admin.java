package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Users;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import security.PasswordHash;

@Path("admin/")
@RolesAllowed("Admin")
public class Admin
{
    Gson gson = new Gson();
    UserFacade uf = new UserFacade();
    
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers()
    {
        List<Users> userList = uf.getAllUsers();
        JsonArray liste = new JsonArray();
        for (Users u : userList)
        {
            JsonObject user = new JsonObject();
            user.addProperty("username", u.getUserName());
            user.addProperty("password", u.getPassword());
            JsonArray JsonStringList = new JsonArray();
            JsonObject role = new JsonObject();
            List<String> StringList = u.getRoles();
            for (String s : StringList)
            {
                role.addProperty("role", s);
                JsonStringList.add(role);
            }
            user.add("roles", JsonStringList);
            liste.add(user);
        }
        String json = gson.toJson(liste);
        return json;
    }
    
    @DELETE
    @Path("user/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") String username)
    {
        uf.deleteUser(username);
    }

}
