package com.controller;

import com.entity.JsonResult;
import com.solr.SolrParam;
import com.solr.SolrSearchEntity;
import com.solr.SolrSearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolrSearchController {
  @Autowired
  private SolrSearchService solrSearchService;
  @PostMapping(value = "/solr/",produces ="application/json;charset=utf-8" )
  public JsonResult SolrSearch(@RequestBody SolrParam solrParam) throws SolrServerException {

SolrSearchEntity solrSearchEntity=solrSearchService.SolrSearch(solrParam.getBaseURL(),solrParam.getPrice(),
    solrParam.getKeyword(),solrParam.getSort());
    JsonResult jsonResult= new JsonResult(solrSearchEntity);
    return jsonResult;
  }

}
