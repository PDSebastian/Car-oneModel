package ro.mycode.user.dtos;


public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password

) {
}
