package com.yakimtsov.xml.command.impl;

import com.yakimtsov.xml.command.Command;
import com.yakimtsov.xml.entity.Voucher;
import com.yakimtsov.xml.exeption.ParseException;
import com.yakimtsov.xml.util.EmailUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class SendEmailCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ArrayList<Voucher> vouchers;

    public SendEmailCommand(HttpServletRequest request, HttpServletResponse response, ArrayList<Voucher> vouchers) {
        this.request = request;
        this.response = response;
        this.vouchers = vouchers;
    }

    @Override
    public void execute() throws IOException, ServletException, ParseException {
        Properties configProperties = new Properties();
        configProperties.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));
        String host = configProperties.getProperty("mail.smtp.host");
        String port = configProperties.getProperty("mail.smtp.port");
        String user = configProperties.getProperty("mail.user.name");
        String pass = configProperties.getProperty("mail.user.password");


        String to = request.getParameter("to");
        String subject = "result";
        StringBuilder body = new StringBuilder();
        vouchers.forEach(voucher -> body.append(voucher.toString() + "\n"));


        try {
            EmailUtil.sendEmail(host, port, user, pass, to, subject,
                    body.toString());
        } catch (Exception ex) {
            logger.catching(ex);
        }
    }
}
