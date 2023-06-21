package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.GetPlaceRequest;
import com.nashss.se.citrusservice.activity.results.GetPlaceResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.PlaceDao;
import com.nashss.se.citrusservice.dynamodb.models.Place;
import com.nashss.se.citrusservice.models.PlaceModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetPlaceActivity {
    private final Logger log = LogManager.getLogger();
    private final PlaceDao placeDao;

    @Inject
    public GetPlaceActivity(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }
    public GetPlaceResult handleRequest(final GetPlaceRequest getPlaceRequest){
        log.info("Receive GetPlaceResult {}", getPlaceRequest);

        String id = getPlaceRequest.getPlaceId();
        Place place = placeDao.getPlace(id);

        PlaceModel placeModel = new ModelConverter().toPlaceModel(place);
        return GetPlaceResult.builder()
                .withPlaceModel(placeModel)
                .build();
    }
}
