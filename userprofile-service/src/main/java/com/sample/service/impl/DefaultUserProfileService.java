package com.sample.service.impl;

import com.sample.exception.UserNotFoundException;
import com.sample.model.UserProfile;
import com.sample.repository.UserRepository;
import com.sample.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implement  UserProfileService
 *
 */

@Service
public class DefaultUserProfileService implements UserProfileService {

    private static UserRepository userRepository;

    @Autowired
    public DefaultUserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<List<UserProfile>> findAll() {

        List<UserProfile> userProfiles = userRepository.findAll();
        if (userProfiles != null)
            return Optional.of(userProfiles);
        else
            return Optional.empty();
    }

    @Override
    public Optional<UserProfile> findByAlias(String alias) {
        UserProfile userProfile = userRepository.findByAlias(alias);
        if (userProfile != null) {
            return Optional.of(userProfile);
        } else
            return Optional.empty();
    }


    @Override
    public Optional<UserProfile> findByUserId(Long userID) {
        UserProfile userProfile = userRepository.findByUserId(userID);
        if (userProfile != null) {
            return Optional.of(userProfile);
        } else
            return Optional.empty();
    }


    @Override
    public UserProfile saveUser (UserProfile userProfile) {
        return userRepository.save(userProfile);
    }

    @Override
    public Boolean deleteUser(Long id) {
        UserProfile deleteUser = userRepository.findByUserId(id);
        if(deleteUser!=null) {
            userRepository.deleteById(id);
            return true;
        } else{
            throw new UserNotFoundException(id);
        }
    }
}
