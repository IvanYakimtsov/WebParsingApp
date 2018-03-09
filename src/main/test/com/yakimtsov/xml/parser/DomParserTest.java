package com.yakimtsov.xml.parser;

import com.yakimtsov.xml.exeption.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.FileAssert.fail;

public class DomParserTest {

    File validXml;
    File invalidXml;
    VouchersParser parser;

    @BeforeTest
    public void setUp(){
     validXml = new File("data/vouchers.xml");
     invalidXml = new File("data/invalid.xml");
     parser = new VouchersDomParser();
    }


    @Test
    public void parsePositive(){
        try {
            parser.parse(validXml);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
}
