package com.sample;

import com.sample.model.UserProfile;
import com.sample.repository.UserRepository;
import com.sample.service.impl.DefaultUserProfileService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class has been develop with tdd.
 * <p>
 * The goal of this class is go from the test to the service, developing the necesary steps to reach all process of
 * our service
 *
 */
public class DefaultUserProfileServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DefaultUserProfileService defaultUserProfileService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Captor
    private ArgumentCaptor<UserProfile> userProfileArgumentCaptor;


    @Test
    public void findAll() {

        UserProfile userProfile = new UserProfile();
        userProfile.setAlias("pepe");
        userProfile.setUserId(747940L);

        List accounts = new ArrayList();
        accounts.add(userProfile);

        when(userRepository.findAll()).thenReturn(accounts);

        List<UserProfile> accountsResult = defaultUserProfileService.findAll().get();
        assertThat(accounts.size(), is(accountsResult.size()));
    }

    @Test
    public void findByAccountId() {
        UserProfile userProfile = when(mock(UserProfile.class).getUserId())
                .thenReturn(2L)
                .getMock();
        when(userProfile.getAlias())
                .thenReturn("TDD");
        when(userRepository.findById(2L))
                .thenReturn(Optional.ofNullable(userProfile));

        Optional<UserProfile> optionalAccount = defaultUserProfileService.findByUserId(2L);

        UserProfile userProfileDefault = optionalAccount.get();
        assertThat(userProfile.getUserId(), is(userProfileDefault.getUserId()));
        assertThat(userProfile.getAlias(), is(userProfileDefault.getAlias()));
    }


    @Test
    public void findByAccountAlias() {
        UserProfile userProfile = when(mock(UserProfile.class).getAlias())
                .thenReturn("pepe")
                .getMock();
        when(userProfile.getUserId())
                .thenReturn(1L);
        when(userRepository.findByAlias("pepe"))
                .thenReturn(userProfile);

        Optional<UserProfile> optionalAccount = defaultUserProfileService.findByAlias("pepe");

        UserProfile userProfileDefault = optionalAccount.get();
        assertThat(userProfile.getUserId(), is(userProfileDefault.getUserId()));
        assertThat(userProfile.getAlias(), is(userProfileDefault.getAlias()));

    }

    @Test
    public void saveNewAccount() {
        UserProfile userProfile = new UserProfile();
        userProfile.setAlias("New Alias");

        UserProfile newUserProfile = when(mock(UserProfile.class).getUserId())
                .thenReturn(1L)
                .getMock();
        when(newUserProfile.getAlias())
                .thenReturn("New Alias");
        when(userRepository.save(userProfileArgumentCaptor.capture()))
                .thenReturn(newUserProfile);

        UserProfile defaultUserProfile = defaultUserProfileService.saveUser(userProfile);

        assertThat(userProfileArgumentCaptor.getValue().getAlias(), is(userProfile.getAlias()));
        assertThat(defaultUserProfile.getUserId(), is(newUserProfile.getUserId()));
        assertThat(defaultUserProfile.getAlias(), is(newUserProfile.getAlias()));
    }


    @Test
    public void returnNothingIsNotExist() {
        when(userRepository.findById(1L))
                .thenReturn(null);
        Optional<UserProfile> account = defaultUserProfileService.findByUserId(1L);

        assertThat(account.isPresent(), is(false));
    }
}
