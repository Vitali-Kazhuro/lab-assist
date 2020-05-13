package by.bia.labAssist.service.impl;

import by.bia.labAssist.service.UserService;
import by.bia.labAssist.exception.CustomUserNotFoundException;
import by.bia.labAssist.model.Role;
import by.bia.labAssist.model.User;
import by.bia.labAssist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new CustomUserNotFoundException("Введите имя пользователя и пароль!");
        }

        return user;
    }

    public boolean addUser(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB != null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public void delete(Integer userId){
        userRepository.deleteById(userId);
    }

    public void updateProfile(User user, String password) {
        if (!StringUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
            //user.setPassword(password);
        }
        userRepository.save(user);
    }
}

