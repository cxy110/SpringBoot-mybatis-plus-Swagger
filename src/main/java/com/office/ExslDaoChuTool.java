package com.office;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @Description:exsl导出数据库的工具类
 * @author:ChenXY
 *
 * @Date:2019/5/30
 */
@Component
public class ExslDaoChuTool {

  public static HSSFWorkbook writeExcel(String tname){
    //输出路径
    String path="d:/"+tname+".xls";
    //生成exsl的文档对象
    HSSFWorkbook book=new HSSFWorkbook();
    //创建exsl的表单
    HSSFSheet sheet=book.createSheet("测试表");
    try {
      //jdbc数据驱动等几步查询
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection=null;
      connection= DriverManager.getConnection("jdbc:mysql://192.168.0.96:3306/jtdb?serverTimezone=UTC&useUnicode=true" +
          "&characterEncoding=utf8&characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&autoReconnct" +
          "=true&autoReconnectForPools=true&allowMultiQueries=true","root","root");
      String sql="select * from "+tname;
      PreparedStatement statement=connection.prepareStatement(sql);
        ResultSet rs= statement.executeQuery();
        //获取结果集中的字段名
      ResultSetMetaData metaData=rs.getMetaData();
      //获取结果集中字段的数量
      int c= metaData.getColumnCount();
      //生成表格的第一行,表头
      HSSFRow row0=sheet.createRow(0);
      //创建表头
      for(int i=0;i<c;i++){
        HSSFCell cell=row0.createCell(i);//创建第一行(表头)第i列
        //将对应的字段名填入第一行的每一列中
        cell.setCellValue(metaData.getColumnName(i+1));
      }
     //填充数据
      int r=1;
      while (rs.next()){
        //创建第r行,对应的是第r条数据
        HSSFRow row=sheet.createRow(r++);
        //开始填充第r条的具体数据
        for(int i=0;i<c;i++){
          HSSFCell cell=row.createCell(i);
          cell.setCellValue(rs.getString(i+1));
        }

      }
     // FileOutputStream out =new FileOutputStream(path);
     // book.write(out);
      if(rs!=null)
      rs.close();
      if(statement!=null)
        statement.close();
     if(connection!=null)
      connection.close();
   //   out.flush();
   //   out.close();
    }catch (Exception e){
      e.printStackTrace();
    }
    return  book;
  }
}
