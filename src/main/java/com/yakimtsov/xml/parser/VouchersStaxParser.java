package com.yakimtsov.xml.parser;

import com.yakimtsov.xml.entity.*;
import com.yakimtsov.xml.exeption.ParseException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VouchersStaxParser implements VouchersParser {

    private XMLInputFactory inputFactory;

    public VouchersStaxParser() {
        inputFactory = XMLInputFactory.newInstance();
    }


    @Override
    public ArrayList<Voucher> parse(File file) throws ParseException {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        FileInputStream inputStream;
        XMLStreamReader reader;
        String tagName;

        try {
            inputStream = new FileInputStream(file);
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    tagName = reader.getLocalName();

                    if (XMLElement.EXCURSION.toString().toLowerCase().equals(tagName)
                            || XMLElement.JOURNEY.toString().toLowerCase().equals(tagName)) {
                        Voucher voucher = buildVoucher(reader, tagName);
                        vouchers.add(voucher);
                    }


                }

            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ParseException(e);
        }
        return vouchers;
    }

    private Voucher buildVoucher(XMLStreamReader reader, String voucherType) throws XMLStreamException, ParseException {
        Voucher voucher;
        if (XMLElement.JOURNEY.toString().toLowerCase().equals(voucherType)) {
            voucher = new Journey();
        } else {
            voucher = new Excursion();
        }
        String transport = reader.getAttributeValue(null,
                XMLElement.TRANSPORT.toString().toLowerCase());
        voucher.setTransport(transport);
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase().replace('-', '_');
                if (XMLElement.contains(name)) {
                    XMLElement element = XMLElement.valueOf(name);
                    switch (element) {
                        case ID:
                            voucher.setId(getXMLText(reader));
                            break;
                        case COUNTRY:
                            voucher.setCountry(getXMLText(reader));
                            break;
                        case DAYS_NUMBER:
                            voucher.setDaysNumber(Integer.valueOf(getXMLText(reader)));
                            break;
                        case COST:
                            voucher.setCost(Integer.valueOf(getXMLText(reader)));
                            break;
                        case EXCURSION_LANGUAGE:
                            if (voucher instanceof Excursion) {
                                ((Excursion) voucher).setExcursionLanguage(getXMLText(reader));
                            } else {
                                throw new ParseException("invalid tag in " + voucherType + " tag");
                            }

                            break;
                        case HOTEL:
                            Hotel hotel = buildHotel(reader);
                            if (voucher instanceof Journey) {
                                ((Journey) voucher).setHotel(hotel);
                            } else {
                                throw new ParseException("invalid tag in " + voucherType + " tag");
                            }

                            break;
                    }
                }

            }
            if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if (XMLElement.JOURNEY.toString().toLowerCase().equals(name)
                        || XMLElement.EXCURSION.toString().toLowerCase().equals(name)) {
                    return voucher;
                }
            }
        }

        throw new ParseException("Unknown element in " + voucherType + " tag");
    }

    private Hotel buildHotel(XMLStreamReader reader) throws XMLStreamException {
        Hotel hotel = new Hotel();
        String hotelName = reader.getAttributeValue(null, XMLElement.NAME.toString().toLowerCase());
        String hotelRate = reader.getAttributeValue(null, XMLElement.RATE.toString().toLowerCase());
        hotel.setName(hotelName);
        hotel.setRate(Integer.valueOf(hotelRate));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName().toUpperCase().replace('-', '_');
                if (XMLElement.contains(name)) {
                    XMLElement element = XMLElement.valueOf(name);
                    switch (element) {
                        case MEAL:
                            hotel.setMeal(Meal.fromValue(getXMLText(reader)));
                            break;
                        case APARTMENT_TYPE:
                            hotel.setApartmentType(ApartmentType.fromValue(getXMLText(reader)));
                            break;
                        case APARTMENT_SIZE:
                            hotel.setApartmentSize(Integer.valueOf(getXMLText(reader)));
                            break;
                        case EMAIL:
                            hotel.setEmail(getXMLText(reader));
                            break;
                    }
                }
            }
            if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if ("hotel".equals(name)) {
                    return hotel;
                }
            }
        }
        return hotel;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
