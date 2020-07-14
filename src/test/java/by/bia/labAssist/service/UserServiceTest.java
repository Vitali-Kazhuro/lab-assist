package by.bia.labAssist.service;

import by.bia.labAssist.model.Role;
import by.bia.labAssist.model.User;
import by.bia.labAssist.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void addUserTest(){
        User user = new User();
        boolean isUserCreated = userService.create(user);
        Assertions.assertTrue(isUserCreated);
        Assertions.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void addUserFailTest(){
        User user = new User();

        user.setUsername("Admin007");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("Admin007");

        boolean isUserCreated = userService.create(user);

        Assertions.assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }

}