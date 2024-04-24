package ro.amicus.archive.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.amicus.archive.enums.EventTypes;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private String name;
    private EventTypes type;
    private String cityName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer participants;
    private String description;

}
