package com.yakimtsov.xml.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "Apartment-Type")
@XmlEnum
public enum ApartmentType {

    SIMPLE,
    BUSINESS,
    LUXE;

    public String value() {
        return name();
    }

    public static ApartmentType fromValue(String v) {
        return valueOf(v);
    }

}
