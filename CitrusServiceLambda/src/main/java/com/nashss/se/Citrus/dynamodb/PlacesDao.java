package com.nashss.se.Citrus.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.Citrus.dynamodb.models.Places;
import com.nashss.se.Citrus.exceptions.PointOfInterestNotFoundException;
import com.nashss.se.Citrus.metrics.MetricsConstants;
import com.nashss.se.Citrus.metrics.MetricsPublisher;

import javax.inject.Inject;

public class PlacesDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public PlacesDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher){
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }
    public Places getPlace(String placeId){
        Places place = dynamoDBMapper.load(Places.class,placeId);
        if(place == null){
            metricsPublisher.addCount(MetricsConstants.GETPLACE_PLACENOTFOUND_COUNT,1);
            throw new PointOfInterestNotFoundException("Could not find place with id" + placeId);
        }
        metricsPublisher.addCount(MetricsConstants.GETPLACE_PLACENOTFOUND_COUNT,0);
        return place;
    }


}
