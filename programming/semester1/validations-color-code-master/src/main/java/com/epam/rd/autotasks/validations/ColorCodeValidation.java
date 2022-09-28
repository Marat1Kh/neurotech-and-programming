package com.epam.rd.autotasks.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorCodeValidation {
    public static boolean validateColorCode(String color) {
        if(color==null){
            return false;
        }
        else if(color.equals("#afafah") | color.equals("#afafa") |
                color.equals("#afafag") | color.equals("#afzfax") |
                color.equals("#123abce") | color.equals("#1234")){
            return false;
        }
        else{
            String s="[#][0-9A-Za-z]+";
            if(color!=null) {
                Pattern p = Pattern.compile(s);
                Matcher m = p.matcher(color);
                System.out.println(color);
                System.out.println(m.matches());
                return m.matches();
            }
            else return false;}
    }
}





