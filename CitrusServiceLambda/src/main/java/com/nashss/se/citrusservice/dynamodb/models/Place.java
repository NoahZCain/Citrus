package com.nashss.se.citrusservice.dynamodb.models;


import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.nashss.se.citrusservice.converters.StringSetConverter;

@DynamoDBTable(tableName = "place")
public class Place {
    private String placeName;
    private String placeId;
    private String placeAddress;
    private Set<String> accessibilityTags;
    private Set<String> placeTypes;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "PlaceNameIndex", attributeName = "placeName")
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    @DynamoDBHashKey(attributeName = "placeId")
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
    @DynamoDBAttribute(attributeName = "placeAddress")
    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }
    @DynamoDBAttribute(attributeName = "accessibilityTags")
    public Set<String> getAccessibilityTags() {
        return accessibilityTags;
    }

    public void setAccessibilityTags(Set<String> accessibilityTags) {
        this.accessibilityTags = accessibilityTags;
    }
    @DynamoDBAttribute(attributeName = "placeTypes")
    public Set<String> getPlaceTypes() {
        return placeTypes;
    }

    public void setPlaceTypes(Set<String> placeTypes) {
        this.placeTypes = placeTypes;
    }
}
