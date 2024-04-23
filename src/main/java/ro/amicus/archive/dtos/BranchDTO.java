package ro.amicus.archive.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.amicus.archive.entities.City;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    private City city;
    private Integer foundationYear;

}
