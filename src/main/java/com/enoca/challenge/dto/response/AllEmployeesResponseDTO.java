package com.enoca.challenge.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllEmployeesResponseDTO {
    private String name;
    private String surname;
    private String identificationNumber;
    private String position;
    private double salary;
    private Long companyOid;


}
