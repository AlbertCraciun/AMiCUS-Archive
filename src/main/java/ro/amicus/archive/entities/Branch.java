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
@Table(name = "branches")
@Getter
@Setter
public class Branch {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "branch_id", updatable = false, nullable = false)
    private UUID branchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city", referencedColumnName = "city_id")
    private City city;

    @Column(name = "foundation_year")
    private Integer foundationYear;

    @Column(name = "last_update_on")
    @UpdateTimestamp
    private Timestamp lastUpdateOn;

    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    private Timestamp createdOn;
}

