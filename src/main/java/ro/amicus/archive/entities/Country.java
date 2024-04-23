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
@Table(name = "countries")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "country_id", updatable = false, nullable = false)
    private UUID countryId;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "last_update_on")
    @UpdateTimestamp
    private Timestamp lastUpdateOn;

    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    private Timestamp createdOn;

}

