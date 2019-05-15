package com.serviceImpl;

import com.entity.TbItem;
import com.dao.TbItemMapper;
import com.service.TbItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@Service
public class TbItemServiceImpl extends ServiceImpl<TbItemMapper, TbItem> implements TbItemService {

}
