/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.Currency;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Pernille
 */
public class CurrencyFacade
{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

    EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public CurrencyFacade()
    {
    }

    public void saveCollectedCurrencyRates(List<Currency> currencyList)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            for (Currency c : currencyList)
            {
                if (c.getCode() != null)
                {
                    em.persist(c);
                }
            }
            em.getTransaction().commit();

        } finally
        {
            em.close();
        }
    }

    public List<Currency> getAllCurrencies()
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            List<Currency> currencyList = em.createQuery("Select c from Currency c").getResultList();
            em.getTransaction().commit();
            return currencyList;
        } finally
        {
            em.close();
        }
    }

    public Double getRate(int amount, String fromCurrency, String toCurrency)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            double dbFromCurrency = (double) em.createQuery("Select c.rate From Currency c Where c.code=:code Order by c.id DESC").setMaxResults(1).setParameter("code", fromCurrency).getSingleResult();
            double dbToCurrency = (double) em.createQuery("Select c.rate From Currency c Where c.code=:code Order by c.id DESC").setMaxResults(1).setParameter("code", toCurrency).getSingleResult();
            em.getTransaction().commit();
            double result = (amount * dbFromCurrency) / dbToCurrency;
            return result;
        }
        finally
        {
            em.close();
        }
    }

}
