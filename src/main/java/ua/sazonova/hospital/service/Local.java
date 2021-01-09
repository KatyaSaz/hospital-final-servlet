package ua.sazonova.hospital.service;

import ua.sazonova.hospital.constants.Const;

import javax.servlet.http.HttpServletRequest;

public class Local {

    public static String getLanguage(HttpServletRequest req){
        String lang = req.getParameter(Const.SESSION_LOCALE);
       return (lang==null)? Const.EN:lang;
    }
}
