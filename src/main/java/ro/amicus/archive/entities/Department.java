package ro.amicus.archive.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "department_id", updatable = false, nullable = false)
    private UUID departmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update_on")
    private Timestamp lastUpdateOn;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;
}
