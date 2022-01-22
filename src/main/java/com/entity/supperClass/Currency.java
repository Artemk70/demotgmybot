package com.entity.supperClass;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class Currency {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("NumCode")
    private String numCode;
    @JsonProperty("CharCode")
    private String charCode;
    @JsonProperty("Nominal")
    private String nominal;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private Double value;
    @JsonProperty("Previous")
    private Double previous;

    @Override
    public String toString() {
        return this.getClass().getCanonicalName().replaceAll("(readFromFile)\\.(currency)\\.", "").toUpperCase() +
                " (" + name + "): " + value + ".\n";
    }
}
