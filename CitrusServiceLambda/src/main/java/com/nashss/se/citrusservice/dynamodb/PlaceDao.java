package com.nashss.se.citrusservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.citrusservice.dynamodb.models.Place;
import com.nashss.se.citrusservice.exceptions.InvalidAttributeException;
import com.nashss.se.citrusservice.exceptions.PointOfInterestNotFoundException;
import com.nashss.se.citrusservice.metrics.MetricsConstants;
import com.nashss.se.citrusservice.metrics.MetricsPublisher;

import javax.inject.Inject;
import java.util.*;

public class PlaceDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public PlaceDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher){
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }
    public Place getPlace(String placeId){

        Place place = this.dynamoDBMapper.load(Place.class,placeId);
        if(place == null){
            metricsPublisher.addCount(MetricsConstants.GETPLACE_PLACENOTFOUND_COUNT,1);
            throw new PointOfInterestNotFoundException("Could not find place with id " + placeId);
        }
        metricsPublisher.addCount(MetricsConstants.GETPLACE_PLACENOTFOUND_COUNT,0);
        return place;
    }
    public Place addTagsToPlace(Set<String> accessibilityTags, String placeId) {

        if (placeId == null || placeId.isEmpty()) {
            throw new InvalidAttributeException("The entered place is invalid");
        }
        Place placeToAddTags = getPlace(placeId);

        Set<String> tagsAlreadyStored = placeToAddTags.getAccessibilityTags();
        tagsAlreadyStored.addAll(accessibilityTags);

        placeToAddTags.setAccessibilityTags(tagsAlreadyStored);
        this.dynamoDBMapper.save(placeToAddTags);
        return placeToAddTags;
    }


    public List<Place> searchForPlace(String[] criteria) {
        if (criteria.length == 0) {
            return Collections.emptyList(); // No criteria provided, return an empty list
        }

        List<Place> places = new ArrayList<>();

        for (String criterion : criteria) {
            DynamoDBQueryExpression<Place> queryExpression = new DynamoDBQueryExpression<Place>()
                    .withIndexName("PlaceNameIndex")
                    .withConsistentRead(false)
                    .withKeyConditionExpression("placeName = :placeName")
                    .withExpressionAttributeValues(Map.of(":placeName", new AttributeValue(criterion)));

            List<Place> result = dynamoDBMapper.query(Place.class, queryExpression);
            places.addAll(result);
        }
        return places;
    }


    public Set<String> removeAccessibilityTagsFromPlace (String placeId, Set<String> tagsToRemove){
        if(placeId == null){
            throw new InvalidAttributeException("This place Does not exist");
        }
        Place place = getPlace(placeId);
        Set<String> existingTags = place.getAccessibilityTags();

        for(String s : tagsToRemove){
        existingTags.remove(s);

        }
        this.dynamoDBMapper.save(place);
        return new HashSet<>(existingTags);
    }


}
