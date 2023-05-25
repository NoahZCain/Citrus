package com.nashss.se.Citrus.activity;



import javax.inject.Inject;

import com.nashss.se.Citrus.dynamodb.UserDao;
import com.nashss.se.Citrus.dynamodb.models.User;
import com.nashss.se.Citrus.models.UserModel;
import org.apache.logging.log4j.Logger;
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