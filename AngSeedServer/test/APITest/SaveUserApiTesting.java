package APITest;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.ValidatableResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import static org.hamcrest.Matchers.equalTo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.ApplicationConfig;

public class SaveUserApiTesting
{

    static Server server;

    public SaveUserApiTesting()
    {
        baseURI = "http://localhost:8082";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        server = new Server(8082);
        ServletHolder servletHolder = new ServletHolder(org.glassfish.jersey.servlet.ServletContainer.class);
        servletHolder.setInitParameter("javax.ws.rs.Application", ApplicationConfig.class.getName());
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/api/*");
        server.setHandler(contextHandler);
        server.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        server.stop();
        //waiting for all the server threads to terminate so we can exit gracefully
        server.join();
    }

    @Test
    public void getAllUsers()
    {
        String myJson = "{username : test, password :test}";
        ValidatableResponse json = 
        given().
                contentType("application/json").
                body(myJson).
                when().
                post("/saveUser").
                then().
                statusCode(200).
                body("username", equalTo("test"));
    }    
} 
    
