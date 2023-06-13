package com.nashss.se.citrusservice.activity.results;

import java.util.Set;

public class AddAccessibilityTagsResult {

    private final Set<String> accessibilityTags;

    private AddAccessibilityTagsResult(Set<String> accessibilityTags){
        this.accessibilityTags = accessibilityTags;
    }

    public Set<String> getAccessibilityTags(){
        return accessibilityTags;
    }

    @Override
    public String toString() {
        return "AddAccessibilityTagsResult{" +
                "accessibilityTags=" + accessibilityTags +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Set<String> accessibilityTags;

        public Builder withTags(Set<String> accessibilityTags){
            this.accessibilityTags = accessibilityTags;
            return this;
        }
        public AddAccessibilityTagsResult build(){
            return new AddAccessibilityTagsResult(accessibilityTags);
        }
    }
}
