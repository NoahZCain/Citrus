package ActivityTests;

import com.nashss.se.citrusservice.activity.RemoveAccessibilityTagsActivity;
import com.nashss.se.citrusservice.activity.requests.RemoveAccessibilityTagsRequest;
import com.nashss.se.citrusservice.activity.results.RemoveAccessibilityTagsResult;
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

public class RemoveAccessibilityTagsActivityTest {
    @Mock
    private PlaceDao placeDao;

    private RemoveAccessibilityTagsActivity activity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new RemoveAccessibilityTagsActivity(placeDao);
    }

    @Test
    public void testHandleRequest() {
        // Mocking input request
        String placeId = "123";
        Set<String> tagsToRemove = new HashSet<>();
        tagsToRemove.add("tag1");
        tagsToRemove.add("tag2");
        RemoveAccessibilityTagsRequest request = new RemoveAccessibilityTagsRequest(placeId, tagsToRemove);

        // Mocking the behavior of the placeDao
        Place place = new Place();
        when(placeDao.getPlace(placeId)).thenReturn(place);
        when(placeDao.removeAccessibilityTagsFromPlace(placeId, tagsToRemove)).thenReturn(place);

        // Calling the method under test
        RemoveAccessibilityTagsResult result = activity.handleRequest(request);

        // Verifying the interactions and assertions
        verify(placeDao, times(1)).getPlace(placeId);
        verify(placeDao, times(1)).removeAccessibilityTagsFromPlace(placeId, tagsToRemove);

        // Convert the Place to PlaceModel
        PlaceModel expectedPlaceModel = new ModelConverter().toPlaceModel(place);

        assertEquals(expectedPlaceModel, result.getPlaceModel());
    }
}
