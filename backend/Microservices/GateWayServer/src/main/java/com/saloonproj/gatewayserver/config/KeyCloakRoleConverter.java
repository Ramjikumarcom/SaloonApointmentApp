package com.saloonproj.gatewayserver.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class KeyCloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayDeque<>();
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> realmRoles = (List<String>) realmAccess.get("roles");
            realmRoles.forEach(role -> authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())
            ));
        }

        Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");
        if (resourceAccess != null) {
            resourceAccess.forEach((client, clientDetails) -> {
                Map<String, Object> clientRoles = (Map<String, Object>) clientDetails;

                if (clientRoles.containsKey("roles")) {
                    List<String> roles = (List<String>) clientRoles.get("roles");
                    roles.forEach(role -> authorities.add(
                            new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())
                    ));
                }
            });
        }
        return authorities;
    }
}
