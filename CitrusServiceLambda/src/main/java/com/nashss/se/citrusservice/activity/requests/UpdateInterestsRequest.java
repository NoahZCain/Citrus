package com.nashss.se.citrusservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;
@JsonDeserialize(builder = UpdateInterestsRequest.Builder.class)
public class UpdateInterestsRequest {
    private final String userId;
    private final Set<String> userInterests;

    public UpdateInterestsRequest(String userId, Set<String> userInterests) {
        this.userId = userId;
        this.userInterests = userInterests;
    }

    public String getUserId() {
        return userId;
    }

    public Set<String> getUserInterests() {
        return userInterests;
    }

    @Override
    public String toString() {
        return "UpdateInterestsRequest{" +
                "userId='" + userId + '\'' +
                ", userInterests=" + userInterests +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{
        private String userId;
        private Set<String> userInterests;

        public Builder withUserId(String userId){
            this.userId = userId;
            return this;
        }
        public Builder withUserInterests(Set<String> userInterests){
            this.userInterests = userInterests;
            return this;
        }
        public UpdateInterestsRequest build(){
        return new UpdateInterestsRequest(userId,userInterests);
        }
    }
}
