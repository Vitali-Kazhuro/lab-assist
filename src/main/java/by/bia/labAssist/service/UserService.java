package by.bia.labAssist.service;

import by.bia.labAssist.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    boolean addUser(User user);

    List<User> findAll();

    void saveUser(User user, String username, Map<String, String> form);

    void delete(Integer userId);

    void updateProfile(User user, String password);
}
