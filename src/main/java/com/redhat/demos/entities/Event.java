package com.redhat.demos.entities;

import com.redhat.demos.enums.EventType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
@Entity
public class Event extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "event_id", updatable = false, nullable = false)
    @Getter
    @Setter
    private UUID id;

    @Column(name = "event_name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "event_description", nullable = false, columnDefinition = "TEXT")
    @Getter
    @Setter
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    @Getter
    @Setter
    private EventType type;

    @Column(name = "event_location", nullable = false)
    @Getter
    @Setter
    private String location;

    @Column(columnDefinition = "text[]")
    @Type(type = "com.redhat.demos.utils.PostgreSqlStringArrayType")
    @Getter
    @Setter
    private String[] audience;

    @Column(columnDefinition = "text[]")
    @Type(type = "com.redhat.demos.utils.PostgreSqlStringArrayType")
    @Getter
    @Setter
    private String[] topics;
}
