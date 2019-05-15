package com.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dao.StudentBookMapper;
import com.entity.StudentBook;
import com.service.StudentBookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@Service
public class StudentBookServiceImpl extends ServiceImpl<StudentBookMapper, StudentBook> implements StudentBookService {

  @Override
  public List<StudentBook> findList(String level) {
    /*
    * 通过ModelMapper来进行查询
    * */
    QueryWrapper<StudentBook> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("level",level);
    StudentBook studentBook=new StudentBook();
    List<StudentBook>list= studentBook.selectList(queryWrapper);
    return list;



  }



}