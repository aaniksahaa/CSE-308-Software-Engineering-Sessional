package util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime implements Serializable {
    LocalDateTime time;
    public DateTime(){
        time = LocalDateTime.now(); // initialize with current date and time
    }
    public String toString(){
        String pattern = "h:mm a"; // default
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }

    public static void main(String[] args) {
        DateTime d = new DateTime();
        System.out.println(d);
    }
}
