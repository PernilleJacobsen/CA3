/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeTest;

import facades.CurrencyFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pernille
 */
public class CurrencyFacadeUnitTest
{
    private CurrencyFacade c;
    public CurrencyFacadeUnitTest()
    {
    }
    
    @Before
    public void setUp()
    {
        c = new CurrencyFacade();
    }
    
    @After
    public void tearDown()
    {
    }
    
    
    @Test
    public void calculatorMethodTest()
    {
        Double result  = c.getRate(100, "AUD", "EUR");
    }
}
