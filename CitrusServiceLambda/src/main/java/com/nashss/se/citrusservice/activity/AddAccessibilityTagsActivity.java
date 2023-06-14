package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.AddAccessibilityTagsRequest;
import com.nashss.se.citrusservice.activity.results.AddAccessibilityTagsResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.PlaceDao;
import com.nashss.se.citrusservice.dynamodb.models.Place;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Set;

public class AddAccessibilityTagsActivity {
    private final Logger log = LogManager.getLogger();
    private final PlaceDao placeDao;

    @Inject
    public AddAccessibilityTagsActivity(PlaceDao placeDao){
        this.placeDao = placeDao;
    }


    public AddAccessibilityTagsResult handleRequest(final AddAccessibilityTagsRequest addAccessibilityTagsRequest){
        log.info("Received AddAccessibilityTagsRequest{}",addAccessibilityTagsRequest);
                String placeId = addAccessibilityTagsRequest.getPlaceId();
        Set<String> tagsToAdd = addAccessibilityTagsRequest.getAccessibilityTags();

        placeDao.getPlace(placeId);

       Place updatedPlace = placeDao.addTagsToPlace(tagsToAdd,placeId);

        return AddAccessibilityTagsResult.builder()
                .withPlaceModel(new ModelConverter().toPlaceModel(updatedPlace))
                .build();
    }
}
