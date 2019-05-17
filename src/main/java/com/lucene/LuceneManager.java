package com.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
/**
 * 对lucene索引库进行增,删,改等操作
 */
public class LuceneManager {
  //这个方法是增删改等的公共部分
  //indexSearch是索引库的位置
  public IndexWriter manager(String indexSearch) throws IOException {
    //把索引库的路径引入内存
    Directory directory = new SimpleFSDirectory(new File(indexSearch));
    //Ik分词器
    Analyzer analyzer = new IKAnalyzer();
    IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,analyzer);
    //创建索引写入操作对象
    IndexWriter indexWriter = new IndexWriter(directory, config);
    return indexWriter;
  }

  // 进行删除所有操作
  public void deleteall(String indexSearch) throws IOException {
    IndexWriter indexWriter = manager(indexSearch);
    indexWriter.deleteAll();
    indexWriter.close();
  }
  //根据条件删除
         //参数field是索引库的一个域名
  public void delete(String indexSearch ,String field) throws IOException {
    IndexWriter indexWriter=manager(indexSearch);
    //删除的条件(某个域下的,具体关键词)
    Query query=new TermQuery(new Term(field,"笔记"));
   indexWriter.deleteDocuments(query);
     //删除某个域
     //indexWriter.deleteDocuments(new Term(field));
   indexWriter.close();
  }

  //修改
  public  void  update(String indexSearch,String field) throws IOException {
    IndexWriter indexWriter=manager(indexSearch);
    Term delTerm=new Term(field,"笔记");
    Document doc =new Document();
    doc.add(new TextField("fileTest","流星蝴蝶剑", Field.Store.YES));
    doc.add(new TextField("filemore","仙剑奇侠传", Field.Store.YES));
    Analyzer analyzer=new IKAnalyzer();
    //第一个参数,要删除的关键词,第二个新添加的域,第三个是分词器
    indexWriter.updateDocument(delTerm, doc,analyzer);
    indexWriter.close();
  }
}