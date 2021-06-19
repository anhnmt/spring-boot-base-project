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
@Table(name = "movies")
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "original_title", unique = true, nullable = false)
    private String originalTitle;

    @Column(name = "title", unique = true)
    private String title;

    @Column(unique = true, nullable = false)
    private String slug;

    private String poster;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "release_year")
    private String releaseYear;

    private String rating;

    private String description;

    @Column(name = "status", nullable = false)
    private Long status;

    public void setStatus(Status status) {
        this.status = status.getLong();
    }
}
