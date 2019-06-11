package com.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.TbBook;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
public interface TbBookService extends IService<TbBook> {
  @Override
  List list(Wrapper<TbBook> queryWrapper);

}
