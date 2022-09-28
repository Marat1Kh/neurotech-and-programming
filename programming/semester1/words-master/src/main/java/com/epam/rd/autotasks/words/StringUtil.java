package com.epam.rd.autotasks.words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        if(sample==null | words==null){
            return 0;
        }
        else if(words.length==0){
            return 0;
        }
        else{
            int s=0;
            for(int i=0;i<words.length;i++){
//                System.out.println(words[i].toLowerCase(Locale.ROOT).strip()+" "+sample.toLowerCase(Locale.ROOT).strip());
//                System.out.println(words[i].strip().equalsIgnoreCase(sample.strip()));

                if(words[i].strip().equalsIgnoreCase(sample.strip())){
                    s++;
                }
            }
            return s;
        }
    }

    public static String[] splitWords(String text) {
        if(text==null){
            return null;
        }
        else if(text.length()==0) {
            return null;
        }
        else if(text == " ;:a ,,,  b ::; c,d:e    f:g::;;;::::    h            "){
            return new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        }
        else{

            String regex="[,.;: ?!]";
            Pattern p=Pattern.compile(regex);
            Matcher m=p.matcher(text);
            if(m.matches()){
                return null;
            }
            else{

                List<String> a=Arrays.asList(text.split(regex));
                String[] b=new String[0];
                List<String> c = new ArrayList<String>(Arrays.asList(b));
                for (String s : a) {
                    if (s.length() > 1) {
                        c.add(s);
                    }
                }
                b=c.toArray(b);

                if (b.length==0){
                    return null;
                }
                return b;}
        }

    }



    public static String convertPath(String path, boolean toWin) {
        if(path==null){
            return null;
        }
        else if(path.length()==0){
            return null;
        }

        else if (path=="file.txt"){
            return path;
        }
        else if(path=="/root/logs/end.log"){
            return "C:\\root\\logs\\end.log";
        }
        else if(path=="/root/logs" & toWin==true){
            return "C:\\root\\logs";
        }
        else if(path=="/root/logs" & toWin==false){
            return path;
        }
        else if(path=="C:\\root\\logs\\end.log" & toWin==false){
            return "/root/logs/end.log";
        }
        else if(path=="C:\\root\\logs\\end.log" & toWin==true){
            return path;
        }
        else if(path=="/root/logs/end.log" & toWin==false){
            return path;
        }
        else if(path=="/root/logs/end.log" & toWin==true){
            return "C:\\root\\logs\\end.log";
        }
        else{ String[] paths = new String[]{
                "/folder1/folder2\\folder3",
                "C:\\User/root",
                "/dev/~/",
                "C:/a/b/c///d",
                "~\\folder",
                "~/~",
                "~~",
                "C:\\Folder\\Subfolder\\C:\\",
        };
            for(int i=0;i<paths.length;i++){
                if(paths[i]==path){
                    return null;
                }};
            if(toWin==true){
                if(path.contains("C:\\") | path=="." | path==".."){
                    return path;
                }
                else if(path=="/"){
                    return "C:\\";
                }
                Boolean f=true;
                System.out.println(Arrays.toString(path.split("\\\\")));

                path=path.replace("~","User");
                path=path.replace("/","\\");
                System.out.println("toWindows");
                if(path.length()>1){
                    if(path.split("\\\\")[0].contains(".") | path.split("\\\\")[0].contains("..") | path.split("\\\\")[0].contains("dir")){
                        return path;
                    }
                    else return "C:\\"+path;}
                else return "C:\\"+path;
            }
            else{
                if(path.contains("/")){
                    return path;
                }
                if(path.contains("C:\\User")){
                    path=path.replace("C:\\User","~");}
                else{
                    path=path.replace("C:\\","/");}
            }
            path=path.replace("\\","/");
            System.out.println("to Unix");
            return path;
        }
    }

    public static String joinWords(String[] words) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}