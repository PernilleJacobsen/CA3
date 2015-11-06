package APITest;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.parsing.Parser;
import static com.jayway.restassured.path.json.JsonPath.from;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rest.ApplicationConfig;
import static APITest.BackendTest.server;

public class CurrencyApiTesting
{

    static Server server;

    public CurrencyApiTesting()
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
    public void getDailyRates()
    {
        String json = given().
                contentType("application/json").
                body("{'username':'user','password':'test'}").
                when().
                post("/login").
                then().
                statusCode(200).extract().asString();
        //Then test /demouser URL with the correct token extracted from the JSON string.
        given().
                contentType("application/json").
                header("Authorization", "Bearer " + from(json).get("token")).
                when().
                get("/currency/dailyrates").
                then().
                statusCode(200).
                body("code[0]", equalTo("AUD"));
    }    
} 
    
