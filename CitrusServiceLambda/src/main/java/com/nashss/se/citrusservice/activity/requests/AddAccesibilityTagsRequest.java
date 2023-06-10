package com.nashss.se.citrusservice.activity.requests;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = AddAccesibilityTagsRequest.Builder.class)
public class AddAccesibilityTagsRequest {
    private final String placeId;
    private final Set<String> accessibilityTagsToAdd;

    private AddAccesibilityTagsRequest(String placeId, Set<String> accessibilityTagsToAdd){
        this.placeId = placeId;
        this.accessibilityTagsToAdd = accessibilityTagsToAdd;
    }
    public String getId(){
        return placeId;
    }
    public Set<String> getAccessibilityTagsToAdd(){
        return accessibilityTagsToAdd;
    }
    @Override
    public String toString(){
        return "AddAccessibilityTagsRequest{" +
        "placeId= '" + placeId + '\'' + 
        ", accessibilityTagsToAdd='" + accessibilityTagsToAdd + '\'' +
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
    public AddAccesibilityTagsRequest build(){
            return new AddAccesibilityTagsRequest(placeId, accessibilityTagsToAdd);
    }
    }
}
