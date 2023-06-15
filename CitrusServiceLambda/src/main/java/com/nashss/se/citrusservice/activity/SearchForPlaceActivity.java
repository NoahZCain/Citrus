package com.nashss.se.citrusservice.activity;

import com.nashss.se.citrusservice.activity.requests.SearchForPlaceRequest;
import com.nashss.se.citrusservice.activity.results.SearchForPlaceResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.PlaceDao;
import com.nashss.se.citrusservice.dynamodb.models.Place;
import com.nashss.se.citrusservice.models.PlaceModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

import java.util.List;

import static com.nashss.se.citrusservice.utils.NullUtils.ifNull;

public class SearchForPlaceActivity {

    private final Logger log = LogManager.getLogger();
    private final PlaceDao placeDao;


    @Inject
    public SearchForPlaceActivity(PlaceDao placeDao){
        this.placeDao = placeDao;
    }

    public SearchForPlaceResult handleRequest(final SearchForPlaceRequest searchForPlaceRequest){
        log.info("Received SearchForPlaceRequest{}",searchForPlaceRequest);

        String criteria = ifNull(searchForPlaceRequest.getplaceName(),"");
        String[] criteriaArray = criteria.isBlank() ? new String[0] : criteria.split("\\s");

        List<Place> result = placeDao.searchForPlace(criteriaArray);
        List<PlaceModel> placeModels = new ModelConverter().toPlaceModelList(result);

        return SearchForPlaceResult.builder()
                .withPlaces(placeModels)
                .build();
    }
}
