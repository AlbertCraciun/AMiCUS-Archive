package ro.amicus.archive.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRole {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "role_id", updatable = false, nullable = false)
    private UUID roleId;

    @ManyToOne
    @JoinColumn(name = "privilege", referencedColumnName = "privilege_id")
    private Privilege privilege;

    @Column(name = "name")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "department_id")
    private Department department;

    @Column(name = "start_date")
    private LocalDate startDate;

    @UpdateTimestamp
    @Column(name = "last_update_on")
    private Timestamp lastUpdateOn;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;
}
