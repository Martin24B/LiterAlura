package com.literAlura.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Author(
        @JsonProperty("name") String name,
        @JsonProperty("birth_year") int birthYear,
        @JsonProperty("death_year") Integer deathYear
) {}
