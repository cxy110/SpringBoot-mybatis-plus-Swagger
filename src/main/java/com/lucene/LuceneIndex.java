package com.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * lucene创建索引
 */
public class LuceneIndex {

  //indexSearch 是生成的索引库的位置,BaseDocument是原始文档的位置
  public LuceneIndex(String indexSearch,String BaseDocument) throws IOException {
   //第一步创建一个indexWriter对象,作用是将创建的索引存入索引库
      //1.indexwriter中需要有两个对象那个 1个是索引库的位置
      //2.指定分析器对文档内容进行分析

  Directory directory=new SimpleFSDirectory(new File(indexSearch));
    //ik中文分词器, ik中文词库可以自己添加关键词和过滤词汇,找到 Libraies下对应ik分词器jar包下的
    //IKAnalyzer.cfg.xml文件,里面指明了添加关键词库和过滤词库的名字ext.dic以及stopword.dic文件
    Analyzer analyzer=new IKAnalyzer();
    IndexWriterConfig config=new IndexWriterConfig(Version.LATEST,analyzer);

  IndexWriter indexWriter=new IndexWriter(directory, config);
  //原始的文档引入后,生成一个一个的文档(按照原始文档生成),文档中包含一个一个的域,这些域是原始文档的属性,域中又有一个一个的term(就是具体的分词)

    //将原始文档引入
    File file=new File(BaseDocument);

    //将文件夹的文件放入数组中
    File[]files=file.listFiles();

    //开始遍历引入的文档,分别生成field对象(域对象),然后将field对象放入document
    for(File file1: files){
      //生成文档
      Document document=new Document();
      //获取文件的名字
      String filename= file.getName();
      //这一子类的域数据类型是字符串型,是默认进行分析,添加索引,是否保存自己选择
      Field filenameField=new TextField("filename",filename, Field.Store.YES);
      //获取文件的大小
      long filesize= FileUtils.sizeOf(file1);
      //这一子类的域数据类型是long型,默认进行分析,添加索引,是否保存自己选择
      Field filesizeField= new LongField("filesize",filesize, Field.Store.YES);
      //获取文件的路径
      String filepath=file1.getPath();
      //这一子类的域支持多种数据类型,默认不分析,不添加索引,默认保存
      Field filepathField=new StoredField("filepath",filepath);
      //获取文件的内容
      String fileconent=FileUtils.readFileToString(file1);
      Field fileconentField=new TextField("fileconent",fileconent, Field.Store.YES);

      //将域放入文档中
      document.add(fileconentField);
      document.add(filenameField);
      document.add(filepathField);
      document.add(filesizeField);
      //将生成的文档加入到索引库中,此过程创建索引,并将索引和document文档写入索引库
      indexWriter.addDocument(document);
    }
    //生成完毕,释放资源
    indexWriter.close();
  }
}
