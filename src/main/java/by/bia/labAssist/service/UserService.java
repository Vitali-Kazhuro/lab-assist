package by.bia.labAssist.service;

import by.bia.labAssist.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();

    void updateProfile(User user, String password);

    boolean create(User user);

    void edit(User user, String username, Map<String, String> form);

    void delete(Integer userId);
}
