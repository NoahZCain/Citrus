package com.nashss.se.Citrus.exceptions;

public class PointOfInterestNotFoundException extends RuntimeException {

    /**
     * Exception with no message or cause.
     */
    public PointOfInterestNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public PointOfInterestNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public PointOfInterestNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public PointOfInterestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}