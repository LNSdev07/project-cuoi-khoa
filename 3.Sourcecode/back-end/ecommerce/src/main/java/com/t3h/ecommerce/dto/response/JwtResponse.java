package com.t3h.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String name;
    private String avatar;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String token, String name, Collection<? extends GrantedAuthority> roles, String avatar) {
        this.token = token;
        this.name = name;
        this.roles = roles;
        this.avatar = avatar;
    }
}
