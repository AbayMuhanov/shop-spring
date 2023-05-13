package muha.shop.service;

import muha.shop.entity.User;
import muha.shop.entity.UserRole;
import muha.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return userRepository.findByLogin(authentication.getName()).orElse(null);
    }

    public void craeteUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRegistrationDate(LocalDateTime.now());
        user.setRole(UserRole.USER);
        userRepository.save(user);
    }

}
