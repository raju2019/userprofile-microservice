package com.sample.service;

import com.sample.model.UserProfile;

import java.util.List;
import java.util.Optional;

/**
 * Interface of UserProfileService, this interface define the necessary methods
 * to work with users
 */
public interface UserProfileService {

    /**
     * Return all users in the system
     *
     * @return Otional<List<UserProfile>>
     */
    Optional<List<UserProfile>> findAll();

    /**
     * Find an User by Alias
     *
     * @param alias alias account
     * @return Optional<UserProfile>
     */
    Optional<UserProfile> findByAlias(String alias);

    /**
     * Find an User by UserId
     *
     * @param userID userID of the user
     * @return Optional<UserProfile>
     */
    Optional<UserProfile> findByUserId(Long userID);

    /**
     * Save a new User
     *
     * @param userProfile the new userProfile
     * @return UserProfile
     */
    UserProfile saveUser(UserProfile userProfile);

    /**
     * Delete a  User
     *
     * @param userID the new userProfile
     * @return UserProfile
     */
    Boolean deleteUser(Long userID);

}
