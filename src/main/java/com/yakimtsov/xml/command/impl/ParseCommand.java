package com.yakimtsov.xml.command.impl;

import com.yakimtsov.xml.command.Command;
import com.yakimtsov.xml.entity.Excursion;
import com.yakimtsov.xml.entity.Journey;
import com.yakimtsov.xml.entity.TouristVoucherList;
import com.yakimtsov.xml.entity.Voucher;
import com.yakimtsov.xml.exeption.ParseException;
import com.yakimtsov.xml.parser.VouchersDomParser;
import com.yakimtsov.xml.parser.VouchersParser;
import com.yakimtsov.xml.parser.VouchersSaxParser;
import com.yakimtsov.xml.parser.VouchersStaxParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ParseCommand implements Command {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private File file;
    private VouchersParser parser;

    private final String JAXB_TYPE = "JAXB";
    private final String STAX_TYPE = "STAX";
    private final String SAX_TYPE = "SAX";
    private final String DOM_TYPE = "DOM";

    public ParseCommand(HttpServletRequest request, HttpServletResponse response, File file) {
        this.request = request;
        this.response = response;
        this.file = file;
    }

    @Override
    public void execute() throws IOException, ServletException, ParseException {
        String parserType = request.getParameter("parserType");
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        ResourceBundle rb = ResourceBundle.getBundle("elements", locale);
        if (parserType != null) {
            ArrayList<Voucher> vouchers;

            if (JAXB_TYPE.equals(parserType)) {

                try {
                    JAXBContext jc = JAXBContext.newInstance(TouristVoucherList.class);
                    Unmarshaller u = jc.createUnmarshaller();
                    FileReader reader = new FileReader(file.getAbsolutePath());
                    TouristVoucherList touristVoucherList = (TouristVoucherList) u.unmarshal(reader);
                    vouchers = new ArrayList<>();
                    touristVoucherList.getVoucher().forEach(v -> vouchers.add(v.getValue()));
                } catch (JAXBException e) {
                    throw new ParseException(e);
                }

            } else {
                switch (parserType) {
                    case DOM_TYPE:
                        parser = new VouchersDomParser();
                        break;
                    case SAX_TYPE:
                        parser = new VouchersSaxParser();
                        break;
                    case STAX_TYPE:
                        parser = new VouchersStaxParser();
                        break;
                    default:
                        parser = new VouchersDomParser();
                }
                vouchers = parser.parse(file);
            }
            String parseMessage = rb.getString("parseMessage");
            request.setAttribute("list",vouchers);
            request.setAttribute("parseMessage", parseMessage + parserType);
        }

    }
}
