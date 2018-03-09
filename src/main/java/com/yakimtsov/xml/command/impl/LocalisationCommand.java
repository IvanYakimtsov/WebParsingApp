package com.yakimtsov.xml.command.impl;

import com.yakimtsov.xml.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalisationCommand implements Command{
    private HttpServletRequest request;
    private HttpServletResponse response;

    public LocalisationCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws IOException, ServletException {
        String locale = request.getParameter("localeName");
        String language = locale.substring(0,2);
        String country = locale.substring(3, locale.length());
        Locale loc = new Locale(language, country);
        request.getSession().setAttribute("locale", loc);
        ResourceBundle rb = ResourceBundle.getBundle("elements", loc);
        String pageTitle = rb.getString("pageTitle");
        String languageElement = rb.getString("language");
        String submitElement = rb.getString("submit");
        String fileUpload = rb.getString("fileUpload");
        String selectFile = rb.getString("selectFile");
        String parseFile = rb.getString("parseFile");
        request.setAttribute("locale", loc);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("language", languageElement);
        request.setAttribute("submit", submitElement);
        request.setAttribute("fileUpload", fileUpload);
        request.setAttribute("selectFile", selectFile);
        request.setAttribute("parseFile", parseFile);
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
