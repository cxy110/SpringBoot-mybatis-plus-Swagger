package com.serviceImpl;

import com.entity.TbUser;
import com.dao.TbUserMapper;
import com.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
