package com.yakimtsov.xml.servlet;


import com.yakimtsov.xml.command.impl.ParseCommand;
import com.yakimtsov.xml.exeption.ParseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/UploadFile")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 10 // 10 MB
)
public class UploadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UploadServlet.class);
    private static final String UPLOAD_DIR = "uploads";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        ResourceBundle rb = ResourceBundle.getBundle("resultPageElements", locale);
        String filename = null;
        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null) {
                String uploadMessage = rb.getString("uploadMessage");
                String back = rb.getString("back");
                String pageTitle = rb.getString("pageTitle");
                request.setAttribute("uploadMessage", uploadMessage);
                request.setAttribute("back", back);
                request.setAttribute("pageTitle", pageTitle);
                request.setAttribute("uploadFileName", part.getSubmittedFileName());
                if (!part.getSubmittedFileName().equals("")) {
                    part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
                    filename = uploadFilePath + File.separator + part.getSubmittedFileName();
                }

            }
        }

        parseFile(filename, request, response, rb);

        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/pages/result.jsp").forward(request, response);

    }

    private void parseFile(String filename,
                           HttpServletRequest request, HttpServletResponse response,
                           ResourceBundle rb) throws ServletException {
        if (filename != null) {
            ClassLoader classLoader = getClass().getClassLoader();
            File schemaFile = new File(classLoader.getResource("vouchers.xsd").getFile());
            File uploadFile = new File(filename);
            Source xmlFile = new StreamSource(uploadFile);
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            try {
                Schema schema = schemaFactory.newSchema(schemaFile);
                Validator validator = schema.newValidator();
                validator.validate(xmlFile);
                new ParseCommand(request, response, uploadFile).execute();
            } catch (SAXException e) {
                LOGGER.log(Level.ERROR, xmlFile.getSystemId() + " is NOT valid reason:" + e);
                String errorMessage = rb.getString("errorMessage");
                request.setAttribute("errorMessage", errorMessage + e.getMessage());
            } catch (IOException | ParseException e) {
                LOGGER.catching(e);
                String errorMessage = rb.getString("errorMessage");
                request.setAttribute("errorMessage", errorMessage + e.getMessage());
            }
        }
    }

}
