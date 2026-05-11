package ro.mycode.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ro.mycode.user.repository.UserRepository;

@Component
public class UserDetailImpl implements UserDetailsService {
        UserRepository userRepository;
        public UserDetailImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       if(!userRepository.findByEmail(email).isPresent()){
           throw new UsernameNotFoundException("User not found");
       }
       return  userRepository.findByEmail(email).get();
    }



}
