package com.yakimtsov.xml.command;

import com.yakimtsov.xml.exeption.ParseException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Command {
    void execute() throws IOException, ServletException, ParseException;
}
