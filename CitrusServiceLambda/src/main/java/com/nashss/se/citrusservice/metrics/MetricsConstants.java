package com.nashss.se.citrusservice.metrics;

/**
 * Constant values for use with metrics.
 */
public class MetricsConstants {
    public static final String GETUSER_PROFILENOTFOUND_COUNT = "GetUser.UserNotFoundException.Count";
    public static final String GETPLACE_PLACENOTFOUND_COUNT = "GetPlace.PlaceNotFoundException.Count";
    public static final String UPDATEEVENT_INVALIDATTRIBUTEVALUE_COUNT =
            "UpdateEvent.InvalidAttributeValueException.Count";
    public static final String SERVICE = "Service";
    public static final String SERVICE_NAME = "Citrus";
    public static final String NAMESPACE_NAME = "Capstone/Citrus";
    public static final String UPDATEPLACE_INVALIDATTRIBUTECHANGE_COUNT =
            "UpdatePlace.InvalidAttributeValueException.Count";
    public static final String UPDATEUSER_INVALIDATTRIBUTECHANGE_COUNT = "UpdateUser.InvalidAttributeChangeException.Count";
    public static final String UPDATEUSER_INVALIDATTRIBUTEVALUE_COUNT = "UpdateUser.InvalidAttributeValueException.Count";
}