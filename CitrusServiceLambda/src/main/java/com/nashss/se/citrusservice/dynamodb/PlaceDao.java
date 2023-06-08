package com.nashss.se.citrusservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.citrusservice.dynamodb.models.Place;
import com.nashss.se.citrusservice.exceptions.PointOfInterestNotFoundException;
import com.nashss.se.citrusservice.metrics.MetricsConstants;
import com.nashss.se.citrusservice.metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

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
            throw new PointOfInterestNotFoundException("Could not find place with id" + placeId);
        }
        metricsPublisher.addCount(MetricsConstants.GETPLACE_PLACENOTFOUND_COUNT,0);
        return place;
    }


}
