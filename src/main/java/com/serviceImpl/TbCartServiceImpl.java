package com.serviceImpl;

import com.entity.TbCart;
import com.dao.TbCartMapper;
import com.service.TbCartService;
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
public class TbCartServiceImpl extends ServiceImpl<TbCartMapper, TbCart> implements TbCartService {

}
