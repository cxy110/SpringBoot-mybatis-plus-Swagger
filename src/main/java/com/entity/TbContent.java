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
 * 
 * </p>
 *
 * @author CXY
 * @since 2019-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbContent extends Model<TbContent> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容类目ID
     */
    private Long categoryId;

    /**
     * 内容标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 标题描述
     */
    private String titleDesc;

    /**
     * 链接
     */
    private String url;

    /**
     * 图片绝对路径
     */
    private String pic;

    /**
     * 图片2
     */
    private String pic2;

    /**
     * 内容
     */
    private String content;

    private LocalDateTime created;

    private LocalDateTime updated;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
