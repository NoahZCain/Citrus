package com.nashss.se.citrusservice.activity.results;

import com.nashss.se.citrusservice.models.PlaceModel;

import java.util.List;

public class SearchForPlaceResult {

    private final List<PlaceModel> places;

    private SearchForPlaceResult(List<PlaceModel> places){
        this.places = places;
    }
    public List<PlaceModel> getPlaces(){
        return places;
    }

    @Override
    public String toString() {
        return "SearchForPlaceResult{" +
                "places=" + places +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private List<PlaceModel> places;

        public Builder withPlaces(List<PlaceModel> places){
            this.places = places;
            return this;
        }

        public SearchForPlaceResult build(){
            return new SearchForPlaceResult(places);
        }
    }
}
