package com.nashss.se.Citrus.dynamodb.models;


import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "places")
public class Places {
    private String placeName;
    private String placeId;
    private String placeAddress;
    private Set<String> accessibilityTags;
    private Set<String> placeTypes;


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
    @DynamoDBAttribute(attributeName = "placeAddress")
    public Set<String> getAccessibilityTags() {
        return accessibilityTags;
    }

    public void setAccessibilityTags(Set<String> accessibilityTags) {
        this.accessibilityTags = accessibilityTags;
    }
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "PlaceTypeIndex", attributeName = "placeTypes")
    public Set<String> getPlaceTypes() {
        return placeTypes;
    }

    public void setPlaceTypes(Set<String> placeTypes) {
        this.placeTypes = placeTypes;
    }
}
