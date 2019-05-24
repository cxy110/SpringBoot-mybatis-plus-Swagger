package com.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * solrJ中对索引库的修改中,和新增的方法一样,如果索引库中有相同的id,就是修改,
 * 否则就是新增
 *
 * id是必须有的
 */
public class SolrJManager {
  /**
   * solr索引库的添加
   * @param baseURL
   * @throws Exception
   */
  public  void solrAdd(String baseURL) throws Exception {
      //新建一个solrserver连接solr服务器
    SolrServer solrServer=new HttpSolrServer(baseURL);
      //准备添加新的field域(索引)
    SolrInputDocument doc=new SolrInputDocument();
    doc.addField("id",123456);
    doc.addField("name","王大锤");
    doc.addField("family","大锤家里有好多的人");
    //将新的索引加入到solrserver
    solrServer.add(doc);
    //提交到solr后台索引库中
    solrServer.commit();
}

  /**
   * solr索引删除
   * @param baseURL
   * @throws IOException
   * @throws SolrServerException
   */
  public void  solrDelete(String baseURL) throws IOException, SolrServerException {
  //新建一个solrserver连接solr服务器
    SolrServer solrServer=new HttpSolrServer(baseURL);
    String query="item_sell_point: 清仓";
    solrServer.deleteByQuery("*:*");
    solrServer.commit();

}
//solr的查询
  public List solrSelect(String baseURL,  String sort) throws SolrServerException {
    SolrServer solrServer=new HttpSolrServer(baseURL);
    //查询条件,过滤条件,分页,指定查询结果中的域,默认域,高亮显示,排序
    SolrQuery solrQuery=new SolrQuery();
    //设置查询的域(查询的关键词)
     //"item_sell_point:清仓"
    solrQuery.set("q","item_sell_point:清仓");
    //设置过滤条件
      //"item_price:[200000 TO *]"

       solrQuery.setFilterQueries("item_price:[200000 TO *]");

     //设置排序方式
    if("1".equals(sort)) {
      solrQuery.addSort("item_price", SolrQuery.ORDER.desc);
    } else {
      solrQuery.addSort("item_price", SolrQuery.ORDER.asc);
    }
    //设置查询结果中显示的域
    solrQuery.set("fl","item_sell_point,item_price,item_title,id");
    //设置默认域
    solrQuery.set("df","item_sell_point");
    //设置分页(从第几条开始)
    solrQuery.setStart(0);
    //设置每页记录数
    solrQuery.setRows(10);
    //设置高亮
       //打开高亮
       solrQuery.setHighlight(true);
       //设置高亮域
    solrQuery.addHighlightField("item_sell_point");
    //设置高亮前缀
    solrQuery.setHighlightSimplePre("<span style='color:red'>");
    //设置高亮后缀
    solrQuery.setHighlightSimplePost("</span>");
    //执行查询
    QueryResponse response=solrServer.query(solrQuery);
    //获取文档结果集
    SolrDocumentList docs=response.getResults();
    //获取高亮结果集
        //第一个map的key是这个索引对应的数据的id,第二个map的key是高亮对应的域名,List中是高亮对应的内容
    Map<String, Map<String, List<String>>> map= response.getHighlighting();
    List A=new ArrayList();
    //将高亮得数据放在这个list中
    List highlight=new ArrayList();
    //遍历查询出来的结果,每一条商品信息放在一个List B中
    for(SolrDocument doc:docs){
      List B=new ArrayList();
      B.add(doc.get("item_sell_point"));
      B.add(doc.get("item_price"));
      B.add(doc.get("item_title"));
      B.add(doc.get("id"));
        Object x= doc.get("id");
        //然后把每一条的商品list B放在List A中
      A.add(B);
      //为获得高亮显示的数据
      //其中response.getHighlighting()中的id要求是String,但数据库中是long型,所以先转化成string类型
     String number=  doc.get("id").toString();
     //通过转化后的商品id查找高亮域和高亮数据
      Map<String, List<String>> C = map.get(number);
      //通过高亮域查找具体高亮的信息
      List<String> d=C.get("item_sell_point");
     highlight.add(d.get(0));

    }
      //最后把高亮信息的list集合也放在List A中输出
    A.add(highlight);

    return  A;
  }
}