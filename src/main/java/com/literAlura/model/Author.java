package com.literAlura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.literAlura.literAlura.AuthorData;

public record Author(
        @JsonProperty("name") String name,
        @JsonProperty("birth_year") int birthYear,
        @JsonProperty("death_year") Integer deathYear
) {
    public static Author fromAuthorData(AuthorData authorData) {
        return new Author(authorData.getName(), authorData.getBirthYear(), authorData.getDeathYear());
    }
}
