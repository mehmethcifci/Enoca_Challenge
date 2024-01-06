package com.enoca.challenge.solr;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.apache.solr.client.solrj.beans.Field;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDocument {
    @Id
    @Field
    private Long oid;
    @Field
    private String companyName;
    @Field
    private String email;
    @Field
    private String phone;
    @Field
    private String address;
    @Field
    private int foundingDate;
    @Field
    private Long updatedAt;
}
