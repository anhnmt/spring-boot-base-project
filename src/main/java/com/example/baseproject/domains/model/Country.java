package com.example.baseproject.domains.model;

import com.example.baseproject.common.utils.Status;
import lombok.*;

import javax.persistence.*;

import static com.example.baseproject.common.utils.Status.ACTIVE;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(name = "status", nullable = false)
    private Long status;

    public void setStatus(Status status) {
        this.status = status.getLong();
    }
}
