package com.literAlura.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Book(
        @JsonProperty("id") int id,
        @JsonProperty("title") String title,
        @JsonProperty("authors") List<Author> authors, 
        @JsonProperty("languages") String language,
        @JsonProperty("download_count") int downloadCount
) {

}