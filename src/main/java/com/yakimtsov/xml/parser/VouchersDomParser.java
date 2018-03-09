package com.yakimtsov.xml.parser;

import com.yakimtsov.xml.entity.*;
import com.yakimtsov.xml.exeption.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VouchersDomParser implements VouchersParser {
    private final int ID_NUMBER = 1;
    private final int COUNTRY_NUMBER = 3;
    private final int DAYS_NUMBER = 5;
    private final int COST_NUMBER = 7;
    private final int HOTEL_NUMBER = 9;
    private final int EXCURSION_LANGUAGE_NUMBER = 9;
    private final int HOTEL_MEAL_NUMBER = 1;
    private final int HOTEL_APARTMENT_TYPE_NUMBER = 3;
    private final int HOTEL_APARTMENT_SIZE_NUMBER = 5;
    private final int HOTEL_EMAIL_NUMBER = 7;

    @Override
    public ArrayList<Voucher> parse(File file) throws ParseException {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder;
        try {
            builder = f.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParseException(e);
        }
        Document doc;
        try {
            doc = builder.parse(file);
        } catch (SAXException | IOException e) {
            throw new ParseException(e);
        }
        NodeList journeyList = doc.getElementsByTagName(XMLElement.JOURNEY.toString().toLowerCase());
        NodeList excursionList = doc.getElementsByTagName(XMLElement.EXCURSION.toString().toLowerCase());
        for (int index = 0; index < journeyList.getLength(); index++) {
            vouchers.add(buildJourney(journeyList.item(index)));
        }

        for (int index = 0; index < excursionList.getLength(); index++) {
            vouchers.add(buildExcursion(excursionList.item(index)));
        }

        return vouchers;
    }

    private Excursion buildExcursion(Node node) {
        Excursion excursion = new Excursion();
        NodeList excursionNodes = node.getChildNodes();
        buildVoucher(excursion, node);
        excursion.setExcursionLanguage(excursionNodes.item(EXCURSION_LANGUAGE_NUMBER).getTextContent());
        return excursion;
    }


    private Journey buildJourney(Node node) {
        Journey journey = new Journey();
        NodeList journeyNodes = node.getChildNodes();
        buildVoucher(journey, node);
        journey.setHotel(buildHotel(journeyNodes.item(HOTEL_NUMBER)));
        return journey;
    }

    private void buildVoucher(Voucher voucher, Node node) {
        NodeList journeyNodes = node.getChildNodes();
        voucher.setTransport(((Element) node).getAttribute(XMLElement.TRANSPORT.toString().toLowerCase()));
        voucher.setId(journeyNodes.item(ID_NUMBER).getTextContent());
        voucher.setCountry(journeyNodes.item(COUNTRY_NUMBER).getTextContent());
        voucher.setDaysNumber(Integer.valueOf(journeyNodes.item(DAYS_NUMBER).getTextContent()));
        voucher.setCost(Integer.valueOf(journeyNodes.item(COST_NUMBER).getTextContent()));
    }

    private Hotel buildHotel(Node hotelNode) {
        Hotel hotel = new Hotel();
        NodeList hotelNodes = hotelNode.getChildNodes();
        hotel.setName(((Element) hotelNode).getAttribute(XMLElement.NAME.toString().toLowerCase()));
        hotel.setRate(Integer.valueOf(((Element) hotelNode)
                .getAttribute(XMLElement.RATE.toString().toLowerCase())));
        hotel.setMeal(Meal.fromValue(hotelNodes.item(HOTEL_MEAL_NUMBER).getTextContent()));
        hotel.setApartmentType(ApartmentType.fromValue(hotelNodes.item(HOTEL_APARTMENT_TYPE_NUMBER).getTextContent()));
        hotel.setApartmentSize(Integer.valueOf(hotelNodes.item(HOTEL_APARTMENT_SIZE_NUMBER).getTextContent()));
        hotel.setEmail(hotelNodes.item(HOTEL_EMAIL_NUMBER).getTextContent());

        return hotel;
    }
}
