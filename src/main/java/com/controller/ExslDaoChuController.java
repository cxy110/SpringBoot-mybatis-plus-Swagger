package com.controller;

import com.office.ExslDaoChuTool;
import io.swagger.annotations.Api;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @Description:
 * @author:ChenXY
 * @Date:2019/5/30
 */
@Api(value = "表格输出")
@RestController
@RequestMapping("/v1/lo")
public class ExslDaoChuController {

  @Autowired
  private ExslDaoChuTool exslDaoChuTool;
  @PostMapping("/exclude")
  public  String exclude(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="tname")
                       String tname){
    //调用工具类生成表格
  HSSFWorkbook book= exslDaoChuTool.writeExcel(tname);
  String path="d:/"+tname+".xls";
   // FileOutputStream output=new FileOutputStream();
response.reset();
//设置响应文件的格式为excel表格
response.setContentType("application/msexcel");
response.setHeader("Content-disposition","attachment;filename="+tname+".xls");
/**
 * 通过response设置响应的文件格式与名字(文件下载相关的)
 * ,然后通过获取response的输出流,将表格(文档)输出,前端会显示一个下载按钮,点击后下载
 *
 * 如果想生成文件后直接导出到本地,那就直接 new FileOutoutStream,然后book.writer(),
 */
    try {
  OutputStream output=response.getOutputStream();
  book.write(output);
  output.close();
}catch(Exception e){
  e.printStackTrace();
}
return  "你好";
  }
}
