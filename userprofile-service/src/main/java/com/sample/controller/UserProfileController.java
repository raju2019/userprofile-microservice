package com.sample.controller;

import com.sample.exception.UserNotFoundException;
import com.sample.model.UserProfile;
import com.sample.service.UserProfileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller tier, this class is in charge on manage the request and respond of the
 * UserProfile microservice
 */

@RestController
@RequestMapping(value = "/users")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;


    /**
     * This method return all Users in the system
     *
     * @return ResponseEntity<List < UserProfile>>
     */
    @ApiOperation(value = "Find all users", nickname = "retrieveAllUsers")
    @GetMapping("/retrieveAll")
    public ResponseEntity<List<UserProfile>> getUsers() {
        return ResponseEntity.ok(userProfileService.findAll().get());
    }


    /**
     * This method create a new userProfile
     *
     * @param userProfile new userProfile
     * @return ResponseEntity<UserProfile>
     */
    @ApiOperation(value = "Add New User in the System", nickname = "addNewUser")
    @PostMapping()
    public ResponseEntity<UserProfile> newUser(@RequestBody @Valid @ApiParam(value = "NewAccount User Profile") UserProfile userProfile) {
        UserProfile newUserProfile = userProfileService.saveUser(userProfile);

        return ResponseEntity.ok(newUserProfile);
    }

    /**
     * This method updates an userProfile
     *
     * @param userProfile new userProfile
     * @return ResponseEntity<UserProfile>
     */
    @ApiOperation(value = "Update existing User in the System", nickname = "updateUser")
    @PutMapping()
    public ResponseEntity<UserProfile> updateUser(@RequestBody @Valid @ApiParam(value = "updateUserProfile") UserProfile userProfile) {
        Optional<UserProfile> account = userProfileService.findByUserId(userProfile.getUserId());
        if (account != null && account.isPresent()) {
            UserProfile newUserProfile = userProfileService.saveUser(userProfile);
            return ResponseEntity.ok(newUserProfile);
        }
        throw new UserNotFoundException(userProfile.getUserId());
    }

    /**
     * This method deletes an userProfile
     *
     * @param userId userId
     * @return ResponseEntity<UserProfile>
     */
    @ApiOperation(value = "Delete an existing User in the System", nickname = "deleteUser")
    @DeleteMapping(value = "{userId}")
    public ResponseEntity deleteUser(@PathVariable @ApiParam(value = "deleteUserId") Long userId) {
        Optional<UserProfile> userProfile = userProfileService.findByUserId(userId);
        if (userProfile != null && userProfile.isPresent() && userProfileService.deleteUser(userId)) {
            return ResponseEntity.ok(userProfile);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Unable to delete user");
    }

    /**
     * This method return an userprofile by userId
     *
     * @param userId
     * @return ResponseEntity<UserProfile>
     * @throws NotFoundException if the userId doesn't exist
     */
    @ApiOperation(value = "Find an user by UserId", nickname = "findUser")
    @GetMapping(value = "{userId}")
    public ResponseEntity<UserProfile> findUserById(@PathVariable @ApiParam(value = "findUserID") Long userId) throws UserNotFoundException {
        Optional<UserProfile> account = userProfileService.findByUserId(userId);
        if (account != null && account.isPresent()) {
            return ResponseEntity.ok(account.get());
        }
        throw new UserNotFoundException(userId);

    }

}
