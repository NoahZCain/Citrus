package ActivityTests;

import com.nashss.se.citrusservice.activity.UpdateUserActivity;
import com.nashss.se.citrusservice.activity.requests.UpdateUserRequest;
import com.nashss.se.citrusservice.activity.results.UpdateUserResult;
import com.nashss.se.citrusservice.converters.ModelConverter;
import com.nashss.se.citrusservice.dynamodb.UserDao;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.models.UserModel;
import com.nashss.se.citrusservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UpdateUserActivityTest {
    @Mock
    private UserDao userDao;

    @Mock
    private MetricsPublisher metricsPublisher;

    private UpdateUserActivity activity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new UpdateUserActivity(userDao, metricsPublisher);
    }

    @Test
    public void testHandleRequest() {
        // Mocking input request
        String userId = "user123";
        String firstName = "John";
        String lastName = "Doe";
        String gender = "Male";
        String dateOfBirth = "1990-01-01";
        Set<String> userInterests = new HashSet<>();
        userInterests.add("Sports");
        userInterests.add("Music");
        UpdateUserRequest request = new UpdateUserRequest(userId, firstName, lastName, gender, dateOfBirth, userInterests);

        // Mocking the behavior of the userDao
        User newUser = new User();
        newUser.setUserId(userId);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setGender(gender);
        newUser.setDateOfBirth(dateOfBirth);
        newUser.setUserInterests(userInterests);
        when(userDao.saveUser(eq(false), eq(userId), eq(firstName), eq(lastName), eq(gender), eq(dateOfBirth), eq(userInterests)))
                .thenReturn(newUser);

        // Calling the method under test
        UpdateUserResult result = activity.handleRequest(request);

        // Verifying the interactions and assertions
        verify(userDao, times(1)).saveUser(eq(false), eq(userId), eq(firstName), eq(lastName), eq(gender), eq(dateOfBirth), eq(userInterests));

        // Convert the User object to UserModel
        UserModel expectedUserModel = new ModelConverter().toUserModel(newUser);

        assertEquals(expectedUserModel, result.getUserModel());
    }
}
