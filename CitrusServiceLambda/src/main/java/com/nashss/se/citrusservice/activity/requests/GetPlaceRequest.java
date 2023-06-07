package com.nashss.se.citrusservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetPlaceRequest.Builder.class)
public class GetPlaceRequest {
    private final String placeId;

    public GetPlaceRequest(String placeId){
        this.placeId = placeId;
    }
    public String getPlaceId(){
        return placeId;
    }

    @Override
    public String toString() {
        return "GetPlaceRequest{" +
                "placeId='" + placeId + '\'' +
                '}';
    }
    @JsonPOJOBuilder
    public static class Builder{
        private String placeId;

        public Builder withId(String placeId){
            this.placeId = placeId;
            return this;
        }
        public GetPlaceRequest build(){
            return new GetPlaceRequest(placeId);

        }
    }
    public static Builder builder(){
        return new Builder();
    }
}
