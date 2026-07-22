package ro.mycode.user.model;

import ro.mycode.config.UserPermissions;

import java.util.EnumSet;
import java.util.Set;

public final class PermissionTemplates {
        public static final Set<UserPermissions> USER_DEFAULT=EnumSet.of(
                UserPermissions.USER_DELETE,
                UserPermissions.USER_WRITE,
                UserPermissions.USER_EDIT,
                UserPermissions.USER_READ



        );

}
