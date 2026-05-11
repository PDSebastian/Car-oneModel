package ro.mycode.user.service;

import ro.mycode.auth.dtos.AuthLoginResponse;
import ro.mycode.user.dtos.UserRequest;
import ro.mycode.user.dtos.UserResponse;

public interface AuthService {
    UserResponse register(UserRequest userRequest);
    AuthLoginResponse login (UserRequest userRequest);

}
