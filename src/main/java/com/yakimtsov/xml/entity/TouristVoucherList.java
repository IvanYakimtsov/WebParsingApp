//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 
//         See <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.03.07 at 07:48:41 PM MSK 
//


package com.yakimtsov.xml.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "voucher"
})
@XmlRootElement(name = "tourist-vouchers")
public class TouristVoucherList {

    @XmlElementRef(name = "voucher", namespace = "http://www.example.com/vouchers", type = JAXBElement.class)
    protected List<JAXBElement<? extends Voucher>> voucher;

    public List<JAXBElement<? extends Voucher>> getVoucher() {
        if (voucher == null) {
            voucher = new ArrayList<JAXBElement<? extends Voucher>>();
        }
        return this.voucher;
    }

}