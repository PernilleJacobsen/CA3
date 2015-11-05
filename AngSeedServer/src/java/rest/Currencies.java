package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Currency;
import entity.Users;
import facades.CurrencyFacade;
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

@Path("currency/")
@RolesAllowed("User")
public class Currencies
{
    Gson gson = new Gson();
    CurrencyFacade cf = new CurrencyFacade();
    
    @GET
    @Path("dailyrates")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDailyRates()
    {
        List<Currency> currencyList = cf.getAllCurrencies();
        JsonArray list = new JsonArray();
        for (Currency c : currencyList)
        {
            JsonObject currency = new JsonObject();
            currency.addProperty("code", c.getCode());
            currency.addProperty("date", c.getDates());
            currency.addProperty("desc", c.getDescription());
            currency.addProperty("rate", c.getRate());
//            currency.addProperty("id", c.getId());
            list.add(currency);
        }
        String json = gson.toJson(list);
        return json;
    }
    
    @GET
    @Path("calculator/{amount}/{fromcurrency}/{tocurrency}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDailyRates(@PathParam("amount") int amount, @PathParam("fromcurrency") String fromCurrency, @PathParam("tocurrency") String toCurrency)
    {
        Double result = cf.getRate(amount, fromCurrency, toCurrency);
        return result+"";
    }
}
