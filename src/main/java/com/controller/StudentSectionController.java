package com.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.entity.StudentSection;
import com.service.StudentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@RestController
@RequestMapping("/studentSection")
public class StudentSectionController {
  @Autowired
 private StudentSectionService studentSectionService;
  //分页查询
@RequestMapping("/GET/{page}/{pageSize}/{isFree}")
  public IPage<StudentSection> get(@PathVariable Integer page,
                                   @PathVariable Integer pageSize,
                                   @PathVariable String isFree){
 IPage<StudentSection>p = studentSectionService.findLimit(page ,pageSize,isFree);
 return p;
}
}

