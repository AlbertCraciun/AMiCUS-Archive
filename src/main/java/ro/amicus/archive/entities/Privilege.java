package ro.amicus.archive.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "privileges")
@Getter
@Setter
public class Privilege {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "privilege_id", updatable = false, nullable = false)
    private UUID privilegeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active_days", nullable = false)
    private Integer activeDays;

    @UpdateTimestamp
    @Column(name = "last_update_on")
    private Timestamp lastUpdateOn;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

}

