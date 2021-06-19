package com.example.baseproject.domains.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequest {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("slug")
    private String slug;
}
