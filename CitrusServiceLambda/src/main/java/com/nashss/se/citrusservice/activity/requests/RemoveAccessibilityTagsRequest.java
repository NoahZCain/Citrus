package com.nashss.se.citrusservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

@JsonDeserialize(builder = RemoveAccessibilityTagsRequest.Builder.class)
public class RemoveAccessibilityTagsRequest {
    private final String placeId;
    private final Set<String> tagsToRemove;

    public RemoveAccessibilityTagsRequest(String placeId, Set<String> tagsToRemove){
        this.placeId = placeId;
        this.tagsToRemove = tagsToRemove;
    }

    public String getPlaceId() {
        return placeId;
    }

    public Set<String> getTagsToRemove() {
        return tagsToRemove;
    }

    @Override
    public String toString() {
        return "RemoveAccessibilityTagsRequest{" +
                "placeId='" + placeId + '\'' +
                ", tagsToRemove=" + tagsToRemove +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String placeId;
        private Set<String> tagsToRemove;

        public Builder withPlaceId(String placeId){
            this.placeId = placeId;
            return this;
        }
        public Builder withTagsToRemove(Set<String> tagsToRemove){
            this.tagsToRemove = tagsToRemove;
            return this;
        }

        public RemoveAccessibilityTagsRequest build(){
            return new RemoveAccessibilityTagsRequest(placeId,tagsToRemove);
        }
    }
}
