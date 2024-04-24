package ro.amicus.archive.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private String branchName;
    private String departmentName;
    private String name;
    private Integer editions;
    private Integer startYear;
    private LocalDate lastEdDate;
    private Integer lastEdParticipants;
    private Integer lastEdExpenses;
    private String objectives;
    private String description;
    private LocalDate lastUpdateOn;

}
