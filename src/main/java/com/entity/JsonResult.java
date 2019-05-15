package com.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
@ApiModel(value ="输出结果",description = "结果包装")
public class JsonResult {
  @ApiModelProperty(name="message",value = "输出信息")
  private String message;
  @ApiModelProperty(value = "状态",required = true)
  private  Integer status;
  @ApiModelProperty("查询结果")
  private List<StudentBook> list;
  public  JsonResult(){
    this.message="没有查到相关信息,请您确认查询关键词是否正确";
    this.status=500;

  }
  public JsonResult(List<StudentBook> list){
    this.list=list;
    this.status=200;
    this.message="查找到的结果如下";
  }
  public JsonResult ok(List<StudentBook> list){
    return new JsonResult(list);
  }
 public  JsonResult error(){
    return new JsonResult();
  }

}
