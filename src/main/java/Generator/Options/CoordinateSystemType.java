package Generator.Options;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CoordinateSystemType {
    CONTINUOUS,
    DISCRETE;

    @JsonCreator
    public static CoordinateSystemType fromString(String value) {
        return value == null ? null : CoordinateSystemType.valueOf(value.toUpperCase());
    }
}