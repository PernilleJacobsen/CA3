/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import xml.XmlReader;

public class CurrencyRunner implements Runnable {
    
    
    public XmlReader xread = new XmlReader();
    
    public CurrencyRunner(){
        
    }

    public CurrencyRunner(XmlReader xread) {
        this.xread = xread;
    }
    
    @Override
    public void run() {
        xread.getNewCurrencies();
    }

}
