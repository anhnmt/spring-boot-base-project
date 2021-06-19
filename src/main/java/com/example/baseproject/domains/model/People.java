package com.example.baseproject.domains.model;

import com.example.baseproject.common.utils.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "peoples")
public class People extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "people_id")
    private Long profileId;

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
