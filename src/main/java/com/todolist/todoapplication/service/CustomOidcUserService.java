package com.todolist.todoapplication.service;

import com.todolist.todoapplication.entity.User;
import com.todolist.todoapplication.model.AuthenticationProvider;
import com.todolist.todoapplication.model.GoogleUserInfo;
import com.todolist.todoapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) throws AuthenticationException {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

        String em = googleUserInfo.getEmail();
        String id = googleUserInfo.getId();

        User userEmail = userRepository.findByEmail(em);

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(em));
        if (!userOptional.isPresent()) {
            User user = new User();
            user.setId(googleUserInfo.getId());
            user.setEmail(googleUserInfo.getEmail());
            user.setUsername(googleUserInfo.getName());
            user.setAuthProvider(AuthenticationProvider.GOOGLE);

            User userSaveAuth2 = userRepository.save(user);
            userRepository.updateUserId(userSaveAuth2.getId(), id);
        }

        return oidcUser;
    }
}