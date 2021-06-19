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
@Table(name = "movie_genres")
public class MovieGenre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_genre_id")
    private Long movieGenreId;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "status", nullable = false)
    private Long status;

    public void setStatus(Status status) {
        this.status = status.getLong();
    }
}