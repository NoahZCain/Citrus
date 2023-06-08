package com.nashss.se.citrusservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


import java.util.Set;

@JsonDeserialize(builder = CreateUserRequest.Builder.class)
public class CreateUserRequest {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String dateOfBirth;
    private final Set<String> userInterests;

    public CreateUserRequest(String userId, String firstName, String lastName, String gender, String dateOfBirth, Set<String> userInterests) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.userInterests = userInterests;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", userInterests=" + userInterests +
                '}';
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
    public static Builder builder(){
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{
        private  String userId;
        private  String firstName;
        private  String lastName;
        private  String gender;
        private  String dateOfBirth;
        private  Set<String> userInterests;

        public Builder withUserId(String userId){
            this.userId = userId;
            return this;
        }
        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public Builder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public Builder withGender(String gender){
            this.gender = gender;
            return this;
        }
        public Builder withDateOfBirth(String dateOfBirth){
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public Builder withUserInterests(Set<String> userInterests){
            this.userInterests = userInterests;
            return this;
        }
        public CreateUserRequest build(){
            return new CreateUserRequest(userId,firstName,lastName,gender,dateOfBirth,userInterests);
        }
    }
}
