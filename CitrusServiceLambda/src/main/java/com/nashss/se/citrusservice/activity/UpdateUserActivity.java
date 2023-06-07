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

        User user = userDao.getUser(updateUserRequest.getUserId());

        User newUser = userDao.saveUser(false, user.getUserId(),user.getFirstName(),user.getLastName(),user.getGender(),user.getDateOfBirth(),user.getUserInterests());

        return UpdateUserResult.builder()
                .withUserModel(new ModelConverter().toUserModel(newUser))
                .build();
    }






}
