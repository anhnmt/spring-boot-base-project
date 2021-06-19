package com.example.baseproject.domains.model;

import com.example.baseproject.common.utils.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static com.example.baseproject.common.utils.Status.ACTIVE;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie_countries")
public class MovieCountry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_country_id")
    private Long movieCountryId;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "status", nullable = false)
    private Long status;

    public void setStatus(Status status) {
        this.status = status.getLong();
    }
}
