package com.enoca.challenge.solr;

import com.enoca.challenge.dto.response.AllCompaniesResponseDTO;
import com.enoca.challenge.entity.Company;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyDocumentSolrService {


    private Http2SolrClient solrClient;

    public CompanyDocumentSolrService() {
        solrClient = new Http2SolrClient.Builder("http://localhost:8983/solr/company").build();
        solrClient.setParser(new XMLResponseParser());
    }


    public void saveDocument(Company company) throws SolrServerException, IOException {
        SolrInputDocument document = createCompanyDocument(company);
        solrClient.add(document);
        solrClient.commit();
    }

    public SolrInputDocument createCompanyDocument(Company company) {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("oid", company.getOid());
        document.addField("companyName", company.getCompanyName());
        document.addField("foundingDate", company.getFoundingYear());
        document.addField("updatedAt", company.getUpdatedAt());
        return document;
    }

    public List<AllCompaniesResponseDTO> getAllByUpdatedAt(Long updatedAt) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setQuery("updatedAt:[" + updatedAt + " TO *]");
        List<AllCompaniesResponseDTO> responseDTOList = new ArrayList<>();
        try {
            QueryRequest request = new QueryRequest(query, SolrRequest.METHOD.GET);
            SolrResponse response = request.process(solrClient);

            ((QueryResponse) response).getResults().forEach(result -> {
                AllCompaniesResponseDTO dto = AllCompaniesResponseDTO.builder()
                        .companyName(result.get("companyName").toString())
                        .build();
                responseDTOList.add(dto);
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the Solr client
            if (solrClient != null) {
                solrClient.close();
            }
        }
        return responseDTOList;

    }

}
