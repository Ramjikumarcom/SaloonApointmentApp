package com.SaloonProj.saloonapointment.OtherDto;

import lombok.*;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


// Data Transfer Objects(DTO)
public class KeycloakRole {
    private  String id;

    private String name;

    private  String description;

    private  boolean composite;

    private  boolean clientRole;

    private  String containerId;

    private Map<String,Object> attributes;

}
