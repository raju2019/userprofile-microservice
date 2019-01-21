/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gateway.controller;

import com.gateway.application.UsersServiceClient;
import com.gateway.domain.UserProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiGatewayController {

    private final UsersServiceClient usersServiceClient;


    @GetMapping(value = "userdetails/{userId}")
    public UserProfileModel getUser(final @PathVariable Long userId) {
        final UserProfileModel userProfileModel = usersServiceClient.getUser(userId);
        return userProfileModel;
    }

    @GetMapping(value = "userdetails")
    public List<UserProfileModel> getAllUserDetails() {
        return usersServiceClient.getAllUsers();
    }

}
