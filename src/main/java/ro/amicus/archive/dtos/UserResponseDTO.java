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
public class UserResponseDTO {

    private String lastName;
    private String firstName;
    private String email;
    private LocalDate birthDate;
    private String branchName;
    private String facultyName;
    private Integer bcdStartYear;
    private String bcdStatus;
    private String mdStatus;
    private String ddStatus;
    private String role;
    private LocalDate lastUpdate;

}
