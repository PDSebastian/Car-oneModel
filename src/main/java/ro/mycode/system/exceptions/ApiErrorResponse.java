package ro.mycode.system.exceptions;

import lombok.Builder;


import java.time.Instant;
import java.time.LocalDateTime;

@Builder
public record ApiErrorResponse(
        LocalDateTime dateTime,
        String message,
        int status,
        String hint

) {

    public static ApiErrorResponse of(int status, String error, String message, String hint) {
        return new ApiErrorResponse(LocalDateTime.now(),message,status,hint);
    }
}
