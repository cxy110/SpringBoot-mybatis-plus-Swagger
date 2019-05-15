package com.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dao.IndexBookMapper;
import com.entity.IndexBook;
import com.service.IndexBookService;
import org.springframework.beans.factory.annotation.Autowired;
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

public class IndexBookServiceImpl extends ServiceImpl<IndexBookMapper, IndexBook>
    implements IndexBookService {
  @Autowired
  private IndexBookMapper indexBookMapper;
  //通过BaseMapper查询
  @Override
  public List openlist(String param) {
    QueryWrapper<IndexBook>queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("teacher",param);
    List list=indexBookMapper.selectList(queryWrapper);
    return list;
  }
}
