package com.nashss.se.citrusservice.activity;



import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

import com.nashss.se.citrusservice.activity.requests.GetUserRequest;
import com.nashss.se.citrusservice.activity.results.GetUserResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.UserDao;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.models.UserModel;

import org.apache.logging.log4j.LogManager;

public class GetUserActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public GetUserActivity(UserDao userDao){
        this.userDao = userDao;
    }
    public GetUserResult handleRequest(final GetUserRequest getUserRequest){
        log.info("Receive GetUserResult {}", getUserRequest);

        String userId = getUserRequest.getUserId();
        User user = userDao.getUser(userId);

        UserModel userModel = new ModelConverter().toUserModel(user);

        return GetUserResult.builder()
                .withUserModel(userModel)
                .build();

    }

}