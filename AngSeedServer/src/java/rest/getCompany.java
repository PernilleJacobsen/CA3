package rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import java.net.URL;
import java.util.Scanner;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("getCompany")
@RolesAllowed("User")
public class getCompany {
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{options}/{searchText}/{country}")
  public String getSomething(@PathParam("options") String options, @PathParam("searchText") String searchText, @PathParam("country") String country) throws MalformedURLException, IOException{
      String urlToUse = "http://cvrapi.dk/api?" + options + "=" + searchText + "&country=" + country;
      System.out.println(urlToUse);
      URL url = new URL(urlToUse);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();

        return jsonStr;
  }
 
}