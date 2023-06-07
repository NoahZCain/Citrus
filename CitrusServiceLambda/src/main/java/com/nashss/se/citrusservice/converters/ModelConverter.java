package com.nashss.se.citrusservice.converters;

import com.nashss.se.citrusservice.dynamodb.models.Place;
import com.nashss.se.citrusservice.models.PlaceModel;
import org.apache.logging.log4j.Logger;

import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.models.UserModel;

import org.apache.logging.log4j.LogManager;


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
    public PlaceModel toPlaceModel(Place place){
        return PlaceModel.builder()
                .withPlaceId(place.getPlaceId())
                .withPlaceName(place.getPlaceName())
                .withPlaceAddress(place.getPlaceAddress())
                .withAccessibilityInfo(place.getAccessibilityTags())
                .withPlaceTypes(place.getPlaceTypes())
                .build();
    }

}
