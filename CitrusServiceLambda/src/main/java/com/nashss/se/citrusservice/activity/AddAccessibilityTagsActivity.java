package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.AddAccessibilityTagsRequest;
import com.nashss.se.citrusservice.activity.results.AddAccessibilityTagsResult;
import com.nashss.se.citrusservice.dynamodb.PlaceDao;
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
        System.out.println("NOAH LINE 24 " + addAccessibilityTagsRequest);
                String placeId = addAccessibilityTagsRequest.getPlaceId();
        Set<String> tagsToAdd = addAccessibilityTagsRequest.getAccessibilityTagsToAdd();

        placeDao.getPlace(placeId);

        Set<String> updatedTags = placeDao.addTagsToPlace(tagsToAdd,placeId);
        return AddAccessibilityTagsResult.builder()
                .withTags(updatedTags)
                .build();
    }
}
