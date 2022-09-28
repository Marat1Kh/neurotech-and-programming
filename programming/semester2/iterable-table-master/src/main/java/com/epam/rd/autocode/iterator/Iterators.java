package com.epam.rd.autocode.iterator;

import java.util.ArrayList;
import java.util.List;

class Iterators {

    public static Iterable<String> table(String[] columns, int[] rows){
        String s = "";
    List<String> cells= new ArrayList<String>();
        for(String column : columns){

            for(int row: rows){
                s=(String) column + Integer.toString(row);
                cells.add(s);
                        s="";
            }

        }
        return cells;
    }

}

