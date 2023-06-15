package com.nashss.se.citrusservice.activity.requests;

public class SearchForPlaceRequest {
    private final String placeName;

    private SearchForPlaceRequest(String placeName) {
        this.placeName = placeName;
    }

    public String getplaceName() {
        return placeName;
    }

    @Override
    public String toString() {
        return "SearchForPlaceRequest{" +
                "placeName='" + placeName + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String placeName;

        public Builder withplaceName(String placeName) {
            this.placeName = placeName;
            return this;
        }

        public SearchForPlaceRequest build() {
            return new SearchForPlaceRequest(placeName);
        }
    }
}
