package com.nashss.se.citrusservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.citrusservice.dynamodb.models.User;
import com.nashss.se.citrusservice.exceptions.InvalidAttributeException;
import com.nashss.se.citrusservice.exceptions.UserNotFoundException;
import com.nashss.se.citrusservice.metrics.MetricsConstants;
import com.nashss.se.citrusservice.metrics.MetricsPublisher;

import javax.inject.Inject;
import java.util.HashSet;
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

    public User addInterestsToUser(Set<String> interests, String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new InvalidAttributeException("The entered email address is invalid. Please try again.");
        }
        User userToAddInterest = getUser(userId);
        Set<String> interestsAlreadyStored = userToAddInterest.getUserInterests();

        interestsAlreadyStored.addAll(interests);
        userToAddInterest.setUserInterests(interestsAlreadyStored);
        dynamoDBMapper.save(userToAddInterest);
        return userToAddInterest;
    }
    public Set<String> removeTagsFromUser(String tag, String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new InvalidAttributeException("The entered email address is invalid. Please try again.");
        }
        User user = getUser(userId);
        Set<String> userInterests = user.getUserInterests();
        userInterests.remove(tag);
        user.setUserInterests(userInterests);
        this.dynamoDBMapper.save(user);
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
