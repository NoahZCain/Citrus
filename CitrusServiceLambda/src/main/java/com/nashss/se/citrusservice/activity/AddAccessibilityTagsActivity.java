package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.AddAccessibilityTagsRequest;
import com.nashss.se.citrusservice.dynamodb.PlaceDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class AddAccessibilityTagsActivity {
    private final Logger log = LogManager.getLogger();
    private final PlaceDao placeDao;

    @Inject
    public AddAccessibilityTagsActivity(PlaceDao placeDao){
        this.placeDao = placeDao;
    }


    public AddAccessibilityTagsRequest handleRequest(final AddAccessibilityTagsRequest addAccessibilityTagsRequest){
        log.info("Received AddAccessibilityTagsRequest");
    }
}
