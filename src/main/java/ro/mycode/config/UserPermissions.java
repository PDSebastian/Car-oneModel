package ro.mycode.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_EDIT("user:edit"),
    USER_DELETE("user:delete");

    private final String permission;

    public  String getPermission() {
        return permission;
    }


}
