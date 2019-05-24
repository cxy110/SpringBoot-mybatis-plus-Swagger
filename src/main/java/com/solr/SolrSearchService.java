package com.solr;

import org.apache.solr.client.solrj.SolrServerException;

public interface SolrSearchService {
 SolrSearchEntity SolrSearch(String baseURL,String price,String keyword,  String sort) throws SolrServerException;
}
