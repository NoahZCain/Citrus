package com.nashss.se.citrusservice.activity.results;
import com.nashss.se.citrusservice.models.PlaceModel;


public class GetPlaceResult {
    private final PlaceModel placeModel;

    public GetPlaceResult(PlaceModel placeModel){
        this.placeModel = placeModel;
    }
    public PlaceModel getPlacesModel(){
        return placeModel;
    }

    @Override
    public String toString() {
        return "GetPlaceResult{" +
                "placesModel=" + placeModel +
                '}';
    }
    public static class Builder{
        private PlaceModel placeModel;

        public Builder withPlaceModel(PlaceModel placeModel){
            this.placeModel = placeModel;
            return this;
        }

        public GetPlaceResult build(){
            return new GetPlaceResult(placeModel);
        }
    }
    public static Builder builder(){
        return new Builder();
    }

}
