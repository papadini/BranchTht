package com.test.branchtht.service;

import com.test.branchtht.dao.UserDao;
import com.test.branchtht.entity.FormattedUserDto;
import com.test.branchtht.entity.RawUser;
import com.test.branchtht.entity.RawUserRepo;
import com.test.branchtht.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public FormattedUserDto getUserByUsername(final String username) {
        final RawUser rawUser = getRawUserByUserName(username);
        final List<RawUserRepo> rawUserRepos = getRawUserByUserRepoByUsername(username);

        return rawUserToFormattedUserDto(rawUser, rawUserRepos);
    }

    protected RawUser getRawUserByUserName(final String username) {
        RawUser foundUser = userDao.getUserByUsername(username);
        if (foundUser == null) {
            throw new UserNotFoundException("No user was found for username:" + username);
        }

        return userDao.getUserByUsername(username);
    }

    protected List<RawUserRepo> getRawUserByUserRepoByUsername(final String username) {
        return userDao.getUserReposByUsername(username);
    }

    protected String formatDate(final Date createdDate) {
        String pattern = "yyyy-MM-d H:m:s";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(createdDate);
    }

    protected FormattedUserDto rawUserToFormattedUserDto(final RawUser rawUser, final List<RawUserRepo> rawUserRepos) {

        return FormattedUserDto.builder()
                .user_name(rawUser.getLogin())
                .display_name(rawUser.getName())
                .avatar(rawUser.getAvatar_url())
                .geo_location(rawUser.getLocation())
                .email(rawUser.getEmail())
                .url(rawUser.getHtml_url())
                .created_at(formatDate(rawUser.getCreated_at()))
                .repos(rawUserRepos)
                .build();

    }

}
