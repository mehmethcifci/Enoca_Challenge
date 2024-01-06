package com.enoca.challenge.controller;

import com.enoca.challenge.dto.request.NewCompanyRequestDTO;
import com.enoca.challenge.dto.request.UpdateCompanyRequestDTO;
import com.enoca.challenge.dto.response.AllCompaniesResponseDTO;
import com.enoca.challenge.services.CompanyService;

import com.enoca.challenge.solr.CompanyDocument;
import com.enoca.challenge.solr.CompanyDocumentSolrService;
import com.enoca.challenge.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyDocumentSolrService solrService;


    @PostMapping("/create")
    public ResponseEntity<Void> createCompany(@RequestBody @Valid NewCompanyRequestDTO companyRequestDto) throws SolrServerException, IOException {
        companyService.save(companyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/findall")
    public ResponseEntity<List<AllCompaniesResponseDTO>> findAllCompanies(){
        return ResponseEntity.ok(companyService.findAll());
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateCompany(@RequestBody @Valid UpdateCompanyRequestDTO updateDto) throws SolrServerException, IOException {
        companyService.update(updateDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id){
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getbyupdatedat/{updatedAt}")
    public ResponseEntity<List<AllCompaniesResponseDTO>> getAllCompanyDocumentByUpdatedAt(@PathVariable("updatedAt") String updatedAt) throws ParseException, SolrServerException, IOException {
        List<AllCompaniesResponseDTO> companyDocumentList = solrService.getAllByUpdatedAt(DateUtil.convertStringDateToLong(updatedAt));
        return ResponseEntity.ok(companyDocumentList);
    }
}
