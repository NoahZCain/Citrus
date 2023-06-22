// package ActivityTests;
// import com.nashss.se.citrusservice.activity.GetPlaceActivity;
//         import com.nashss.se.citrusservice.activity.requests.GetPlaceRequest;
//         import com.nashss.se.citrusservice.activity.results.GetPlaceResult;
//         import com.nashss.se.citrusservice.converters.ModelConverter;
//         import com.nashss.se.citrusservice.dynamodb.PlaceDao;
//         import com.nashss.se.citrusservice.dynamodb.models.Place;
//         import com.nashss.se.citrusservice.models.PlaceModel;
//         import org.junit.jupiter.api.BeforeEach;
//         import org.junit.jupiter.api.Test;
//         import org.mockito.Mock;
//         import org.mockito.MockitoAnnotations;

//         import static org.junit.jupiter.api.Assertions.assertEquals;
//         import static org.mockito.Mockito.*;

// public class GetPlaceActivityTest {
//     @Mock
//     private PlaceDao placeDao;

//     private GetPlaceActivity activity;

//     @BeforeEach
//     public void setup() {
//         MockitoAnnotations.openMocks(this);
//         activity = new GetPlaceActivity(placeDao);
//     }

//     @Test
//     public void testHandleRequest() {
//         // Mocking input request
//         String placeId = "123";
//         GetPlaceRequest request = new GetPlaceRequest(placeId);

//         // Mocking the behavior of the placeDao
//         Place place = new Place();
//         when(placeDao.getPlace(placeId)).thenReturn(place);

//         // Calling the method under test
//         GetPlaceResult result = activity.handleRequest(request);

//         // Verifying the interactions and assertions
//         verify(placeDao, times(1)).getPlace(placeId);

//         // Convert the Place to PlaceModel
//         PlaceModel expectedPlaceModel = new ModelConverter().toPlaceModel(place);

//         assertEquals(expectedPlaceModel, result.getPlacesModel());
//     }
// }
