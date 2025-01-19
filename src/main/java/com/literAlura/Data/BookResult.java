package com.literAlura.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record BookResult(
        @JsonProperty("id") int id,
        @JsonProperty("title") String title,
        @JsonProperty("authors") List<Author> authors,
        @JsonProperty("languages") List<String> languages,
        @JsonProperty("download_count") int downloadCount
) {}
