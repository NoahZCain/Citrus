package com.nashss.se.citrusservice.activity.requests;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = AddAccessibilityTagsRequest.Builder.class)
public class AddAccessibilityTagsRequest {
   private final String placeId;
   private final Set<String> accessibilityTags;

   public AddAccessibilityTagsRequest(String placeId, Set<String> accessibilityTags){
       this.placeId = placeId;
       this.accessibilityTags = accessibilityTags;
   }

   public String getPlaceId(){
       return placeId;
   }
   public Set<String> getAccessibilityTags(){
       return accessibilityTags;
   }

    @Override
    public String toString() {
        return "AddAccessibilityTagsRequest{" +
                "placeId='" + placeId + '\'' +
                ", accessibilityTags=" + accessibilityTags +
                '}';
    }

    public static Builder builder(){
       return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{

       private String placeId;
       private Set<String> accessibilityTags;

       public Builder withPlaceId(String placeId){
           this.placeId = placeId;
           return this;
       }

       public Builder withAccessibilityTags(Set<String> accessibilityTags){
           this.accessibilityTags = accessibilityTags;
           return this;
       }

       public AddAccessibilityTagsRequest build(){
           return new AddAccessibilityTagsRequest(placeId, accessibilityTags);
       }
    }
}
