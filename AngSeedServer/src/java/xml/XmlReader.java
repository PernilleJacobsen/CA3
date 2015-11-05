/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

/**
 *
 * @author Pernille
 */
import entity.Currency;
import facades.CurrencyFacade;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlReader extends DefaultHandler
{

    private String tempDate;
    private List<Currency> currencyList = new ArrayList();

    public String getTempDate()
    {
        return tempDate;
    }

    public void setTempDate(String tempDate)
    {
        this.tempDate = tempDate;
    }

    @Override
    public void startDocument() throws SAXException
    {
        System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException
    {
        System.out.println("End Document (Sax-event)");
        CurrencyFacade f = new CurrencyFacade();
        f.saveCollectedCurrencyRates(currencyList);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        Currency c = new Currency();
        for (int i = 0; i < attributes.getLength(); i++)
        {
            String attribute = attributes.getLocalName(i);
            if (attribute.equals("id"))
            {
                setTempDate(attributes.getValue(i));
            }
            if (attribute.equals("code"))
            {
                c.setCode(attributes.getValue(i));
            }
            if (attribute.equals("desc"))
            {
                c.setDescription(attributes.getValue(i));
            }
            if (attribute.equals("rate"))
            {
                if (attributes.getValue(i).equals("-"))
                {
                    c.setRate(0);
                } else
                {
                    c.setRate(Double.parseDouble(attributes.getValue(i)));
                }
            }
            c.setDates(getTempDate());
        }
        currencyList.add(c);
    }

    public void getNewCurrencies()
    {
        try
        {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
