package ro.mycode.user.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.mycode.auth.dtos.AuthLoginResponse;
import ro.mycode.config.UserPermissions;
import ro.mycode.config.security.JWTTokenProvider;
import ro.mycode.user.dtos.UserRequest;
import ro.mycode.user.dtos.UserResponse;
import ro.mycode.user.exceptions.UserNotFoundException;
import ro.mycode.user.mapper.UserMapper;
import ro.mycode.user.model.User;
import ro.mycode.user.repository.UserRepository;

import java.util.Set;

@Component
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,JWTTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        if(userRepository.findByEmail(userRequest.email()).isPresent()){
            throw new RuntimeException("Email already in use");
        }

        User user = userMapper.toEntity(userRequest);

        user.setPassword(passwordEncoder.encode(userRequest.password()));

        user.setPermissions(Set.of(UserPermissions.USER_READ, UserPermissions.USER_WRITE, UserPermissions.USER_EDIT, UserPermissions.USER_DELETE));
        return UserMapper.toDTO(userRepository.save(user));
    }
    @Override
    public AuthLoginResponse login(UserRequest userRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password())
        );

        User user = userRepository.findByEmail(userRequest.email())
                .orElseThrow(() -> new UserNotFoundException());

        return new AuthLoginResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPermissions(),
                jwtTokenProvider.generateToken(user)
        );
    }
}
