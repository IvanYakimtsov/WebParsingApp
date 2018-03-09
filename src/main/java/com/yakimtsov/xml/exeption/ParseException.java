package com.yakimtsov.xml.exeption;

/**
 * Created by Ivan on 21.01.2018.
 */
public class ParseException extends Exception {
    public ParseException(String message){
        super(message);
    }

    public ParseException(){
    }

    public ParseException(Throwable th){
        super(th);
    }

    public ParseException(String m, Throwable th){
        super(m,th);
    }
}
