package dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static String createdAtGenerator(){
        LocalDateTime now = LocalDateTime.now();
        String createdAt = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return createdAt;
    }
}
