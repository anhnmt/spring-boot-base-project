package com.example.baseproject.domains.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActionUserDTO implements Serializable {
    private Long userId;
    private String name;
    private String email;
}
