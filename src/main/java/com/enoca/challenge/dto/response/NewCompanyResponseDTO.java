package com.enoca.challenge.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCompanyResponseDTO {
    private Long oid;
    private String companyName;
    private String phone;
    private String address;
    private String email;
    private int foundingYear;
}
