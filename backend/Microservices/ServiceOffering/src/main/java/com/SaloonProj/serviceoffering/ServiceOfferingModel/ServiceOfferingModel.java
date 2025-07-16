package com.SaloonProj.serviceoffering.ServiceOfferingModel;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ServiceOfferingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long offerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private  int price;

    @Column(nullable = false)
    private  int duration; // in minnute

    @Column(nullable = false)
    private  Long saloonId;

    @Column(nullable = false)
    private  Long CategoryId;

    @Column(nullable = false)
    private  String image;

}
