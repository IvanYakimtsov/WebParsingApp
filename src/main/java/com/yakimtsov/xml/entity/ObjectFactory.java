package com.yakimtsov.xml.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _Voucher_QNAME = new QName("http://www.example.com/vouchers", "voucher");
    private final static QName _Journey_QNAME = new QName("http://www.example.com/vouchers", "journey");
    private final static QName _Excursion_QNAME = new QName("http://www.example.com/vouchers", "excursion");

    public ObjectFactory() {
    }

    public TouristVoucherList createTouristVouchers() {
        return new TouristVoucherList();
    }


    public Journey createJourney() {
        return new Journey();
    }


    public Excursion createExcursion() {
        return new Excursion();
    }


    public Hotel createHotel() {
        return new Hotel();
    }


    @XmlElementDecl(namespace = "http://www.example.com/vouchers", name = "voucher")
    public JAXBElement<Voucher> createVoucher(Voucher value) {
        return new JAXBElement<Voucher>(_Voucher_QNAME, Voucher.class, null, value);
    }


    @XmlElementDecl(namespace = "http://www.example.com/vouchers", name = "journey", substitutionHeadNamespace = "http://www.example.com/vouchers", substitutionHeadName = "voucher")
    public JAXBElement<Journey> createJourney(Journey value) {
        return new JAXBElement<Journey>(_Journey_QNAME, Journey.class, null, value);
    }


    @XmlElementDecl(namespace = "http://www.example.com/vouchers", name = "excursion", substitutionHeadNamespace = "http://www.example.com/vouchers", substitutionHeadName = "voucher")
    public JAXBElement<Excursion> createExcursion(Excursion value) {
        return new JAXBElement<Excursion>(_Excursion_QNAME, Excursion.class, null, value);
    }

}
