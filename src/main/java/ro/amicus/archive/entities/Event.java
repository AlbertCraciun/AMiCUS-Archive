package ro.amicus.archive.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import ro.amicus.archive.enums.EventTypes;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "event_id", updatable = false, nullable = false)
    private UUID eventId;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EventTypes type;

    @ManyToOne
    @JoinColumn(name = "city", referencedColumnName = "city_id")
    private City city;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "participants")
    private Integer participants;

    @Column(name = "last_update_on")
    private Timestamp lastUpdateOn;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;
}
