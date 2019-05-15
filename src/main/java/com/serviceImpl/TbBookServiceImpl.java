package com.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.entity.TbBook;
import com.dao.TbBookMapper;
import com.service.TbBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TbBookServiceImpl extends ServiceImpl<TbBookMapper, TbBook> implements TbBookService {
  @Override
  public List list(Wrapper queryWrapper) {
    return super.list(queryWrapper);
  }
}
