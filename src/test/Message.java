package test;

import java.util.Date;

public class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

    public  Message(String asText){
        this.asText = asText;
        double parseDouble;
        try {
            parseDouble = Double.parseDouble(asText);
        } catch (NumberFormatException e) {
            parseDouble = Double.NaN;
        }
        this.asDouble = parseDouble;
        this.data = asText.getBytes();
        this.date = new Date();
    }

    public Message(double asDouble) {
        this(Double.toString(asDouble));
    }

    public Message(byte[] data) {
        this(new String(data));
    }


}
