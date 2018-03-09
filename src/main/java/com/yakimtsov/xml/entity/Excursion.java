
package com.yakimtsov.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Excursion", propOrder = {
    "excursionLanguage"
})
public class Excursion
    extends Voucher
{

    @XmlElement(name = "excursion-language", required = true)
    protected String excursionLanguage;

    public String getExcursionLanguage() {
        return excursionLanguage;
    }

    public void setExcursionLanguage(String value) {
        this.excursionLanguage = value;
    }

    @Override
    public String toString() {
        return "Excursion: " + super.toString() + " " + excursionLanguage;
    }
}
