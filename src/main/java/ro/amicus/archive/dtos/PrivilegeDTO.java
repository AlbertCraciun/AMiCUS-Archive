package ro.amicus.archive.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.amicus.archive.enums.PrivilegeNames;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeDTO {

    private PrivilegeNames name;
    private Integer activeDays;

}
