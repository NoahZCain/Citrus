package com.nashss.se.citrusservice.models;

import java.util.Objects;
import java.util.Set;

public class PlaceModel {
    private final String placeId;
    private final String placeName;
    private final String placeAddress;
    private final Set<String> accessibilityTags;
    private final Set<String> placeTypes;

    public PlaceModel(String placeId, String placeName, String placeAddress, Set<String> accessibilityTags, Set<String> placeTypes){
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.accessibilityTags = accessibilityTags;
        this.placeTypes = placeTypes;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public Set<String> getAccessibilityInfo() {
        return accessibilityTags;
    }
    public Set<String> getPlaceTypes() {
        return placeTypes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceModel that = (PlaceModel) o;

        if (!Objects.equals(placeId, that.placeId)) return false;
        if (!placeName.equals(that.placeName)) return false;
        if (!placeAddress.equals(that.placeAddress)) return false;
        if (!accessibilityTags.equals(that.accessibilityTags)) return false;
        return placeTypes.equals(that.placeTypes);
    }

    @Override
    public int hashCode() {
        int result = placeId != null ? placeId.hashCode() : 0;
        result = 31 * result + placeName.hashCode();
        result = 31 * result + placeAddress.hashCode();
        result = 31 * result + accessibilityTags.hashCode();
        result = 31 * result + placeTypes.hashCode();
        return result;
    }

    public static PlaceModel.Builder builder(){
        return new PlaceModel.Builder();
    }

    public static class Builder {
        private String placeId;
        private String placeName;
        private String placeAddress;
        private Set<String> accessibilityTags;
        private Set<String> placeTypes;

        public Builder withPlaceId(String placeId){
            this.placeId = placeId;
            return this;
        }
        public Builder withPlaceName(String placeName){
            this.placeName = placeName;
            return this;
        }
        public Builder withPlaceAddress(String placeAddress){
            this.placeAddress = placeAddress;
            return this;
        }
        public Builder withAccessibilityInfo(Set<String> accessibilityTags){
            this.accessibilityTags = accessibilityTags;
            return this;
        }
        public Builder withPlaceTypes(Set<String> placeTypes){
            this.placeTypes = placeTypes;
            return this;
        }
        public PlaceModel build(){
            return new PlaceModel(placeId,placeName,placeAddress,accessibilityTags,placeTypes);
        }


    }
}
