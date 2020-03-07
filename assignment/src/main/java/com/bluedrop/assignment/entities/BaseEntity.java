package com.bluedrop.assignment.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Base entity.
 *
 * @author Dimitar
 */
@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    public BaseEntity(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, State state) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.state = state;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    /**
     * Date the entity was last created.
     * We are forcing the save to be in the UTC timezone.
     */
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    /**
     * Date the entity was last modified.
     * We are forcing the save to be in the UTC timezone.
     */
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    /**
     * Used for keeping track of the entity state, ie active or deleted.
     */
    @Enumerated(EnumType.STRING)
    private State state;
}
