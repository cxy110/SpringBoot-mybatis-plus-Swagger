package com.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dao.StudentSectionMapper;
import com.entity.StudentSection;
import com.service.StudentSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@Service
public class StudentSectionServiceImpl extends ServiceImpl<StudentSectionMapper, StudentSection> implements StudentSectionService {
@Autowired
private StudentSectionMapper studentSectionMapper;
//分页查询
  @Override
  public IPage<StudentSection> findLimit(Integer page, Integer pageSize, String isFree) {

    Page<StudentSection>page1=new Page<>(page,pageSize);
    QueryWrapper<StudentSection>queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("is_free",isFree);
     IPage<StudentSection> list= studentSectionMapper.selectPage(page1,queryWrapper);


   //page1.setRecords(list);

    return list ;
  }
}
