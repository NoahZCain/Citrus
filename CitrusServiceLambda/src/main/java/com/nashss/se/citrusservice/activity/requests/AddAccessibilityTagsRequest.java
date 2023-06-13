package com.nashss.se.citrusservice.activity.requests;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = AddAccessibilityTagsRequest.Builder.class)
public class AddAccessibilityTagsRequest {
    private final String placeId;
    private final Set<String> accessibilityTagsToAdd;

    private AddAccessibilityTagsRequest(String placeId, Set<String> accessibilityTagsToAdd){
        this.placeId = placeId;
        this.accessibilityTagsToAdd = accessibilityTagsToAdd;
    }
    public String getPlaceId(){
        return placeId;
    }
    public Set<String> getAccessibilityTagsToAdd(){
        return accessibilityTagsToAdd;
    }

    @Override
    public String toString() {
        return "AddAccessibilityTagsRequest{" +
                "placeId='" + placeId + '\'' +
                ", accessibilityTagsToAdd=" + accessibilityTagsToAdd +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{
        private String placeId;
        private Set<String> accessibilityTagsToAdd;

    public Builder withPlaceId(String placeId){
        this.placeId = placeId;
        return this;
    }
    public Builder withAccessibilityTagsToAdd(Set<String> accessibilityTagsToAdd){
        this.accessibilityTagsToAdd = accessibilityTagsToAdd;
        return this;
    }
    public AddAccessibilityTagsRequest build(){
            return new AddAccessibilityTagsRequest(placeId, accessibilityTagsToAdd);
    }
    }
}
