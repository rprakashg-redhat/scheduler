package com.redhat.demos.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sessions")
@Entity
public class Session extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "session_id", updatable = false, nullable = false)
    @Getter
    @Setter
    private UUID id;

    @Column(name = "session_title", nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(name = "session_start", nullable = false)
    @Getter
    @Setter
    private LocalDateTime start;

    @Column(name = "session_end", nullable = false)
    @Getter
    @Setter
    private LocalDateTime end;

    @Column(name = "session_description", nullable = false, columnDefinition = "TEXT")
    @Getter
    @Setter
    private String description;

    @Column(name = "event_id", nullable = false)
    @Getter
    @Setter
    private UUID event;

    @Column(name = "speaker_id", nullable = false)
    @Getter
    @Setter
    private UUID speaker;
}
