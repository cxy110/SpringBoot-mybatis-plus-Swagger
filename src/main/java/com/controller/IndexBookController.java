package com.controller;

import com.entity.IndexBook;
import com.service.IndexBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@RestController
@RequestMapping("/indexBook")
public class IndexBookController {
  @Autowired
  private IndexBookService indexBookService;

  //通过BaseMapper查询
  @PostMapping("/findList/{param}")
  public List<IndexBook> findList(@PathVariable String param) {
    List<IndexBook> list = indexBookService.openlist(param);
    return list;
  }

}


