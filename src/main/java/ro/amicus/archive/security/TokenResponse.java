package ro.amicus.archive.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
    private String token;
    private String validUntil;
    private String activeRole;
}