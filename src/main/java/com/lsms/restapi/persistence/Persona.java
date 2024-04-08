package com.lsms.restapi.persistence;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Integer edad;
    private String email;
}
