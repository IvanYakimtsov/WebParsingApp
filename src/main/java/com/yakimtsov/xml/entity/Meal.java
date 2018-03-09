
package com.yakimtsov.xml.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Meal")
@XmlEnum
public enum Meal {

    HB,
    BB,
    AL;

    public String value() {
        return name();
    }

    public static Meal fromValue(String v) {
        return valueOf(v);
    }

}
