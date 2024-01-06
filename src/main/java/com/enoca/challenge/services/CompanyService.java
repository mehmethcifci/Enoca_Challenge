package com.enoca.challenge.services;

import com.enoca.challenge.dto.request.NewCompanyRequestDTO;
import com.enoca.challenge.dto.request.UpdateCompanyRequestDTO;
import com.enoca.challenge.dto.response.AllCompaniesResponseDTO;
import com.enoca.challenge.entity.Company;
import com.enoca.challenge.exception.custom.CompanyNotFoundException;
import com.enoca.challenge.repository.CompanyRepository;
import com.enoca.challenge.solr.CompanyDocumentSolrService;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyDocumentSolrService companyDocumentSolrService;

    public void save(NewCompanyRequestDTO companyRequestDto) throws SolrServerException, IOException {
        Company company = Company.builder()
                .companyName(companyRequestDto.getCompanyName())
                .email(companyRequestDto.getEmail())
                .updatedAt(new Date().getTime())  //1559779200000L
                .phone(companyRequestDto.getPhone())
                .foundingYear(companyRequestDto.getFoundingYear())
                .address(companyRequestDto.getAddress())
                .build();
        companyRepository.save(company);
        companyDocumentSolrService.saveDocument(company);
    }

    public List<AllCompaniesResponseDTO> findAll() {
        List<Company> allCompanies = companyRepository.findAll();
        if(allCompanies.isEmpty()){
            throw new CompanyNotFoundException("Company is not found");
        }
        return allCompanies.stream().map(company -> AllCompaniesResponseDTO.builder()
                .companyName(company.getCompanyName())
                .phone(company.getPhone())
                .foundingYear(company.getFoundingYear())
                .address(company.getAddress())
                .email(company.getEmail())
                .build()).collect(Collectors.toList());
    }

    public void update(UpdateCompanyRequestDTO updateDto) throws SolrServerException, IOException {
        if (companyRepository.findById(updateDto.getOid()).isEmpty()){
            throw new CompanyNotFoundException("Company is not found");
        }
        Company company =Company.builder()
                .oid(updateDto.getOid())
                .companyName(updateDto.getCompanyName())
                .address(updateDto.getAddress())
                .email(updateDto.getEmail())
                .updatedAt(new Date().getTime())
                .phone(updateDto.getPhone())
                .foundingYear(updateDto.getFoundingYear())
                .build();
        companyRepository.save(company);
        companyDocumentSolrService.saveDocument(company);
    }

    public Boolean deleteCompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isEmpty()){
            throw new CompanyNotFoundException("Company is not found");
        }
        companyRepository.delete(optionalCompany.get());
        return true;
    }
}
