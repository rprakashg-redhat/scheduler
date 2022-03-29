package com.redhat.demos.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "speakers")
@Entity
public class Speaker extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "speaker_id", updatable = false, nullable = false)
    @Getter
    @Setter
    private UUID id;

    @Column(name = "speaker_name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "speaker_title", nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(name = "company_name", nullable = false)
    @Getter
    @Setter
    private String company;

    @Column(name = "email_address", nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(name = "twitter")
    @Getter
    @Setter
    private String twitter;

    @Column(name = "linkedin")
    @Getter
    @Setter
    private String linkedIn;
}
