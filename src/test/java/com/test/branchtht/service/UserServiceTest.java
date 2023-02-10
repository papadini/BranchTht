package com.test.branchtht.service;

import com.test.branchtht.dao.UserDao;
import com.test.branchtht.entity.FormattedUserDto;
import com.test.branchtht.entity.RawUser;
import com.test.branchtht.entity.RawUserRepo;
import com.test.branchtht.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    UserDao userDao;
    UserService userService;
    private static final String TEST_USERNAME = "octocat";

    @BeforeEach
    void setup() {
        userDao = mock(UserDao.class);
        userService = new UserService(userDao);
    }

    @Test
    public void getRawUser() {
        RawUser testUser = RawUser.builder()
                .name("The Octocat")
                .build();

        when(userDao.getUserByUsername(TEST_USERNAME)).thenReturn(testUser);

        RawUser foundUser = userService.getRawUserByUserName(TEST_USERNAME);

        assertEquals("The Octocat", foundUser.getName());
    }

    @Test
    public void getRawUserRepos() {
        RawUserRepo testUserRepo1 = RawUserRepo.builder()
                .name("repo1")
                .html_url("html url ")
                .build();
        RawUserRepo testUserRepo2 = RawUserRepo.builder()
                .name("repo2")
                .html_url("html url ")
                .build();

        when(userDao.getUserReposByUsername(TEST_USERNAME)).thenReturn(Arrays.asList(testUserRepo1, testUserRepo2));

        int numRepos = userService.getRawUserByUserRepoByUsername(TEST_USERNAME).size();

        assertEquals(2, numRepos);
    }

    @Test
    public void getFormattedUser() {
        RawUserRepo testUserRepo1 = RawUserRepo.builder()
                .name("repo1")
                .html_url("html url ")
                .build();
        RawUserRepo testUserRepo2 = RawUserRepo.builder()
                .name("repo2")
                .html_url("html url ")
                .build();

        RawUser testUser = RawUser.builder()
                .name("The Octocat")
                .location("denver")
                .login("octocat")
                .url("Testy.test.com")
                .avatar_url("github.com/avatar/12345")
                .created_at(new Date())
                .build();

        when(userDao.getUserByUsername(TEST_USERNAME)).thenReturn(testUser);
        when(userDao.getUserReposByUsername(TEST_USERNAME)).thenReturn(Arrays.asList(testUserRepo1, testUserRepo2));

        FormattedUserDto formattedUserDto = userService.getUserByUsername(TEST_USERNAME);

        assertEquals(TEST_USERNAME, formattedUserDto.getUser_name());
        assertEquals("The Octocat", formattedUserDto.getDisplay_name());
        assertEquals("denver", formattedUserDto.getGeo_location());
        assertEquals(2, formattedUserDto.getRepos().size());
    }

    @Test
    public void getUser_noUserReturned_throw_UserNotFoundException() {
        when(userDao.getUserByUsername("name")).thenReturn(null);

        assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserByUsername("name"));
    }
}
