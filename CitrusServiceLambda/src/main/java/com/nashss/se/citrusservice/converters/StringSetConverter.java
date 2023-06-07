package com.nashss.se.citrusservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.HashSet;
import java.util.Set;

public class StringSetConverter  implements DynamoDBTypeConverter<String, Set<String>> {
    /**
     * Turns an object of type T into an object of type S.
     *
     * @param object
     */
    @Override
    public String convert(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        return String.join(",", set);
    }

    /**
     * Turns an object of type S into an object of type T.
     *
     * @param object
     */
    @Override
    public Set<String> unconvert(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        String[] items = string.split(",");
        Set<String> set = new HashSet<>();
        for (String item : items) {
            set.add(item.trim());
        }
        return set;
    }
}
