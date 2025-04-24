package Generator.Options;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GeneratorType {
    SIMPLE,
    PERLIN;

    @JsonCreator
    public static GeneratorType fromString(String value) {
        return value == null ? null : GeneratorType.valueOf(value.toUpperCase());
    }
}