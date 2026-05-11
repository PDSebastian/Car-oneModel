package ro.mycode.auth.dtos;

import ro.mycode.config.UserPermissions;

import java.util.Set;
public record AuthLoginResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Set<UserPermissions> directPermissions,
        String token



) {}
