package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    LocalDateTime time;
    public DateTime(){
        time = LocalDateTime.now(); // initialize with current date and time
    }
    public String getString(int format){
        String pattern = "dd/MM/yyyy HH:mm:ss"; // default
        if(format == 1){
            pattern = "dd MMMM, yyyy h:mm a";
        } else if (format == 2) {
            pattern = "dd/MM/yyyy HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }
}
