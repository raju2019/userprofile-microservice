
package com.gateway.application;

import com.gateway.domain.UserProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersServiceClient {

    private final RestTemplate loadBalancedRestTemplate;

    public UserProfileModel getUser(final Long userId) {
        return loadBalancedRestTemplate.getForObject("http://userprofile-service/users/{userId}", UserProfileModel.class, userId);
    }

    public List<UserProfileModel> getAllUsers() {
        UserProfileModel[] userProfileModels = loadBalancedRestTemplate.getForObject("http://userprofile-service/users/retrieveAll", UserProfileModel[].class);
        List<UserProfileModel> searchList= Arrays.asList(userProfileModels);

        return searchList;
    }
}
