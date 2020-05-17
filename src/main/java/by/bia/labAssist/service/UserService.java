package by.bia.labAssist.service;

import by.bia.labAssist.model.User;

import java.util.List;
import java.util.Map;

/**
 * Provides service logic for{@link User} entity
 */
public interface UserService {
    /**
     * Returns List with all User instances
     * @return {@link List<User>} object
     */
    List<User> findAll();

    /**
     * Updates User profile
     * @param user {@link User} whose profile is updated
     * @param password new User password
     */
    void updateProfile(User user, String password);

    /**
     * Creates new User instance and persists it into database
     * @param user {@link User} object
     * @return {@link boolean} flag that returns true if user was created, false - if user already exists in database
     */
    boolean create(User user);

    /**
     * Edits passed User instance and persists it into database
     * @param user edited instance of User
     * @param username User username
     * @param form passed html form with user name and roles
     */
    void edit(User user, String username, Map<String, String> form);

    /**
     * Deletes instance of User from database
     * @param userId User id
     */
    void delete(Integer userId);
}
