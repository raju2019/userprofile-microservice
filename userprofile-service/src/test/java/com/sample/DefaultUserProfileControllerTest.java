package com.sample;

import com.sample.controller.UserProfileController;
import com.sample.model.UserProfile;
import com.sample.service.UserProfileService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * This class has been develop with tdd.
 * <p>
 * The goal of this class is go from the test to the controller, developing the necesary steps to reach all process of
 * our controller
 *
 * @author Noel Rodriguez
 */


public class DefaultUserProfileControllerTest {

    @InjectMocks
    private UserProfileController userProfileController;

    @Mock
    private UserProfileService userProfileService;


    private MockMvc mockMvc;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Before
    public void setUpMockMvc() {
        mockMvc = standaloneSetup(userProfileController).build();
    }


    @Test
    public void findAllAccounts() throws Exception {

        UserProfile userProfile = new UserProfile();
        userProfile.setAlias("pepe");
        userProfile.setUserId(747940L);

        List accounts = new ArrayList();
        accounts.add(userProfile);

        Optional<List<UserProfile>> accountsOptional = Optional.of(accounts);
        when(userProfileService.findAll()).thenReturn(accountsOptional);

        mockMvc.perform(get("/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void creatingErrorAccount() throws Exception {
        UserProfile userProfile = when(mock(UserProfile.class).getUserId())
                .thenReturn(1L)
                .getMock();

        when(userProfileService.saveUser(isA(UserProfile.class)))
                .thenReturn(userProfile);

        mockMvc.perform(post("/accounts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void creatingAccount() throws Exception {
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"alias\" : \"pepe\",\"accountId\" : \"1\"}"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void findAccountId() throws Exception {

        UserProfile userProfile = new UserProfile();
        when(userProfileService.findByUserId(1L))
                .thenReturn(Optional.of(userProfile));

        mockMvc.perform(get("/accounts/{accountId}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }


    @Test
    public void notFoundAccountId() throws Exception {
        mockMvc.perform(get("/accounts/-1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
