package com.controller;


import com.entity.JsonResult;
import com.entity.StudentBook;
import com.service.StudentBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@RestController
@RequestMapping("/studentBook")
@Api(description =  "学生书籍相关操作",value = "学生")
public class StudentBookController {
  @Autowired
 private StudentBookService studentBookService;
  //通过ModelMapper查询
  @GetMapping("/find/{level}")
  @ApiOperation(value = "查找资料",nickname = "查找列表",notes = "这是查询的书籍结果",httpMethod ="GET")
  @ApiImplicitParam(name = "level",value = "水平",required = true,defaultValue = "初级",dataType = "String",example = "高级")
  public JsonResult findList(@PathVariable String level){
  List<StudentBook>list=studentBookService.findList(level);
  if(!list.isEmpty()){
    return new JsonResult().ok(list);
  }
  return new JsonResult().error();

}

@PostMapping("/delete/{name}")
  public String delete(@PathVariable String name){
    return name;
}
}

