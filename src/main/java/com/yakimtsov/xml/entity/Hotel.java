package com.yakimtsov.xml.entity;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hotel", propOrder = {
    "meal",
    "apartmentType",
    "apartmentSize",
    "email"
})
public class Hotel {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Meal meal;
    @XmlElement(name = "apartment-type", required = true)
    @XmlSchemaType(name = "string")
    protected ApartmentType apartmentType;
    @XmlElement(name = "apartment-size", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected Integer apartmentSize;
    @XmlElement(required = true)
    protected String email;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "rate")
    protected Integer rate;

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal value) {
        this.meal = value;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType value) {
        this.apartmentType = value;
    }

    public Integer getApartmentSize() {
        return apartmentSize;
    }

    public void setApartmentSize(Integer value) {
        this.apartmentSize = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Integer getRate() {
        if (rate == null) {
            return 1;
        } else {
            return rate;
        }
    }

    public void setRate(Integer value) {
        this.rate = value;
    }

}
