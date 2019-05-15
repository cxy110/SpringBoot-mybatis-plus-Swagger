package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.IndexBook;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
public interface IndexBookService extends IService<IndexBook> {

   List<IndexBook> openlist(String param);

}
