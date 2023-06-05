package com.nashss.se.citrusservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = GetUserRequest.Builder.class)
public class GetUserRequest {
    private final String userId;

    public GetUserRequest(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return userId;
    }

    @Override
    public String toString() {
        return "GetUserRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
    @JsonPOJOBuilder
    public static class Builder{
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        public GetUserRequest build(){
            return new GetUserRequest(userId);
        }
    }
    public static Builder builder(){
        return new Builder();
    }
}
