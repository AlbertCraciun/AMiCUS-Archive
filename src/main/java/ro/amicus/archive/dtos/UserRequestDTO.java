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
public class UserRequestDTO {

    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String branch;
    private String faculty;
    private Integer bcdStartYear;
    private String bcdStatus;
    private String mdStatus;
    private String ddStatus;

}
