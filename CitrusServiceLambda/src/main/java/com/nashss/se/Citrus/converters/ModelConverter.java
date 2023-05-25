package com.nashss.se.Citrus.converters;

import com.nashss.se.Citrus.dynamodb.models.User;
import com.nashss.se.Citrus.models.UserModel;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ModelConverter {

    private final S.Logger log = LogManager.getLogger();

    public UserModel toUserModel(User user){
        return UserModel.builder()
                .withUserId(user.getUserId())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withEmailAddress(user.getEmailAddress())
                .withGender(user.getGender())
                .withDateOfBirth(user.getDateOfBirth())
                .withInterests(user.getUserInterests())
                .build();

    }

}
