package com.sample.repository;

import com.sample.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to connect with DB
 */

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {

    /**
     * This method return an user by Alias
     * @param alias to search
     * @return UserProfile
     */
    UserProfile findByAlias(String alias);

    /**
     * This method return an userProfile object
     * @param userId to search
     * @return UserProfile
     */
    UserProfile findByUserId(Long userId);
}
