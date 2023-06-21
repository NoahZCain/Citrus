package ActivityTests;

import com.nashss.se.citrusservice.activity.GetUserActivity;
import com.nashss.se.citrusservice.activity.requests.GetUserRequest;
import com.nashss.se.citrusservice.activity.results.GetUserResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.UserDao;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetUserActivityTest {
    @Mock
    private UserDao userDao;

    private GetUserActivity activity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new GetUserActivity(userDao);
    }

    @Test
    public void testHandleRequest() {
        // Mocking input request
        String userId = "user123";
        GetUserRequest request = new GetUserRequest(userId);

        // Mocking the behavior of the userDao
        User user = new User();
        user.setUserId(userId);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");

        when(userDao.getUser(userId)).thenReturn(user);

        // Calling the activity's handleRequest method
        GetUserResult result = activity.handleRequest(request);

        // Verifying the userDao method was called with the correct userId
        verify(userDao, times(1)).getUser(userId);

        // Verifying the returned UserModel matches the expected values
        UserModel expectedUserModel = new ModelConverter().toUserModel(user);
        assertEquals(expectedUserModel, result.getUserModel());
    }
}

