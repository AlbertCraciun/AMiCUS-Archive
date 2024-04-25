package ro.amicus.archive.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import ro.amicus.archive.enums.AcademicStatus;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class MyUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "branch", referencedColumnName = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "faculty", referencedColumnName = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "bcd_start_year")
    private Integer bcdStartYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "bcd_status")
    private AcademicStatus bcdStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "md_status")
    private AcademicStatus mdStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "dd_status")
    private AcademicStatus ddStatus;

    @ManyToOne
    @JoinColumn(name = "user_role", referencedColumnName = "role_id", nullable = false)
    private UserRole userRole;

    @NonNull
    @Column(name = "approved_role_by")
    private String approvedRoleBy;

    @NonNull
    @Column(name = "approved_role_on")
    private LocalDate approvedRoleOn;

    @Column(name = "last_update_on")
    @UpdateTimestamp
    private Timestamp lastUpdateOn;

    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    private Timestamp createdOn;
}