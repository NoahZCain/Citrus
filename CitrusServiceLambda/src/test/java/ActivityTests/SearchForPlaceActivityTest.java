package ActivityTests;


import com.nashss.se.citrusservice.activity.SearchForPlaceActivity;
import com.nashss.se.citrusservice.activity.requests.SearchForPlaceRequest;
import com.nashss.se.citrusservice.activity.results.SearchForPlaceResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.PlaceDao;
import com.nashss.se.citrusservice.dynamodb.models.Place;
import com.nashss.se.citrusservice.models.PlaceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchForPlaceActivityTest {
    @Mock
    private PlaceDao placeDao;

    private SearchForPlaceActivity activity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new SearchForPlaceActivity(placeDao);
    }

    @Test
    public void testHandleRequest() {
        // Mocking input request
        String placeName = "Example Place";
        SearchForPlaceRequest request = new SearchForPlaceRequest(placeName);

        // Mocking the behavior of the placeDao
        List<Place> places = new ArrayList<>();
        Place place1 = new Place();
        place1.setName("Example Place 1");
        places.add(place1);
        Place place2 = new Place();
        place2.setName("Example Place 2");
        places.add(place2);
        when(placeDao.searchForPlace(any())).thenReturn(places);

        // Calling the method under test
        SearchForPlaceResult result = activity.handleRequest(request);

        // Verifying the interactions and assertions
        verify(placeDao, times(1)).searchForPlace(any());

        // Convert the Place list to PlaceModel list
        List<PlaceModel> expectedPlaceModels = new ModelConverter().toPlaceModelList(places);

        assertEquals(expectedPlaceModels, result.getPlaces());
    }
}
