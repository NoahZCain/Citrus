package com.nashss.se.citrusservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;

@JsonDeserialize(builder = UpdateUserRequest.Builder.class)
public class UpdateUserRequest {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String dateOfBirth;
    private final Set<String> userInterests;

    private UpdateUserRequest(String userId,String firstName,String lastName, String gender,String dateOfBirth,Set<String> userInterests){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.userInterests = userInterests;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Set<String> getUserInterests() {
        return userInterests;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", userInterests=" + userInterests +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }
}
