package com.example.baseproject.domains.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {
    @NotNull
    private String name;

    @NotNull
    private String slug;
}
