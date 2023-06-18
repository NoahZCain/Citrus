package com.nashss.se.citrusservice.activity.results;

import com.nashss.se.citrusservice.models.PlaceModel;

import java.util.List;

public class RemoveAccessibilityTagsResult {

    private final List<String> placeList;

    private RemoveAccessibilityTagsResult(List<String> placeList){
        this.placeList = placeList;
    }

    public List<String> getPlaceList() {
        return placeList;
    }

    @Override
    public String toString() {
        return "RemoveAccessibilityTagsResult{" +
                "placeList=" + placeList +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private List<String> placeList;

        public Builder withPlaces(List<String> placeList){
            this.placeList = placeList;
            return this;
        }
        public RemoveAccessibilityTagsResult build(){
            return new RemoveAccessibilityTagsResult(placeList);
        }
    }
}
