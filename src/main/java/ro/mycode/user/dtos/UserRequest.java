package ro.mycode.user.dtos;

import lombok.AllArgsConstructor;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password

) {
}
