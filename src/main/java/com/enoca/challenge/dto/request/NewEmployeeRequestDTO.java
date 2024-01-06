package com.enoca.challenge.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewEmployeeRequestDTO {
    private String name;
    private String surname;
    private String identificationNumber;
    private String position;
    private double salary;
    private Long companyOid;
}
