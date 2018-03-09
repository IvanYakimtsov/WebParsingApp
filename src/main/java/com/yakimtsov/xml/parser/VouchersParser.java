package com.yakimtsov.xml.parser;

import com.yakimtsov.xml.entity.Voucher;
import com.yakimtsov.xml.exeption.ParseException;

import java.io.File;
import java.util.ArrayList;

public interface VouchersParser {
    ArrayList<Voucher> parse(File file) throws ParseException;
}
