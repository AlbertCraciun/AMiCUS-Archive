package ro.amicus.archive.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "faculties")
@Getter
@Setter
public class Faculty {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "faculty_id", updatable = false, nullable = false)
    private UUID facultyId;

    @ManyToOne
    @JoinColumn(name = "university", referencedColumnName = "university_id")
    private University university;

    @Column(name = "name")
    private String name;

    @Column(name = "years")
    private Integer years;

    @UpdateTimestamp
    @Column(name = "last_update_on")
    private Timestamp lastUpdateOn;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;
}
