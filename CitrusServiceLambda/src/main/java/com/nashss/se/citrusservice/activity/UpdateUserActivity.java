package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.UpdateUserRequest;
import com.nashss.se.citrusservice.activity.results.UpdateUserResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.UserDao;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateUserActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private MetricsPublisher metricsPublisher;

    @Inject
    public UpdateUserActivity(UserDao userDao, MetricsPublisher metricsPublisher){
        this.userDao = userDao;
        this.metricsPublisher = metricsPublisher;
    }

    public UpdateUserResult handleRequest(final UpdateUserRequest updateUserRequest){
        log.info("Received UpdateUserRequest{}", updateUserRequest);


        User newUser = userDao.saveUser(false, updateUserRequest.getUserId(), updateUserRequest.getFirstName(), updateUserRequest.getLastName(), updateUserRequest.getGender(), updateUserRequest.getDateOfBirth(), updateUserRequest.getUserInterests());

        return UpdateUserResult.builder()
                .withUserModel(new ModelConverter().toUserModel(newUser))
                .build();
    }






}
