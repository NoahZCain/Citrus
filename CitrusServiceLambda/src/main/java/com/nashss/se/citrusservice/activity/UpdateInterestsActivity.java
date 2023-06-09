package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.UpdateInterestsRequest;
import com.nashss.se.citrusservice.activity.results.UpdateInterestsResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.UserDao;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Set;

public class UpdateInterestsActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private MetricsPublisher metricsPublisher;

    @Inject
    public UpdateInterestsActivity(UserDao userDao, MetricsPublisher metricsPublisher){
        this.userDao = userDao;
        this.metricsPublisher = metricsPublisher;
    }

    public UpdateInterestsResult handleRequest(final UpdateInterestsRequest updateInterestsRequest) {
        log.info("Received UpdateInterestsRequest{}", updateInterestsRequest);
        System.out.println("NOAH ACTIVITY");
        String userId = updateInterestsRequest.getUserId();
        Set<String> userInterests = updateInterestsRequest.getUserInterests();

        User updatedUser = userDao.addInterestsToUser(userInterests,userId);

        return  UpdateInterestsResult.builder()
                .withUserModel(new ModelConverter().toUserModel(updatedUser))
                .build();
    }
}
