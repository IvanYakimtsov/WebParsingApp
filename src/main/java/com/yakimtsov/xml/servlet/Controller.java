package com.yakimtsov.xml.servlet;

import com.yakimtsov.xml.command.impl.LocalisationCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main_page")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new LocalisationCommand(request,response).execute();
    }
}