package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbItem extends Model<TbItem> {

private static final long serialVersionUID=1L;

    /**
     * 商品id，同时也是商品编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格，单位为：分
     */
    private Long price;

    /**
     * 库存数量
     */
    private Integer num;

    /**
     * 商品条形码
     */
    private String barcode;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 所属类目，叶子类目
     */
    private Long cid;

    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 更新时间
     */
    private LocalDateTime updated;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
