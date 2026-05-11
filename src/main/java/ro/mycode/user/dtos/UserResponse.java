package ro.mycode.user.dtos;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
