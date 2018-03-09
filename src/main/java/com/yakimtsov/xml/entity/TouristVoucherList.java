
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
