package com.nashss.se.citrusservice.converters;

import org.apache.logging.log4j.Logger;

import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ModelConverter {

    private final Logger log = LogManager.getLogger();

    public UserModel toUserModel(User user){
        return UserModel.builder()
                .withUserId(user.getUserId())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withGender(user.getGender())
                .withDateOfBirth(user.getDateOfBirth())
                .withInterests(user.getUserInterests())
                .build();

    }

}
