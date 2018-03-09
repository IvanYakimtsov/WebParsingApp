package com.yakimtsov.xml.parser;

import com.yakimtsov.xml.entity.*;
import com.yakimtsov.xml.exeption.ParseException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VouchersSaxParser extends DefaultHandler implements VouchersParser {
    private SAXParser saxParser;
    private XMLElement currentTag;
    private ArrayList<Voucher> vouchers = new ArrayList<>();
    private Voucher currentVoucher;
    private Hotel currentHotel;

    public VouchersSaxParser() throws ParseException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public ArrayList<Voucher> parse(File file) throws ParseException {
        vouchers = new ArrayList<>();
        try {
            saxParser.parse(file.getAbsolutePath(), this);
        } catch (SAXException | IOException e) {
            throw new ParseException(e);
        }

        return vouchers;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String tagName = qName.toUpperCase().replace('-', '_');
        if (XMLElement.contains(tagName)) {
            currentTag = XMLElement.valueOf(tagName);
            switch (currentTag) {
                case JOURNEY:
                    currentVoucher = new Journey();
                    currentVoucher.setTransport(attributes.getValue(0));
                    vouchers.add(currentVoucher);
                    break;
                case EXCURSION:
                    currentVoucher = new Excursion();
                    currentVoucher.setTransport(attributes.getValue(0));
                    vouchers.add(currentVoucher);
                    break;

                case HOTEL:
                    currentHotel = new Hotel();
                    ((Journey) currentVoucher).setHotel(currentHotel);
                    String firstAttr = attributes.getValue(0);
                    String secondAttr = attributes.getValue(1);
                    String attrName = XMLElement.NAME.toString().toLowerCase();
                    if (attrName.equals(attributes.getQName(0))) {
                        currentHotel.setName(firstAttr);
                        currentHotel.setRate(Integer.valueOf(secondAttr));
                    } else {
                        currentHotel.setRate(Integer.valueOf(firstAttr));
                        currentHotel.setName(secondAttr);
                    }
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (currentTag != null) {
            switch (currentTag) {
                case ID:
                    currentVoucher.setId(new String(ch, start, length));
                    break;
                case COUNTRY:
                    currentVoucher.setCountry(new String(ch, start, length));
                    break;
                case DAYS_NUMBER:
                    currentVoucher.setDaysNumber(Integer.valueOf(new String(ch, start, length)));
                    break;
                case COST:
                    currentVoucher.setCost(Integer.valueOf(new String(ch, start, length)));
                    break;
                case MEAL:
                    currentHotel.setMeal(Meal.fromValue(new String(ch, start, length)));
                    break;
                case APARTMENT_TYPE:
                    currentHotel.setApartmentType(ApartmentType.fromValue(new String(ch, start, length)));
                    break;
                case APARTMENT_SIZE:
                    currentHotel.setApartmentSize(Integer.valueOf(new String(ch, start, length)));
                    break;
                case EMAIL:
                    currentHotel.setEmail(new String(ch, start, length));
                    break;
                case EXCURSION_LANGUAGE:
                    ((Excursion) currentVoucher).setExcursionLanguage(new String(ch, start, length));
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        currentTag = null;
    }

}
