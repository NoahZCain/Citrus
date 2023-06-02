package com.nashss.se.citrusservice.models;

import java.util.Objects;
import java.util.Set;

public class PlacesModel {
    private final String placeId;
    private final String placeName;
    private final String placeAddress;
    private final Set<String> accessibilityInfo;
    private final Set<String> placeTypes;

    public PlacesModel(String placeId,String placeName,String placeAddress, Set<String> accessibilityInfo, Set<String> placeTypes){
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.accessibilityInfo = accessibilityInfo;
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
        return accessibilityInfo;
    }
    public Set<String> getPlaceTypes() {
        return placeTypes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlacesModel that = (PlacesModel) o;

        if (!Objects.equals(placeId, that.placeId)) return false;
        if (!placeName.equals(that.placeName)) return false;
        if (!placeAddress.equals(that.placeAddress)) return false;
        if (!accessibilityInfo.equals(that.accessibilityInfo)) return false;
        return placeTypes.equals(that.placeTypes);
    }

    @Override
    public int hashCode() {
        int result = placeId != null ? placeId.hashCode() : 0;
        result = 31 * result + placeName.hashCode();
        result = 31 * result + placeAddress.hashCode();
        result = 31 * result + accessibilityInfo.hashCode();
        result = 31 * result + placeTypes.hashCode();
        return result;
    }

    public static PlacesModel.Builder builder(){
        return new PlacesModel.Builder();
    }

    public static class Builder {
        private String placeId;
        private String placeName;
        private String placeAddress;
        private Set<String> accessibilityInfo;
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
        public Builder withAccessibilityInfo(Set<String> accessibilityInfo){
            this.accessibilityInfo = accessibilityInfo;
            return this;
        }
        public Builder withPlaceTypes(Set<String> placeTypes){
            this.placeTypes = placeTypes;
            return this;
        }
        public PlacesModel build(){
            return new PlacesModel(placeId,placeName,placeAddress,accessibilityInfo,placeTypes);
        }


    }
}
