package ro.mycode.system.exceptions;

import lombok.Builder;


import java.time.LocalDateTime;

@Builder
public record ApiErrorResponse(
        LocalDateTime dateTime,
        String message,
        int status,
        String hint

) {
}
