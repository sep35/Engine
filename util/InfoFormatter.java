package util;

public class InfoFormatter {
    public static String formatActiveAttribute(String name, String rec, Double val){
        String s = ("   > "+name+":\n     Affects "+
                rec+"\n     by: "+val+"\n");
        return s;     
    }
    public static String formatPassiveAttribute(String name, Double val){
        String s = ("   > "+name+":\n     has value "+val+"\n");
        return s;
    }
    public static String formatDescriptor(String name, String val){
        String s = (" - "+name+": "+val+"\n");
        return s;
    }
}
