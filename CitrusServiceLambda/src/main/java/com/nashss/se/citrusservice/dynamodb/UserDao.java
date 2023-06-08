package com.nashss.se.citrusservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.exceptions.InvalidAttributeException;
import com.nashss.se.citrusservice.exceptions.UserNotFoundException;
import com.nashss.se.citrusservice.metrics.MetricsConstants;
import com.nashss.se.citrusservice.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class UserDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public UserDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher){
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }
    public User getUser(String userId){
        User user = this.dynamoDBMapper.load(User.class, userId);
        if(user == null){
            metricsPublisher.addCount(MetricsConstants.GETUSER_PROFILENOTFOUND_COUNT, 1);
            throw new UserNotFoundException("User not found");
        }

        return user;
    }

    public Set<String> addTagsToUser(String tag, String UserId) {
        User userToAddEventTo = this.getUser(UserId);
        Set<String> tagsAlreadyStored = userToAddEventTo.getUserInterests();
        tagsAlreadyStored.add(tag);
        userToAddEventTo.setUserInterests(tagsAlreadyStored);
        this.dynamoDBMapper.save(userToAddEventTo);
        return tagsAlreadyStored;
    }
    public Set<String> removeTagsFromUser(String tag, String UserId) {
        if (UserId == null || UserId.isEmpty()) {
            throw new InvalidAttributeException("The entered email address is invalid. Please try again.");
        }
        User User = getUser(UserId);
        Set<String> userInterests = User.getUserInterests();
        userInterests.remove(tag);
        User.setUserInterests(userInterests);
        this.dynamoDBMapper.save(User);
        return new HashSet<>(userInterests);
    }

    public User saveUser(boolean isNew, String userId,String firstName, String lastName, String gender, String dateOfBirth, Set<String> userInterests) {
        User saveUser = new User();
        if(isNew) {
            saveUser.setUserId(userId);
            saveUser.setFirstName(firstName);
            saveUser.setLastName(lastName);
            saveUser.setGender(gender);
            saveUser.setDateOfBirth(dateOfBirth);
            saveUser.setUserInterests(userInterests);
            this.dynamoDBMapper.save(saveUser);
        } else {
            this.getUser(userId);
            saveUser.setUserId(userId);
            saveUser.setUserInterests(userInterests);
            if(firstName != null || !firstName.isEmpty()){
                saveUser.setFirstName(firstName);
            }
            if(lastName != null || !lastName.isEmpty()){
                saveUser.setLastName(lastName);
            }
            if(gender != null || !gender.isEmpty()){
                saveUser.setGender(gender);
            }
            if(dateOfBirth != null || !dateOfBirth.isEmpty()){
                saveUser.setDateOfBirth(dateOfBirth);
            }
            this.dynamoDBMapper.save(saveUser);
        }
        return saveUser;
    }
}
