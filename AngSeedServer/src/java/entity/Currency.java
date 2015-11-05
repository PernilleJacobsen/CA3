/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Pernille
 */
@Entity
public class Currency implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String dates;
    private String code;
    private String description;
    private double rate;

    public Currency()
    {
    }

    public String getDates()
    {
        return dates;
    }

    public void setDates(String dates)
    {
        this.dates = dates;
    }
    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "entity.Currency[ id=" + id + " ]";
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getRate()
    {
        return rate;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }

}
