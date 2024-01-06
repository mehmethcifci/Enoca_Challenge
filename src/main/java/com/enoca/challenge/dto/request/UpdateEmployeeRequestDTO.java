package com.enoca.challenge.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDTO {
    private Long oid;
    private String name;
    private String surname;
    private String identificationNumber;
    private String position;
    private double salary;
    private Long companyOid;

}
