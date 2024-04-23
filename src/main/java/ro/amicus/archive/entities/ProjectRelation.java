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
@Table(name = "project_relations")
@Getter
@Setter
public class ProjectRelation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "relation_id", updatable = false, nullable = false)
    private UUID relationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id_1", referencedColumnName = "project_id")
    private Project project1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id_2", referencedColumnName = "project_id")
    private Project project2;

    @Column(name = "relation_type")
    private String relationType;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @UpdateTimestamp
    @Column(name = "last_update_on")
    private Timestamp lastUpdateOn;
}