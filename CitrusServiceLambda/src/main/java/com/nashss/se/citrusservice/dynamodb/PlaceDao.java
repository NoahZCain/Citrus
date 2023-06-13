package com.nashss.se.citrusservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.citrusservice.dynamodb.models.Place;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.exceptions.InvalidAttributeException;
import com.nashss.se.citrusservice.exceptions.PointOfInterestNotFoundException;
import com.nashss.se.citrusservice.metrics.MetricsConstants;
import com.nashss.se.citrusservice.metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class PlaceDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public PlaceDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher){
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }
    public Place getPlace(String placeId){
        System.out.println("NOAH ");
        Place place = this.dynamoDBMapper.load(Place.class,placeId);
        if(place == null){
            metricsPublisher.addCount(MetricsConstants.GETPLACE_PLACENOTFOUND_COUNT,1);
            throw new PointOfInterestNotFoundException("Could not find place with id" + placeId);
        }
        metricsPublisher.addCount(MetricsConstants.GETPLACE_PLACENOTFOUND_COUNT,0);
        return place;
    }
    public Set<String> addTagsToPlace(Set<String> accessibilityTags, String placeId) {
        System.out.println("NOAH LINE 36" + getPlace(placeId));
        if (placeId == null || placeId.isEmpty()) {
            throw new InvalidAttributeException("The entered place is invalid");
        }
        Place placeToAddTags = getPlace(placeId);
        Set<String> tagsAlreadyStored = placeToAddTags.getAccessibilityTags();

        tagsAlreadyStored.addAll(accessibilityTags);
        placeToAddTags.setAccessibilityTags(accessibilityTags);
        dynamoDBMapper.save(placeToAddTags);
        return new HashSet<>(placeToAddTags.getAccessibilityTags());
    }

}
