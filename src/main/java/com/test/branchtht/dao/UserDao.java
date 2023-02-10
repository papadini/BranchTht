package com.test.branchtht.dao;

import com.test.branchtht.entity.RawUser;
import com.test.branchtht.entity.RawUserRepo;
import com.test.branchtht.exception.UserNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Repository
public class UserDao {

    private static final String BASE_USER_API = "https://api.github.com/users/";
    private static final String END_USER_REPO_API = "repos";
    RestTemplate restTemplate = new RestTemplate();
    @Cacheable("users")
    public RawUser getUserByUsername(final String username) {
       String url =  UriComponentsBuilder.fromHttpUrl(BASE_USER_API)
                .pathSegment(username)
                .toUriString();
       return restTemplate.getForObject(url, RawUser.class);
    }

    @Cacheable("user_repos")
    public List<RawUserRepo> getUserReposByUsername(final String username) {
        String url =  UriComponentsBuilder.fromHttpUrl(BASE_USER_API)
                .pathSegment(username)
                .pathSegment(END_USER_REPO_API)
                .toUriString();

      return restTemplate
              .exchange(
                      url,
                      HttpMethod.GET,
                      null,
                      new ParameterizedTypeReference<List<RawUserRepo>>(){}).getBody();

    }
}
