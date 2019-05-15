package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.service.TbBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/tbBook")
public class TbBookController {
  @Autowired
  private TbBookService tbBookService;

  //指定字段查询
  @RequestMapping("/select")
  public List find(){
    QueryWrapper queryWrapper=new QueryWrapper<>();


    queryWrapper.select("name","book_num");

     List list = tbBookService.list(queryWrapper);
    return list;
  }

}

