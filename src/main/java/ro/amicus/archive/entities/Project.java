package ro.amicus.archive.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "project_id", updatable = false, nullable = false)
    private UUID projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch", referencedColumnName = "branch_id")
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department", referencedColumnName = "department_id")
    private Department department;

    @Column(name = "name")
    private String name;

    @Column(name = "editions")
    private Integer editions;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "last_ed_date")
    private Date lastEdDate;

    @Column(name = "last_ed_participants")
    private Integer lastEdParticipants;

    @Column(name = "last_ed_expenses")
    private Integer lastEdExpenses;

    @Column(name = "objectives")
    private String objectives;

    @Column(name = "description")
    private String description;

    @UpdateTimestamp
    @Column(name = "last_update_on")
    private Timestamp lastUpdateOn;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;
}
