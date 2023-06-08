package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.CreateUserRequest;
import com.nashss.se.citrusservice.activity.results.CreateUserResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.UserDao;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.exceptions.InvalidAttributeException;
import com.nashss.se.citrusservice.models.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


public class CreateUserActivity {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    @Inject
    public CreateUserActivity(UserDao userDao){
        this.userDao = userDao;
    }
    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest)  {
        log.info("Received CreateUserRequest{}", createUserRequest);

        if(createUserRequest.getFirstName() == null ||
                createUserRequest.getFirstName().isEmpty()){
            throw new InvalidAttributeException("Name cannot contain invalid characters");
        }
        User newUser = userDao.saveUser(true,
                createUserRequest.getUserId(),
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getGender(),
                createUserRequest.getDateOfBirth(),
                createUserRequest.getUserInterests());

        UserModel userModel = new ModelConverter().toUserModel(newUser);
        return CreateUserResult.builder()
                .withUser(userModel)
                .build();
    }
}
