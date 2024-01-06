package com.enoca.challenge.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCompanyRequestDTO {
    private Long oid;
    private String companyName;
    private String phone;
    private String address;
    private String email;
    private int foundingYear;
    private Long updatedAt;


}
