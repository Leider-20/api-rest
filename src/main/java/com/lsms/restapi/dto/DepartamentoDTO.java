package com.lsms.restapi.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoDTO {
    private Long id;
    private  String nombre;
}