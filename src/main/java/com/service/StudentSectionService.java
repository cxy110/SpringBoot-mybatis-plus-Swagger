package com.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.StudentSection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
public interface StudentSectionService extends IService<StudentSection> {
  IPage<StudentSection> findLimit(Integer page, Integer pageSize, String isFree);
}
