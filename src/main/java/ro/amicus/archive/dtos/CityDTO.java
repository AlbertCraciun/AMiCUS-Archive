package ro.amicus.archive.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.amicus.archive.entities.Country;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private String cityName;
    private Country country;

}
