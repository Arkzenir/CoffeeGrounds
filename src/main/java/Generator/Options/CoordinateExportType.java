package Generator.Options;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CoordinateExportType {
    DISCRETE,
    CONTINUOUS;

    @JsonCreator
    public static CoordinateExportType fromString(String value) {
        return value == null ? null : CoordinateExportType.valueOf(value.toUpperCase());
    }
}
