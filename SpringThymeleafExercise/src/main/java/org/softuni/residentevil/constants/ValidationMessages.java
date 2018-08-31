package org.softuni.residentevil.constants;

public final class ValidationMessages {
    public static final String INVALID_DESCRIPTION_LENGTH_MESSAGE = "Description should be between 5 and 100 symbols";
    public static final String INVALID_NAME_LENGTH_MESSAGE = "Name should be between 3 and 10 symbols";
    public static final String INVALID_CREATOR_MESSAGE = "Creator should be either \"Corp\" or \"corp\"";
    public static final String INVALID_SIDE_EFFECTS_MESSAGE = "Side effects should have maximum of 50 symbols";
    public static final String INVALID_TURNOVER_RATE_MESSAGE = "Turnover Rate should be between 0 and 100";
    public static final String INVALID_HOURS_UNTIL_TURN_MESSAGE = "Turnover Rate should be between 0 and 100";

    public static final String NULL_NAME_MESSAGE = "Name cannot be null";
    public static final String NULL_DESCRIPTION_MESSAGE = "Description cannot be null";
    public static final String NULL_MUTATION_MESSAGE = "Mutation cannot be null";
    public static final String NULL_MAGNITUDE_MESSAGE = "Magnitude cannot be null";
    public static final String NULL_CAPITAL_MESSAGE = "Select at least 1 capital";
    public static final String NULL_RELEASED_ON_MESSAGE = "Enter date";
    public static final String NULL_MESSAGE = "Cannot be empty";

    public static final String INVALID_RELEASE_DATE_MESSAGE = "Release date cannot empty or after yesterday";

    private ValidationMessages() {
    }
}
