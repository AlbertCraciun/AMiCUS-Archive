package ro.amicus.archive.dtos;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUserRoleRequestDTO {

    private String lastName;
    private String firstName;
    private String email;
    private UserRoleDTO userRoleDTO;

    @NonNull
    private String approvedRoleBy;

    @NotNull
    private LocalDate approvedRoleOn;

}
