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
@Table(name = "movie_peoples")
public class MoviePeople implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_people_id")
    private Long moviePeopleId;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "status", nullable = false)
    private Long status;

    public void setStatus(Status status) {
        this.status = status.getLong();
    }
}