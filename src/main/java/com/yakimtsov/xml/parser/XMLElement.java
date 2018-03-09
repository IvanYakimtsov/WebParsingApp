package com.yakimtsov.xml.parser;

public enum XMLElement {
    JOURNEY, EXCURSION, TRANSPORT, NAME, RATE, HOTEL, ID, COUNTRY, DAYS_NUMBER, COST, MEAL, APARTMENT_TYPE,
    APARTMENT_SIZE, EMAIL, EXCURSION_LANGUAGE;

    public static boolean contains(String test) {
        for (XMLElement element : XMLElement.values()) {
            if (element.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
