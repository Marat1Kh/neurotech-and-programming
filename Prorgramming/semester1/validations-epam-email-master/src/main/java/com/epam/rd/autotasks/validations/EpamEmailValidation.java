package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpamEmailValidation {

    public static boolean validateEpamEmail(String email) {
        if(email==null){
            return false;
        }
        else{
            Pattern p=Pattern.compile("[a-z]+[_]+[a-z0-9]+@epam.com");
            Matcher m=p.matcher(email);
            return m.matches();
        }

    }
}





