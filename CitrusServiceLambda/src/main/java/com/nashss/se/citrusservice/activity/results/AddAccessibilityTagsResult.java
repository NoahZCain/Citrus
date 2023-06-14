package com.nashss.se.citrusservice.activity.results;

import com.nashss.se.citrusservice.models.PlaceModel;

import java.util.Set;

public class AddAccessibilityTagsResult {

    private final PlaceModel placeModel;


    private AddAccessibilityTagsResult(PlaceModel placeModel){
        this.placeModel = placeModel;
    }

    public PlaceModel getPlaceModel() {
        return placeModel;
    }

    @Override
    public String toString() {
        return "AddAccessibilityTagsResult{" +
                "placeModel=" + placeModel +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private PlaceModel placeModel;

        public Builder withPlaceModel(PlaceModel placeModel){
            this.placeModel = placeModel;
            return this;
        }

        public AddAccessibilityTagsResult build(){
            return new AddAccessibilityTagsResult(placeModel);
        }
    }
}
