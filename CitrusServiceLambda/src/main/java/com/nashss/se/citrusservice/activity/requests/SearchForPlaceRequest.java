package com.nashss.se.citrusservice.activity.requests;

public class SearchForPlaceRequest {
    private final String criteria;

    private SearchForPlaceRequest(String criteria) {
        this.criteria = criteria;
    }

    public String getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "SearchForPlaceRequest{" +
                "placeName='" + criteria + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String criteria;

        public Builder withCriteria(String criteria) {
            this.criteria = criteria;
            return this;
        }

        public SearchForPlaceRequest build() {
            return new SearchForPlaceRequest(criteria);
        }
    }
}
