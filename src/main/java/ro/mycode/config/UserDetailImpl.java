package ro.mycode.config;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Component;
import ro.mycode.user.model.User;
import ro.mycode.user.repository.UserRepository;
@Component
public class UserDetailImpl implements Usw {
    private UserRepository userRepository;
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User loadUserByEmail(String email) throws Exception {
        if(userRepository.findByEmail(email)==null){
            throw new Exception("User not found");
        }
        return userRepository.findByEmail(email);


    }
}
