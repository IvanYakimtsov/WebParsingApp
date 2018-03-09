
package com.yakimtsov.xml.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Voucher", propOrder = {
    "id",
    "country",
    "daysNumber",
    "cost"
})
@XmlSeeAlso({
    Journey.class,
    Excursion.class
})
public abstract class Voucher {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String id;
    @XmlElement(required = true)
    private String country;
    @XmlElement(name = "days-number", required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer daysNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer cost;
    @XmlAttribute(name = "transport")
    private String transport;


    public String getId() {
        return id;
    }


    public void setId(String value) {
        this.id = value;
    }


    public Object getCountry() {
        return country;
    }


    public void setCountry(String value) {
        this.country = value;
    }


    public Integer getDaysNumber() {
        return daysNumber;
    }


    public void setDaysNumber(Integer value) {
        this.daysNumber = value;
    }


    public Integer getCost() {
        return cost;
    }


    public void setCost(Integer value) {
        this.cost = value;
    }


    public String getTransport() {
        return transport;
    }

    public void setTransport(String value) {
        this.transport = value;
    }

}
