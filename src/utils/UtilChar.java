package utils;

public class UtilChar {
    public static char[] toChar(String s){
        //char ss[] = String.valueOf(s).replaceAll("\\s*","").toUpperCase().toCharArray();
        char ss[] = String.valueOf(s).replaceAll("[^a-zA-Z]","").toUpperCase().toCharArray();
        return ss;
    }
}
