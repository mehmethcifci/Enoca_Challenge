package com.enoca.challenge.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {


    private String solrServerUrl = "http://localhost:8983/solr";

    @Bean
    public SolrClient solrClient() {
        return new Http2SolrClient.Builder(solrServerUrl).build();
    }
}