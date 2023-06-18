package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.RemoveAccessibilityTagsRequest;
import com.nashss.se.citrusservice.activity.results.RemoveAccessibilityTagsResult;
import com.nashss.se.citrusservice.dynamodb.PlaceDao;
import com.nashss.se.citrusservice.models.PlaceModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RemoveAccessibilityTagsActivity {
    private final Logger log = LogManager.getLogger();
    private final PlaceDao placeDao;

    @Inject
    public RemoveAccessibilityTagsActivity(PlaceDao placeDao){
        this.placeDao = placeDao;
    }


    public RemoveAccessibilityTagsResult handleRequest(final RemoveAccessibilityTagsRequest removeAccessibilityTagsRequest){
        log.info("Received RemoveTagsRequest{}",removeAccessibilityTagsRequest);

        String placeId = removeAccessibilityTagsRequest.getPlaceId();
        Set<String> tagsToRemove = removeAccessibilityTagsRequest.getTagsToRemove();

        placeDao.getPlace(placeId);

        Set<String> updatedList = placeDao.removeAccessibilityTagsFromPlace(placeId,tagsToRemove);
        List<String> newList = new ArrayList<>(updatedList);
        return RemoveAccessibilityTagsResult.builder()
                .withPlaces(newList)
                .build();

    }
}
