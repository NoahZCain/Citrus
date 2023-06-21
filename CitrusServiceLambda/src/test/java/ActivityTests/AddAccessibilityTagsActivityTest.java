package ActivityTests;

import com.nashss.se.citrusservice.activity.AddAccessibilityTagsActivity;
import com.nashss.se.citrusservice.activity.requests.AddAccessibilityTagsRequest;
import com.nashss.se.citrusservice.activity.results.AddAccessibilityTagsResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.PlaceDao;
import com.nashss.se.citrusservice.dynamodb.models.Place;
import com.nashss.se.citrusservice.models.PlaceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddAccessibilityTagsActivityTest {
    @Mock
    private PlaceDao placeDao;

    private AddAccessibilityTagsActivity activity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new AddAccessibilityTagsActivity(placeDao);
    }

    @Test
    public void testHandleRequest() {
        // Mocking input request
        String placeId = "123";
        Set<String> tagsToAdd = new HashSet<>();
        tagsToAdd.add("tag1");
        tagsToAdd.add("tag2");
        AddAccessibilityTagsRequest request = new AddAccessibilityTagsRequest(placeId, tagsToAdd);

        // Mocking the behavior of the placeDao
        Place place = new Place();
        when(placeDao.getPlace(placeId)).thenReturn(place);
        when(placeDao.addTagsToPlace(tagsToAdd, placeId)).thenReturn(place);

        // Calling the method under test
        AddAccessibilityTagsResult result = activity.handleRequest(request);

        // Verifying the interactions and assertions
        verify(placeDao, times(1)).getPlace(placeId);
        verify(placeDao, times(1)).addTagsToPlace(tagsToAdd, placeId);

        // Convert the Place model to PlaceModel
        PlaceModel expectedPlaceModel = new ModelConverter().toPlaceModel(place);

        assertEquals(expectedPlaceModel, result.getPlaceModel());
    }
}
