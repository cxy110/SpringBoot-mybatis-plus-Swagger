package com.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @Description:
 * @author:ChenXY
 * @Date:2019/5/30
 *
 * 下面的****项目资料.doc是上传文件后生成的仪的文档,在实际中,需要根据(改为)实际存在的文件
 */
@RestController
@Api(value = "文件上传,下载",tags = {"文件上传操作"})
@RequestMapping("/v1/upload")
public class OfficeuploadController {
  //文件上传所对应的一些属性(条件),在applicattion.properties中
  @Autowired
  private MultipartProperties properties;

  /**
   * word文件上传
   * @param file
   * @return
   */
  @PostMapping("/up")
  public String upload(MultipartFile file) {
    //获取文件名
    String filename = file.getOriginalFilename();
    //获取文件后缀名
    String last = filename.substring(filename.lastIndexOf(".") + 1);
    if(!(last.equals("doc")||last.equals("docx")) ){
      throw new RuntimeException("您提交的不是word文档,请重新提交");
    }
    String a = UUID.randomUUID().toString().replace("-", "");
    //重新命名
    filename = a + filename;
String path= properties.getLocation();
    File file1 = new File(path+filename);
    if (!file1.exists()) {
      file1.mkdir();
    }
    try {
      file.transferTo(file1);

    }catch (Exception e){
      e.printStackTrace();
    }

    return null;
  }

  /**
   * word文件下载
   * @param response
   * @param request
   */
  @PostMapping("/downDoc")
  public void dowmDoc(HttpServletResponse response, HttpServletRequest request){
     String path=properties.getLocation();
    path=path+"nulle8f850f92e194e2e812e4d79f40c0791项目资料.doc";
    response.setContentType("application/msdoc");
    response.setHeader("Content-disposition","attachment;filename=haha.doc");
    try {
      InputStream in = new FileInputStream(path);
      OutputStream out =response.getOutputStream();
      int b;
      while((b=in.read())!=-1){
     out.write(b);
      }
      in.close();
      out.close();
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * word转化为pdf
   * @param response
   */
  //还没写完
  @PostMapping("/wordToPdf")
  public void wordToPdf(HttpServletResponse response){
    String wordpath=properties.getLocation()+"nulle8f850f92e194e2e812e4d79f40c0791项目资料.doc";
    response.setContentType("application/pdf");
    response.setHeader("Content-disposition","attachment;filename=nihao.pdf");
    try {
     /* PathAdapter pathAdapter=new PathAdapter();
     String AS= pathAdapter.getLicensePath("D:/Maven本地仓库/com/github/zxhTom/office-aspose/1.0.0/office-aspose-1.0.0" +
          ".jar!/license" +
              ".xml");*/


      OutputStream output = response.getOutputStream();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  }

